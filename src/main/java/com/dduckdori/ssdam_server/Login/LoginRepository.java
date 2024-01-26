package com.dduckdori.ssdam_server.Login;

import com.dduckdori.ssdam_server.Response.ResponseDTO;

public interface LoginRepository {
     LoginDTO find_sub(AppleDTO appleDTO);
     int join_member(LoginDTO loginDTO);
     int join_member_token(LoginDTO loginDTO);
     ResponseDTO find_mem_info(LoginDTO loginDTO);
    int get_mem_id(LoginDTO loginDTO);

    int delete_send_detlsd(LogoutDTO logoutDTO);

    int delete_personal_data(LogoutDTO logoutDTO);

    String get_refresh_token(LogoutDTO logoutDTO);
}
