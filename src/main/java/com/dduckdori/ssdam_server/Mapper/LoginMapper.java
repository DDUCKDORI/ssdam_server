package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.Login.AppleDTO;
import com.dduckdori.ssdam_server.Login.LoginDTO;
import com.dduckdori.ssdam_server.Login.ResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    LoginDTO find_sub(AppleDTO appleDTO);
    int join_mem(LoginDTO loginDTO);
    int join_mem_token(LoginDTO loginDTO);
    ResponseDTO find_mem_info(LoginDTO loginDTO);

    int get_mem_id(LoginDTO loginDTO);
}
