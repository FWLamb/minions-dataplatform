package com.minions.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.admin.bean.Role;
import com.minions.admin.mapper.RoleMapper;
import com.minions.admin.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@DS("mysql_admin")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
