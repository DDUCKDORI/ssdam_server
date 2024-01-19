package com.dduckdori.ssdam_server.User;

public interface UserService {

   int find_max_id(String code);
   void delete_answer_history(UserDTO userDTO);
   void delete_user_answer(UserDTO userDTO);
   void join_with_code(UserDTO userDTO);
}
