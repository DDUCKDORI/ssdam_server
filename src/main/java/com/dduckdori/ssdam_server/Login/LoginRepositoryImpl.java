package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Mapper.LoginMapper;
import com.dduckdori.ssdam_server.Response.ResponseDTO;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {
    private final LoginMapper loginMapper;
    public LoginRepositoryImpl(LoginMapper loginMapper){
        this.loginMapper=loginMapper;
    }
    @Override
    public LoginDTO find_sub(AppleDTO appleDTO) {
        return loginMapper.find_sub(appleDTO);
    }

    @Override
    public int join_member(LoginDTO loginDTO) {
        return loginMapper.join_mem(loginDTO);
    }

    @Override
    public int join_member_token(LoginDTO loginDTO) {
        return loginMapper.join_mem_token(loginDTO);
    }

    @Override
    public ResponseDTO find_mem_info(LoginDTO loginDTO) {
        return loginMapper.find_mem_info(loginDTO);
    }

    @Override
    public int get_mem_id(LoginDTO loginDTO) {
        return loginMapper.get_mem_id(loginDTO);
    }


    @Override
    public int delete_send_detlsd(LogoutDTO logoutDTO) {
        return loginMapper.delete_send_detlsd(logoutDTO);
    }

    @Override
    public int delete_personal_data(LogoutDTO logoutDTO) {
        return loginMapper.delet_personal_data(logoutDTO);
    }

    @Override
    public String get_refresh_token(LogoutDTO logoutDTO) {
        return loginMapper.get_refresh_token(logoutDTO);
    }

}
