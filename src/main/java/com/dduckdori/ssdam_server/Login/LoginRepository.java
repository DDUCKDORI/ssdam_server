package com.dduckdori.ssdam_server.Login;

public interface LoginRepository {
     LoginDTO find_sub(AppleDTO appleDTO);
     int join_member(LoginDTO loginDTO);
     int join_member_token(LoginDTO loginDTO);
     ResponseDTO find_mem_info(LoginDTO loginDTO);
}
