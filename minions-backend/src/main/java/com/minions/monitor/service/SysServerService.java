package com.minions.monitor.service;


import com.minions.monitor.bean.system.Server;

public interface SysServerService {

    /**
     * 获取系统服务信息
     *
     * @return 系统服务信息
     */
    Server getServerInfo();
}
