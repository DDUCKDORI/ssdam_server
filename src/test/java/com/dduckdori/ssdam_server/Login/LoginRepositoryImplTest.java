package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Mapper.LoginMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginRepositoryImplTest {

    private static LoginMapper loginMapper;

    @Autowired
    public LoginRepositoryImplTest(LoginMapper loginMapper){
        this.loginMapper=loginMapper;
    }

    @Test
    public void sub_succes(){
        AppleDTO appleDTO = new AppleDTO();
        appleDTO.setSub("sdlknbal");
        LoginDTO loginDTO = loginMapper.find_sub(appleDTO);
        Assertions.assertThat(loginDTO).isNull();
    }
    @Test
    public void get_mem_id(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setInvite_cd("A");
        int result = loginMapper.get_mem_id(loginDTO);
        System.out.println("result = " + result);
    }
    @Test
    @Transactional
    public void delete_personal_data(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setInvite_cd("WZFP675");
        loginDTO.setMem_id(2);
        int result = loginMapper.delet_personal_data(loginDTO);
        System.out.println("result = " + result);
    }
}