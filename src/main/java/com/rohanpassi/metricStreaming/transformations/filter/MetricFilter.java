package com.rohanpassi.metricStreaming.transformations.filter;

import com.rohanpassi.metricStreaming.model.Metric;

public interface MetricFilter {
    boolean apply(Metric metric);
}
