package com.dduckdori.ssdam_server.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public int find_max_id(String code) {
     return userRepository.find_max_id(code);
    }

    @Override
    public void delete_answer_history(UserDTO userDTO) {
        userRepository.delete_answer_history(userDTO);
    }
    @Override
    public void delete_user_answer(UserDTO userDTO) {
        userRepository.delete_user_answer(userDTO);
    }

    @Override
    public void join_with_code(UserDTO userDTO) {
        userRepository.join_with_code(userDTO);
    }
}
