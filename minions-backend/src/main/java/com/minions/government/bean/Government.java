package com.minions.government.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@TableName("mysql_data_monitor")
public class Government {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String databaseName;
    private String tableName;
    private String fieldName;
    private Double fieldNullRate;

    public Government() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getFieldNullRate() {
        return fieldNullRate;
    }

    public void setFieldNullRate(Double fieldNullRate) {
        this.fieldNullRate = fieldNullRate;
    }
}
