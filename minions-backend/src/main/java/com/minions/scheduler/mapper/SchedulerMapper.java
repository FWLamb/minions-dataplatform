package com.minions.scheduler.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minions.scheduler.bean.MonitorDatabase;
import com.minions.scheduler.bean.SchedulerJobInfo;
import com.minions.util.Result;

import java.util.List;

public interface SchedulerMapper extends BaseMapper<SchedulerJobInfo> {

    List<MonitorDatabase> getMonitorDatabases();
}
