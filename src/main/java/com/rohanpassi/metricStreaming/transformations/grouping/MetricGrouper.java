package com.rohanpassi.metricStreaming.transformations.grouping;

import java.util.List;
import java.util.Map;

import com.rohanpassi.metricStreaming.dto.Metric;

public interface MetricGrouper {
    Map<String, List<Metric>> group(List<Metric> metrics);
}