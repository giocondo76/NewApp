package com.nc.entity;

public class ConditionStatus {

    private Condition condition;
    private String status;

    public ConditionStatus() {
    }

    public ConditionStatus(Condition condition, String status) {
        this.condition = condition;
        this.status = status;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
