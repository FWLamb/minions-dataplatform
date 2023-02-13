package com.minions.admin.controller;

import com.minions.admin.bean.RolePermission;
import com.minions.admin.service.RolePermissionService;
import com.minions.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/permission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    @GetMapping("{roleId}")
    public Result<String> getPermissionById(@PathVariable Long roleId) {
        RolePermission rolePermission = rolePermissionService.getById(roleId);
        return Result.success(rolePermission.getPermissionIds());
    }

    @PostMapping
    public Result assignPermissionsToRole(@RequestBody RolePermission rolePermission) {
        boolean save = rolePermissionService.save(rolePermission);
        return save ? Result.success() : Result.fail();
    }


}
