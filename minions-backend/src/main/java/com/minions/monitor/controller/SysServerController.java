package com.minions.monitor.controller;

import com.minions.monitor.bean.system.Server;
import com.minions.monitor.service.SysServerService;
import com.minions.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/monitor/server")
public class SysServerController {

    @Resource
    private SysServerService sysServerService;

    @GetMapping
    public Result<Server> getInfo() {
        Server serverInfo = sysServerService.getServerInfo();
        return Result.success(serverInfo);
    }
}
