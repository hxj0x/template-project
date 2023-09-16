package hxj.boot.biz.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangxj
 * @since 2023/9/16
 */
@RestController
@RequestMapping("/system/auth")
public class AuthController {

    @PostMapping("/login")
    public Object login() {
        return "login";
    }
}
