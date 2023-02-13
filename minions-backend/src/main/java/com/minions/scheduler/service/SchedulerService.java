package com.minions.scheduler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minions.scheduler.bean.MonitorDatabase;
import com.minions.scheduler.bean.SchedulerJobInfo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SchedulerService extends IService<SchedulerJobInfo> {

    static List<String> getAllFieldNameByTableName(String databaseName, String tableName) {
        try {
            var connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false",
                    "root",
                    "123456"
            );
            var createTableStatement = connection.prepareStatement(
                    "select COLUMN_NAME from information_schema.COLUMNS where table_name = '" + tableName + "'"
            );
            ArrayList<String> arrayList = new ArrayList<>();
            ResultSet resultSet = createTableStatement.executeQuery();
            while (resultSet.next()) {
                arrayList.add(resultSet.getString("COLUMN_NAME"));
            }
            createTableStatement.close();
            connection.close();
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    Boolean createJob(SchedulerJobInfo jobInfo, String databaseName, String tableName, String fieldName) throws Exception;

    List<MonitorDatabase> getMonitorDatabases();

    static List<String> getAllTableByDatabase(String databaseName) {
        try {
            var connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false",
                    "root",
                    "123456"
            );
            var createTableStatement = connection.prepareStatement(
                    "select table_name from information_schema.tables where table_schema=" + "'" + databaseName + "'"
            );
            ArrayList<String> arrayList = new ArrayList<>();
            ResultSet resultSet = createTableStatement.executeQuery();
            while (resultSet.next()) {
                arrayList.add(resultSet.getString("table_name"));
            }
            createTableStatement.close();
            connection.close();
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
