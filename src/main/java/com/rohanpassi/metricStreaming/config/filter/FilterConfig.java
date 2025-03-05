package com.rohanpassi.metricStreaming.config.filter;

import lombok.Data;

@Data
public abstract class FilterConfig {
    private final FilterType filterType;
    private final FilterTarget filterTarget;
}