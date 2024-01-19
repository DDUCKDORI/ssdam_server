package com.dduckdori.ssdam_server.User;

import java.util.Optional;

public interface UserService {

  Integer find_max_id(String code);
   void delete_answer_history(UserDTO userDTO);
   void delete_user_answer(UserDTO userDTO);
   void join_with_code(UserDTO userDTO);
}
