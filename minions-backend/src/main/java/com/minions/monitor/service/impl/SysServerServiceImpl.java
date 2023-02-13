package com.minions.monitor.service.impl;


import com.minions.monitor.bean.system.Server;
import com.minions.monitor.service.SysServerService;
import org.springframework.stereotype.Service;

@Service
public class SysServerServiceImpl implements SysServerService {
    @Override
    public Server getServerInfo() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return server;
    }
}
