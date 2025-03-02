package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.List;
import java.util.Map;

import com.rohanpassi.metricStreaming.dto.Metric;

public interface MetricAggregator {
    int aggregate(Map<String, List<Metric>> groupedMetrics);
    int aggregate(List<Metric> metrics);
}
