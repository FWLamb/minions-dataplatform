package com.minions.statistic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.minions.statistic.bean.TrafficStatistic;

import java.util.Map;

public interface TrafficStatisticService extends IService<TrafficStatistic> {
    Page<TrafficStatistic> getTrafficStatistics(Page<TrafficStatistic> statisticPage, String dt, Integer recentDays);

    Map<String, Object> getPagePathCount(TrafficStatistic trafficStatistic);
}
