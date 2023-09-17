package hxj.boot.biz.system.service;

import hxj.boot.biz.system.controller.vo.AuthLoginReqVO;
import hxj.boot.biz.system.controller.vo.AuthLoginRespVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author huangxj
 * @since 2023/9/17
 */
// 可以考虑说明下spring是如何判断一个类是否需要被代理增强的
// MethodValidationPostProcessor
// ValidationAutoConfiguration
// org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor.isEligible(java.lang.Object, java.lang.String)
@Validated // 标记后这个接口的实现类就会被代理增强 FilteredMethodValidationPostProcessor
public interface AdminAuthService {
    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(@Valid AuthLoginReqVO reqVO);
}
