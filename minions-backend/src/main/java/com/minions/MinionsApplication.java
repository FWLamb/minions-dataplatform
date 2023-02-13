package com.minions;

import com.minions.scheduler.bean.QuartzScheduler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.minions.*.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MinionsApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MinionsApplication.class);
        QuartzScheduler.getInstance().start();
    }
}
