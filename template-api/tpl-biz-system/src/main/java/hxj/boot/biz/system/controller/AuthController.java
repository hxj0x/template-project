package hxj.boot.biz.system.controller;

import hxj.boot.biz.system.controller.vo.AuthLoginReqVO;
import hxj.boot.biz.system.controller.vo.AuthLoginRespVO;
import hxj.boot.biz.system.framework.CommonResult;
import hxj.boot.biz.system.service.AdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huangxj
 * @since 2023/9/16
 */
@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
public class AuthController {


    @Resource
    private AdminAuthService authService;

    @Operation(summary = "使用账号密码登录")
    @PostMapping("/login")
    public CommonResult<AuthLoginRespVO> login(@RequestBody AuthLoginReqVO reqVO) {
        return CommonResult.success(authService.login(reqVO));
    }
}
