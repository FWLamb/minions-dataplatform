package com.minions.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.admin.bean.RolePermission;
import com.minions.admin.mapper.RolePermissionMapper;
import com.minions.admin.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
@DS("mysql_admin")
public class RolePermissionServiceImpl
        extends ServiceImpl<RolePermissionMapper, RolePermission>
        implements RolePermissionService {
}
