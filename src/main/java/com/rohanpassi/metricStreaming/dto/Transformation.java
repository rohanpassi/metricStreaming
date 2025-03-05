package com.rohanpassi.metricStreaming.dto;

import java.util.Map;
import lombok.Data;

@Data
public class Transformation {
    private final OperationType operationType;
    private final Map<String, Object> config;
}