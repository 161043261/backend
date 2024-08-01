package com.bronya.projdemo.service.impl;

import com.bronya.projdemo.mapper.UserMapper;
import com.bronya.projdemo.pojo.User;
import com.bronya.projdemo.service.UserService;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        String encryption = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(encryption);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateUser(user);
    }

    @Override
    public int updateAvatar(String avatarUrl) {
        Object claims = ThreadLocalUtil.get();
        assert claims instanceof Map<?, ?>; // assert
        Map<?, ?> claimsMap = (Map<?, ?>) claims;
        return userMapper.updateAvatar(avatarUrl, (int) claimsMap.get("id"));
    }

    @Override
    public int updatePwd(int id, String newPwd) {
        String encryption = DigestUtils.md5DigestAsHex(newPwd.getBytes());
        return userMapper.updatePwd(id, encryption);
    }


}
