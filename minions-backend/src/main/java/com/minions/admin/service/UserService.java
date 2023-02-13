package com.minions.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minions.admin.bean.User;
import com.minions.util.Result;

import java.util.List;

public interface UserService extends IService<User> {
    Result<String> login(User user);

    List<String> getPermissionByUserId(String userId);

    List<User> getAllUsers();

}
