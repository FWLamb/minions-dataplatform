package com.minions.statistic.mapper;

import com.minions.statistic.bean.GeneralStatistic;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticMapper {
    BigDecimal getGMV(String date);

    List<GeneralStatistic> getProvinceStatistic(String date);

    List<GeneralStatistic> getKeywordStatistics(String date);

    List<GeneralStatistic> getProductStatisticsGroupByCategory3(String date);

    List<GeneralStatistic> getProductStatsByTrademark(String date);
}
