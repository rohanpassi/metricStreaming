package com.rohanpassi.metricStreaming.filter;

import com.rohanpassi.metricStreaming.model.Metric;

public interface MetricFilter {
    boolean apply(Metric metric);
}
