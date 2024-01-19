package com.dduckdori.ssdam_server.User;

import com.dduckdori.ssdam_server.Response.JoinResponse;

public interface UserRepository {

    int find_max_id(String code);
    void delete_answer_history(UserDTO userDTO);
    void delete_user_answer(UserDTO userDTO);
    void join_with_code(UserDTO userDTO);
}
