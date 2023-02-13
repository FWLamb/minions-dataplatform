package com.minions.government.controller;

import com.minions.government.bean.Government;
import com.minions.government.bean.HiveMetadataMonitor;
import com.minions.government.service.GovernmentService;
import com.minions.government.service.HiveMetadataMonitorService;
import com.minions.government.service.TableLineageService;
import com.minions.util.Result;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/government")
public class GovernmentController {

    @Resource
    private GovernmentService governmentService;
    @Resource
    private HiveMetadataMonitorService hiveMetadataMonitorService;

    /**
     * 获取MySQL数据质量
     */
    @GetMapping("mysqlDataMonitor")
    public Result<List<Government>> getMySQLDataMonitor() {
        return Result.success(governmentService.list());
    }

    /**
     * 获取hive元数据质量监控数据
     */
    @GetMapping("hiveMetadataScore")
    public Result<List<HiveMetadataMonitor>> getHiveMetadataScore() {
        return Result.success(hiveMetadataMonitorService.list());
    }

    /**
     * 计算血缘关系
     */
    @GetMapping("lineage")
    public String getLineage() {
        // 更新血缘图
        var edges = TableLineageService.generateEdges();
        TableLineageService.sinkToNeo4j(edges);

        // 从neo4j中查询所有边以及顶点，并绘成`Mermaid`字符串
        var driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "123456"));
        var session = driver.session();
        var graphString = session.executeRead(tx -> {
            var graph = new StringBuilder("graph LR\n");
            var result = tx.run("MATCH (n)-[r]->(m) RETURN n,r,m");
            for (var record : result.list()) {graph
                        .append("    ")
                        .append(record.get("n").get("tableName"))
                        .append(" --> ")
                        .append(record.get("m").get("tableName"))
                        .append("\n");
            }
            return graph.toString().replaceAll("\"", "");
        });
        session.close();
        driver.close();
        return graphString;
    }


}
