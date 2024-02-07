package org.databasetest.service;

import org.databasetest.mapper.UserMapper;
import org.databasetest.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserInfo findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void registerUser(UserInfo userInfo) {
        userMapper.insert(userInfo);
    }

    public List<UserInfo> findAllUserInfo() {
        List<UserInfo> allUserInfo = userMapper.findAllUserInfo();
        return allUserInfo;
    }
}
