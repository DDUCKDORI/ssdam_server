package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Exception.UnAuthroizedAccessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AppleLoginServiceTest {
    private LoginService loginService;
    @Autowired
    public AppleLoginServiceTest(LoginService loginService){
        this.loginService=loginService;
    }

    @Test
    @Transactional
    void joinMember(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setExists_yn("yes");
        loginDTO.setInvite_cd("");
        loginDTO.setAccess_token("a6e7eed98d39f43e78d04d28ad1cd3669.0.ryzw.9vtAbde6liZNEV04bg2YUg");
        loginDTO.setNick_nm("ㅂㅂ");
        loginDTO.setMem_sub("000896.84fab0294d69429eac7f0e1e23e5328f.0916");
        loginDTO.setRefresh_token("r34409c32ec664301b93921c797ec1421.0.ryzw.vFR65tlewPTpuILTGZRhgw");
        loginDTO.setFm_dvcd("01");
        loginService.joinMember(loginDTO);
    }
}