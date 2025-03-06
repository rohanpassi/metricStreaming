package com.rohanpassi.metricStreaming.dto;

import java.util.Map;

import com.rohanpassi.metricStreaming.config.OperationType;

import lombok.Data;

@Data
public class Transformation {
    private final OperationType operationType;
    private final Map<String, Object> config;
}