package com.minions.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.admin.bean.User;
import com.minions.admin.mapper.UserMapper;
import com.minions.admin.service.UserService;
import com.minions.util.JwtUtil;
import com.minions.util.MD5Util;
import com.minions.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DS("mysql_admin")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<String> login(User loginUser) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, loginUser.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        if (!MD5Util.encrypt(loginUser.getPassword()).equals(user.getPassword())) {
            return Result.fail("密码错误");
        }
        String token = JwtUtil.createToken(loginUser.getId(), loginUser.getUsername());
        return Result.success(token);
    }

    @Override
    public List<String> getPermissionByUserId(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return null;
        }
        return userMapper.getPermissionByUserId(user.getId());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userMapper.selectList(Wrappers.emptyWrapper());
        return users.stream().map(user -> {
            user.setRoleName(userMapper.getUserRoleName(user.getId()));
            return user;
        }).collect(Collectors.toList());
    }
}
