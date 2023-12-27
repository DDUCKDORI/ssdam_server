package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Mapper.LoginMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}