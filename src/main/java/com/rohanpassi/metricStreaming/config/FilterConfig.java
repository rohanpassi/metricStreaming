package com.rohanpassi.metricStreaming.config;

import lombok.Data;

@Data
public abstract class FilterConfig {
    private final FilterType filterType;
    private final FilterTarget filterTarget;
}