package hxj.boot.biz.system.service.impl;

import hxj.boot.biz.system.controller.vo.AuthLoginReqVO;
import hxj.boot.biz.system.controller.vo.AuthLoginRespVO;
import hxj.boot.biz.system.service.AdminAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author huangxj
 * @since 2023/9/17
 */
@Service
@Slf4j
public class AdminAuthServiceImpl implements AdminAuthService {

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        return null;
    }
}
