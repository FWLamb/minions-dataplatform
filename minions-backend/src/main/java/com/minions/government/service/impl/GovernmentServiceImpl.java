package com.minions.government.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minions.government.bean.Government;
import com.minions.government.mapper.GovernmentMapper;
import com.minions.government.service.GovernmentService;
import org.springframework.stereotype.Service;

@Service
@DS("mysql_government")
public class GovernmentServiceImpl extends ServiceImpl<GovernmentMapper, Government> implements GovernmentService {
}
