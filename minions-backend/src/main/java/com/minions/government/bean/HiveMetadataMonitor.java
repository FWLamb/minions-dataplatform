package com.minions.government.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class HiveMetadataMonitor {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    @TableField(exist = false)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long tableId;
    private String tableName;
    private String databaseName;
    private Boolean hasTableComment;
    private Integer fieldsNumber;
    private Integer missingCommentFieldsNumber;
    private Boolean hasTechnicalOwner;
    private Boolean hasBusinessOwner;
    private Boolean hasOutputLastSevenDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Boolean getHasTableComment() {
        return hasTableComment;
    }

    public void setHasTableComment(Boolean hasTableComment) {
        this.hasTableComment = hasTableComment;
    }

    public Integer getFieldsNumber() {
        return fieldsNumber;
    }

    public void setFieldsNumber(Integer fieldsNumber) {
        this.fieldsNumber = fieldsNumber;
    }

    public Integer getMissingCommentFieldsNumber() {
        return missingCommentFieldsNumber;
    }

    public void setMissingCommentFieldsNumber(Integer missingCommentFieldsNumber) {
        this.missingCommentFieldsNumber = missingCommentFieldsNumber;
    }

    public Boolean getHasTechnicalOwner() {
        return hasTechnicalOwner;
    }

    public void setHasTechnicalOwner(Boolean hasTechnicalOwner) {
        this.hasTechnicalOwner = hasTechnicalOwner;
    }

    public Boolean getHasBusinessOwner() {
        return hasBusinessOwner;
    }

    public void setHasBusinessOwner(Boolean hasBusinessOwner) {
        this.hasBusinessOwner = hasBusinessOwner;
    }

    public Boolean getHasOutputLastSevenDay() {
        return hasOutputLastSevenDays;
    }

    public void setHasOutputLastSevenDay(Boolean hasOutputLastSevenDays) {
        this.hasOutputLastSevenDays = hasOutputLastSevenDays;
    }
}
