package com.minions.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minions.admin.bean.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<String> getPermissionByUserId(Long userId);

    String getUserRoleName(Long id);
}
