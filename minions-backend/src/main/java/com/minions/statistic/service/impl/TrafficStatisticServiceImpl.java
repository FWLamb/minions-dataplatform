package com.minions.statistic.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.statistic.bean.PagePath;
import com.minions.statistic.bean.TrafficStatistic;
import com.minions.statistic.mapper.PagePathMapper;
import com.minions.statistic.mapper.TrafficStatisticMapper;
import com.minions.statistic.service.TrafficStatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@DS("mysql_report")
@Service
public class TrafficStatisticServiceImpl
        extends ServiceImpl<TrafficStatisticMapper, TrafficStatistic>
        implements TrafficStatisticService {

    @Resource
    private TrafficStatisticMapper trafficStatisticMapper;
    @Resource
    private PagePathMapper pagePathMapper;

    @Override
    public Page<TrafficStatistic> getTrafficStatistics(Page<TrafficStatistic> statisticPage,
                                                       String dt,
                                                       Integer recentDays) {
        LambdaQueryWrapper<TrafficStatistic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrafficStatistic::getDt, dt)
                .eq(TrafficStatistic::getRecentDays, recentDays);

        return trafficStatisticMapper.selectPage(statisticPage, queryWrapper);
    }

    @Override
    public Map<String, Object> getPagePathCount(TrafficStatistic trafficStatistic) {
        LambdaQueryWrapper<PagePath> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PagePath::getDt, trafficStatistic.getDt())
                .eq(PagePath::getRecentDays, trafficStatistic.getRecentDays())
                .select(PagePath::getSource, PagePath::getTarget, PagePath::getValue);
        List<PagePath> pagePathList = pagePathMapper.selectList(queryWrapper);
        var nodeSet = new HashSet<String>();
        List<Map<String, String>> nodeMapList = new ArrayList<>();
        for (PagePath pagePath : pagePathList) {
            String source = pagePath.getSource();
            String target = pagePath.getTarget();
            nodeSet.add(source);
            nodeSet.add(target);
        }
        for (var nodeName : nodeSet) {
            var nodeMap = new HashMap<String, String>();
            nodeMap.put("name", nodeName);
            nodeMapList.add(nodeMap);
        }

        HashMap<String, Object> rsMap = new HashMap<>();
        rsMap.put("nodeData", nodeMapList);
        rsMap.put("linksData", pagePathList);
        return rsMap;
    }
}
