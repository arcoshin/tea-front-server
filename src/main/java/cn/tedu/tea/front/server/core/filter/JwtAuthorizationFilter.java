package cn.tedu.tea.front.server.core.filter;

import cn.tedu.tea.front.server.account.dao.cache.IUserCacheRepository;
import cn.tedu.tea.front.server.common.consts.HttpConsts;
import cn.tedu.tea.front.server.common.pojo.po.UserLoginInfoPO;
import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.common.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@Component
/**
 * <p>處理JWT的過濾器</p>
 *
 * <p>此過濾器的主要作用：</p>
 * <ul>
 *     <li>嘗試接收客戶端的請求中攜帶的JWT數據</li>
 *     <li>嘗試解析JWT數據</li>
 *     <li>將解析得到的用戶數據創建為Authentication對象，並存入到SecurityContext中</li>
 * </ul>
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter implements HttpConsts {

    /**
     * 從配置文件獲取secretKey值
     */
    @Value("${tea-store.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private IUserCacheRepository userCacheRepository;

    /**
     * JWT最小長度值
     */
    private final static Integer JWT_MIN_LENGTH = 113;

    public JwtAuthorizationFilter() {
        log.debug("創建過濾器對象:JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.debug("處理JWT的過濾器開始處理當前請求......");

        //-------------------過濾器處理流程開始------------------------------------------------------------------------------------------------------------------

        /**===================================
         嘗試接收客戶端的請求中攜帶的JWT數據
         ====================================*/

        // 業內慣用的做法是：客戶端會將JWT放在請求頭中名為Authorization的屬性中
        String jwt = request.getHeader(HEADER_AUTHORIZATION);
        log.debug("客戶端攜帶的JWT：{}", jwt);

        /**==================
         嘗試解析JWT數據
         ===================*/

        // 判斷JWT的基本有效性（沒有必要嘗試解析格式明顯錯誤的JWT數據）
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH) {
            // 對於無效的JWT，應該直接放行
            log.warn("當前請求中，客戶端沒有攜帶有效的JWT，將放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 從Redis中讀取此JWT對應的數據
        UserLoginInfoPO loginInfo = userCacheRepository.getLoginInfo(jwt);

        // 判斷Redis中是否存在此JWT相關數據
        if (loginInfo == null) {
            // 放行，不會向SecurityContext中存入認證信息，則相當於沒有攜帶JWT
            log.warn("在Redis中無此JWT對應的信息，將直接放行，交由後續的組件進行處理");
            filterChain.doFilter(request, response);
            return;
        }

        // 判斷此次請求，與當初登錄成功時的相關信息是否相同
        String userAgent = loginInfo.getUserAgent();
        String ip = loginInfo.getIp();
        if (!userAgent.equals(request.getHeader(HEADER_USER_AGENT))
                && !ip.equals(request.getRemoteAddr())) {
            // 本次請求的信息與當初登錄時完全不同，則視為無效的JWT
            log.warn("本次請求的信息與當初登錄時完全不同，將直接放行，交由後續的組件進行處理");
            filterChain.doFilter(request, response);
            return;
        }

        // 嘗試解析JWT數據
        log.debug("嘗試解析JWT數據……");
        response.setContentType("application/json; charset=utf-8");
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) //簽名格式
                    .parseClaimsJws(jwt)  //放入jwt代碼
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.warn("解析JWT時出現異常：ExpiredJwtException");

            //將響應結果包裝成JsonResult對象
            String message = "操作失敗，您的登入訊息已過期，請重新登入!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED, message);
            //將對象轉換為Json字符串
            String toJson = JSON.toJSONString(jsonResult);

            //響應寫出Json字符串
            PrintWriter writer = response.getWriter();
            writer.println(toJson);
            writer.close();
            return;
        } catch (SignatureException e) {
            log.warn("解析JWT時出現異常：SignatureException");

            //將響應結果包裝成JsonResult對象，再將對象轉換為Json字符串響應
            String message = "非法訪問，你的本次操作已被紀錄!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_SIGNATURE, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (MalformedJwtException e) {
            log.warn("解析JWT時出現異常：MalformedJwtException");

            //將響應結果包裝成JsonResult對象，再將對象轉換為Json字符串響應
            String message = "非法訪問，你的本次操作已被紀錄!";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_MALFORMED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (Throwable e) {
            log.warn("解析JWT時出現異常：{}", e);
            String message = "服務器同時訪問人數較多，請稍候再光臨或聯絡技術人員";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        }
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        //改由從Redis中讀取當前用戶的權限列表
        //String authoritiesJsonString = claims.get("authoritiesJsonString", String.class);

        // 檢查用戶的啟用狀態
        Integer userEnable = userCacheRepository.getEnableByUserId(id);
        if (userEnable != 1) {
            log.warn("用戶已被禁用");
            String message = "用戶已被禁用";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();

            //TODO
            //再寫兩個方法刪除狀態、刪除JWT
            //於此處一併執行，徹底停用帳號

            return;
        }

        String authoritiesJsonString = loginInfo.getAuthoritiesJsonString();

        log.debug("JWT中的用戶id = {}", id);
        log.debug("JWT中的用戶名 = {}", username);
        log.debug("從Redis中讀取當前用戶的權限列表 = {}", authoritiesJsonString);

        /**============================================
         將解析得到的用戶數據創建為Authentication對象
         =============================================*/
        //定義當事人訊息
        CurrentPrincipal principal = new CurrentPrincipal(); // 當事人
        principal.setId(id);
        principal.setUsername(username);

        //定義憑證
        Object credentials = null;   // 憑證:無須憑證

        //獲取authorities(權限列表)
        List<SimpleGrantedAuthority> authorities =
                JSON.parseArray(authoritiesJsonString, SimpleGrantedAuthority.class);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal, credentials, authorities);

        /**==============================================
         將Authentication對象存入到SecurityContext中
         ===============================================*/
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        //-------------------過濾器處理流程結束------------------------------------------------------------------------------------------------------------------

        //讓過濾器鍊繼續執行，即放行
        filterChain.doFilter(request, response);
    }
}
