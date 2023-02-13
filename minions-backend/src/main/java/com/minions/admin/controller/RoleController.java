package com.minions.admin.controller;

import com.minions.admin.bean.Permission;
import com.minions.admin.bean.Role;
import com.minions.admin.bean.RolePermission;
import com.minions.admin.service.RolePermissionService;
import com.minions.admin.service.RoleService;
import com.minions.util.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping
    public Result<List<Role>> getAllRoles() {
        return Result.success(roleService.list());
    }

    @DeleteMapping("{roleId}")
    public Result deleteRoleById(@PathVariable Long roleId) {
        boolean remove = roleService.removeById(roleId);
        return remove ? Result.success() : Result.fail();
    }

    @PostMapping
    public Result saveRole(@RequestBody Role role) {
        boolean save = roleService.save(role);
        return save ? Result.success() : Result.fail();
    }

}
