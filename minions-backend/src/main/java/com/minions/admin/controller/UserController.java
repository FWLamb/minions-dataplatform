package com.minions.admin.controller;

import com.minions.admin.bean.User;
import com.minions.admin.bean.UserRole;
import com.minions.admin.service.UserRoleService;
import com.minions.admin.service.UserService;
import com.minions.util.MD5Util;
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
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping
    public Result<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return Result.success(userList);
    }

    /**
     * 根据id获取用户
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId}")
    public Result<User> getUserById(@PathVariable Long userId) {
        return Result.success(userService.getById(userId));
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public Result saveUser(@RequestBody User user) {
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        boolean save = userService.save(user);
        return save ? Result.success() : Result.fail();
    }

    /**
     * 根据id删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("deleteUser/{userId}")
    public Result deleteUser(@PathVariable Long userId) {
        boolean remove = userService.removeById(userId);
        return remove ? Result.success() : Result.fail();
    }

    @GetMapping("getRoleIdByUserId/{userId}")
    public Result<Long> getRoleIdByUserId(@PathVariable Long userId) {
        Long roleId = userRoleService.getRoleIdByUserId(userId);
        return Result.success(roleId);
    }


    /**
     * 为用户分配角色
     *
     * @param userRole
     * @return
     */
    @PostMapping("assignRoleToUser")
    public Result assignRoleToUser(@RequestBody UserRole userRole) {
        boolean save = userRoleService.save(userRole);
        return save ? Result.success() : Result.fail();
    }

}
