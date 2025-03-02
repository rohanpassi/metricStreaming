package com.rohanpassi.metricStreaming.transformations.filter;

import com.rohanpassi.metricStreaming.dto.Metric;

public interface MetricFilter {
    boolean apply(Metric metric);
}
