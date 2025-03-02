package com.rohanpassi.metricStreaming.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Transformation {
    private OperationType operationType;
    private List<Map<String, Object>> filters;
    private Map<String, Object> grouper;
    private Map<String, Object> aggregator;
    private Object additionalParams;
}