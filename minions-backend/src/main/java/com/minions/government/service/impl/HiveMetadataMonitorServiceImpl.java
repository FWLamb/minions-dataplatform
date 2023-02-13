package com.minions.government.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.government.bean.HiveMetadataMonitor;
import com.minions.government.mapper.HiveMetadataMonitorMapper;
import com.minions.government.service.HiveMetadataMonitorService;
import org.springframework.stereotype.Service;

@DS("mysql_monitor")
@Service
public class HiveMetadataMonitorServiceImpl
        extends ServiceImpl<HiveMetadataMonitorMapper, HiveMetadataMonitor>
        implements HiveMetadataMonitorService {
}
