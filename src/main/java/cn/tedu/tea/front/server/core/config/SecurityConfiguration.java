package cn.tedu.tea.front.server.core.config;

import cn.tedu.tea.front.server.common.web.JsonResult;
import cn.tedu.tea.front.server.common.web.ServiceCode;
import cn.tedu.tea.front.server.core.filter.JwtAuthorizationFilter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Spring Security的配置類
 *
 * @author XJX@tedu.cn
 * @version 1.0
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //開啟基於安全(權限)的安全檢查
//@EnableWebSecurity(debug = true) //開啟調適模式，在控制台將出現很多日誌，在生產環境中不宜開啟
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public SecurityConfiguration() {
        log.debug("創建配置類對象：SecurityConfiguration");
    }

    /**
     * 自動裝配JWT過濾器
     */
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); // 不要調用父類的同名方法，許多默認的效果都是父類方法配置的

        // 配置Security框架不使用Session
        // SessionCreationPolicy.NEVER 從不主動創建Session，但是如果Session存在會自動使用
        // SessionCreationPolicy.STATELESS 無狀態，無論是否存在Session都不使用
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 將自定義解析JWT的過濾器添加到Security框架的過濾器中
        // 必須添加在檢查SecurityContext的Authentication之前，具體位置無嚴格要求
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // 允許跨域訪問，否則前端無法訪問，本質上就是啟用了Security的CorsFilter
        http.cors();

        // 處理未登入時訪問需要認證的資源時的所有403響應
        http.exceptionHandling().authenticationEntryPoint(//以下使用匿名內部類
                new AuthenticationEntryPoint() {//認證入口點
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

                        log.warn("{}", e);
                        response.setContentType("application/json; charset=utf-8");

                        /**
                         * 將響應結果包裝成JsonResult對象
                         */
                        String message = "操作失敗，您尚未登入!";
                        JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED, message);

                        /**
                         * 將對象轉換為Json字符串
                         */
                        String toJson = JSON.toJSONString(jsonResult);

                        /**
                         * 響應寫出Json字符串
                         */
                        PrintWriter writer = response.getWriter();
                        writer.println(toJson);
                        writer.close();

                    }
                }
        );

        // 白名單
        String[] urls = {
                "/favicon.ico",
                "/doc.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-resources",
                "/v2/api-docs",
                "/resources/**",
                "/mappers/account/users/login",
        };

        // 停用"跨域攻擊防禦對策"
        http.csrf().disable();

        // 配置請求授權
        // 如果某個請求被多次配置，按照“第一匹配原則”處理
        // 應該將精確的配置寫在前面，將較模糊的配置寫在後面
        http.authorizeRequests()
                .mvcMatchers(urls) // 匹配某些請求
                .permitAll() // 許可，即不需要通過認證就可以訪問
                .anyRequest() // 任何請求，從執行效果來看，也可以視為：除了以上配置過的以外的其它請求
                .authenticated(); // 需要通過認證才可以訪問

        // 是否調用以下方法，將決定是否啟用默認的登錄頁面
        // 當未通過認證時，如果有登錄頁，則自動跳轉到登錄，如果沒有登錄頁，則直接響應403
//        http.formLogin();
    }
}