package com.minions.scheduler.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.scheduler.bean.MonitorDatabase;
import com.minions.scheduler.bean.QuartzScheduler;
import com.minions.scheduler.bean.SchedulerJobInfo;
import com.minions.scheduler.job.MySQLMonitorJob;
import com.minions.scheduler.job.SimpleJob;
import com.minions.scheduler.mapper.SchedulerMapper;
import com.minions.scheduler.service.SchedulerService;
import com.minions.util.Result;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@DS("mysql_schedule")
@Service
public class SchedulerServiceImpl extends ServiceImpl<SchedulerMapper, SchedulerJobInfo>
        implements SchedulerService {
    @Resource
    private SchedulerMapper schedulerMapper;

    private static int triggerCount = 0;

    @Override
    public Boolean createJob(SchedulerJobInfo jobInfo, String databaseName, String tableName, String fieldName) throws Exception {
        Scheduler scheduler = QuartzScheduler.getInstance();
        // 判断是什么任务，然后新建对应的任务
        JobDetail jobDetail;
        if ("简单任务(测试)".equals(jobInfo.getJobType())) {
            jobDetail = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
                    .build();
        } else {
            var jobData = new JobDataMap();
            jobData.put("databaseName", databaseName);
            jobData.put("tableName", tableName);
            jobData.put("fieldName", fieldName);
            jobDetail = JobBuilder
                    .newJob(MySQLMonitorJob.class)
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
                    .setJobData(jobData)
                    .build();
        }
        // 新建触发器
        Trigger trigger;
        if (jobInfo.getCronJob()) {
            trigger = newTrigger()
                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
                    .withSchedule(cronSchedule(jobInfo.getCronExpression()))
                    .build();
        } else {
            trigger = newTrigger()
                    .withIdentity("trigger" + triggerCount, jobInfo.getJobGroup())
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(jobInfo.getRepeatTime())
                            .repeatForever())
                    .build();
        }
        triggerCount++;
        // 调度任务
        scheduler.scheduleJob(jobDetail, trigger);

        // 将任务存入MySQL
        jobInfo.setJobStatus("运行中");
        return this.save(jobInfo);
    }

    @Override
    public List<MonitorDatabase> getMonitorDatabases() {
        return schedulerMapper.getMonitorDatabases();
    }
}
