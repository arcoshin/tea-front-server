package cn.tedu.tea.front.server.test;

import cn.tedu.tea.front.server.common.security.CurrentPrincipal;
import cn.tedu.tea.front.server.common.web.JsonResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XJX@tedu.cn
 * @version 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public JsonResult test(@AuthenticationPrincipal CurrentPrincipal currentPrincipal) {
        return JsonResult.ok(currentPrincipal);
    }
}
