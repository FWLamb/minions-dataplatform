package com.minions.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minions.admin.bean.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {
    Long getRoleIdByUserId(Long userId);
}
