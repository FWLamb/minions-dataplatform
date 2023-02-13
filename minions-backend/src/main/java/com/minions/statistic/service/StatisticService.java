package com.minions.statistic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minions.statistic.bean.GeneralStatistic;
import com.minions.statistic.bean.TrafficStatistic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StatisticService {

    BigDecimal getGMV(String date);

    List<GeneralStatistic> getProvinceStatistic(String date);

    List<GeneralStatistic> getKeywordStatistics(String date);

    List<GeneralStatistic> getProductStatisticsGroupByCategory3(String date);

    List<GeneralStatistic> getProductStatsByTrademark(String date);

}
