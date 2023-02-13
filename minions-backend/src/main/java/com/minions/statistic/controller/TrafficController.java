package com.minions.statistic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minions.statistic.bean.TrafficStatistic;
import com.minions.statistic.service.StatisticService;
import com.minions.statistic.service.TrafficStatisticService;
import com.minions.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/statistic/visit")
public class TrafficController {

    @Resource
    private StatisticService statisticService;
    @Resource
    private TrafficStatisticService trafficStatisticService;

    @GetMapping("getTrafficStats/{page}/{limit}")
    public Result<Page<TrafficStatistic>> getTrafficStats(@PathVariable Integer page,
                                                          @PathVariable Integer limit,
                                                          TrafficStatistic trafficStatistic) {

        Page<TrafficStatistic> statisticPage = new Page<>(page, limit);
        Page<TrafficStatistic> statistics = trafficStatisticService.getTrafficStatistics(statisticPage,
                trafficStatistic.getDt(),
                trafficStatistic.getRecentDays());
        return Result.success(statistics);
    }

    /**
     * 用户路径分析
     */
    @GetMapping("getPagePath")
    public Result<Map<String, Object>> getPagePath(TrafficStatistic trafficStatistic) {
        return Result.success(trafficStatisticService.getPagePathCount(trafficStatistic));
    }
}
