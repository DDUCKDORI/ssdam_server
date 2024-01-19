package com.dduckdori.ssdam_server.Mapper;

import com.dduckdori.ssdam_server.User.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int find_max_id(String code);
    void delete_answer_history(UserDTO userDTO);
    void delete_user_answer(UserDTO userDTO);
    void join_with_code(UserDTO userDTO);

}
