package com.dduckdori.ssdam_server.User;

import com.dduckdori.ssdam_server.Mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) { this.userMapper = userMapper; }

    @Override
    public Integer find_max_id(String code) {
        Integer result = userMapper.find_max_id(code);
        if (result == null) {
            return -1;
        }
        return userMapper.find_max_id(code);
    }

    @Override
    public void delete_answer_history(UserDTO userDTO) {
        userMapper.delete_answer_history(userDTO);
    }
    @Override
    public void delete_user_answer(UserDTO userDTO) {
        userMapper.delete_user_answer(userDTO);
    }

    @Override
    public void join_with_code(UserDTO userDTO) {
        userMapper.join_with_code(userDTO);
    }
}
