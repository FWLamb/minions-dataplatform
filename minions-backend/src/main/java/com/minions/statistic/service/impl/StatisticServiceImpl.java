package com.minions.statistic.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minions.statistic.bean.GeneralStatistic;
import com.minions.statistic.bean.TrafficStatistic;
import com.minions.statistic.mapper.StatisticMapper;
import com.minions.statistic.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@DS("clickhouse")
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private StatisticMapper statisticMapper;

    /**
     * 获取一天的总交易额
     *
     * @param date
     * @return
     */
    @Override
    public BigDecimal getGMV(String date) {
        return statisticMapper.getGMV(date);
    }

    @Override
    public List<GeneralStatistic> getProvinceStatistic(String date) {
        return statisticMapper.getProvinceStatistic(date);
    }

    @Override
    public List<GeneralStatistic> getKeywordStatistics(String date) {
        return statisticMapper.getKeywordStatistics(date);
    }

    @Override
    public List<GeneralStatistic> getProductStatisticsGroupByCategory3(String date) {
        return statisticMapper.getProductStatisticsGroupByCategory3(date);
    }

    /**
     * 统计某天不同品牌商品交易额排名
     */
    public List<GeneralStatistic> getProductStatsByTrademark(String date) {
        return statisticMapper.getProductStatsByTrademark(date);
    }

}
