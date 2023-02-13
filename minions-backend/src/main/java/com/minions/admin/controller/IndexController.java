package com.minions.admin.controller;

import com.minions.admin.bean.User;
import com.minions.admin.bean.UserLoginInfo;
import com.minions.admin.service.UserService;
import com.minions.util.JwtUtil;
import com.minions.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/index")
public class IndexController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    public Result<String> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("logout")
    public Result<String> logout() {
        return Result.success("登出成功");
    }

    @GetMapping("userInfo")
    public Result<UserLoginInfo> userInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userName = JwtUtil.getUserName(token);
        if (userName != null) {
            List<String> permissions = userService.getPermissionByUserId(userName);
            UserLoginInfo userLoginInfo = new UserLoginInfo();
            userLoginInfo.setUsername(JwtUtil.getUserName(token));
            userLoginInfo.getRoutes().addAll(permissions);
            return Result.success(userLoginInfo);
        }
        return Result.fail();
    }

}
