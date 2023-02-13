package com.minions.scheduler.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.minions.scheduler.bean.MonitorDatabase;
import com.minions.scheduler.bean.QuartzScheduler;
import com.minions.scheduler.bean.SchedulerJobInfo;
import com.minions.scheduler.service.SchedulerService;
import com.minions.util.Result;
import org.quartz.JobKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    @Resource
    private SchedulerService schedulerService;

    /**
     * 获取可监控数据库
     */
    @GetMapping("database")
    public Result<List<MonitorDatabase>> getMonitorDatabases() {
        return Result.success(schedulerService.getMonitorDatabases());
    }

    /**
     * 根据数据库获取表名
     */
    @PostMapping("getAllTableByDatabase/{databaseName}")
    public Result<List<String>> getAllTableByDatabase(@PathVariable String databaseName) {
        return Result.success(SchedulerService.getAllTableByDatabase(databaseName));
    }

    /**
     * 根据数据库获取表名
     */
    @PostMapping("getAllFields/{databaseName}/{tableName}")
    public Result<List<String>> getAllFieldNameByTableName(@PathVariable String tableName,
                                                           @PathVariable String databaseName) {
        return Result.success(SchedulerService.getAllFieldNameByTableName(databaseName, tableName));
    }


    /**
     * 获取所有作业
     */
    @GetMapping("getAllJobs")
    public Result<List<SchedulerJobInfo>> getAllJobs() {
        return Result.success(schedulerService.list());
    }

    /**
     * 暂停作业
     */
    @PostMapping("pauseJob")
    public Result<String> pauseJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();
        scheduler.pauseJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        jobInfo.setJobStatus("已暂停");
        LambdaQueryWrapper<SchedulerJobInfo> queryWrapper = new LambdaQueryWrapper<SchedulerJobInfo>()
                .eq(SchedulerJobInfo::getJobName, jobInfo.getJobName())
                .eq(SchedulerJobInfo::getJobGroup, jobInfo.getJobGroup());
        schedulerService.update(jobInfo, queryWrapper);
        return Result.of(200, "success", "任务暂停");
    }

    /**
     * 重启作业
     */
    @PostMapping("resumeJob")
    public Result<String> resumeJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();
        scheduler.resumeJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        jobInfo.setJobStatus("运行中");
        LambdaQueryWrapper<SchedulerJobInfo> queryWrapper = new LambdaQueryWrapper<SchedulerJobInfo>()
                .eq(SchedulerJobInfo::getJobName, jobInfo.getJobName())
                .eq(SchedulerJobInfo::getJobGroup, jobInfo.getJobGroup());
        schedulerService.update(jobInfo, queryWrapper);
        return Result.of(200, "success", "任务重启");
    }

    /**
     * 删除作业
     */
    @PostMapping("deleteJob")
    public Result<String> deleteJob(@RequestBody SchedulerJobInfo jobInfo) throws Exception {
        var scheduler = QuartzScheduler.getInstance();
        scheduler.deleteJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
        schedulerService.removeById(jobInfo.getId());
        return Result.of(200, "success", "任务删除");
    }

    @PostMapping("createJob/{databaseName}/{tableName}/{fieldName}")
    public Result<String> createJob(@RequestBody SchedulerJobInfo jobInfo,
                                    @PathVariable String databaseName,
                                    @PathVariable String tableName,
                                    @PathVariable String fieldName) throws Exception {
        schedulerService.createJob(jobInfo, databaseName, tableName, fieldName);
        return Result.success("创建成功");
    }
}
