package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.List;
import java.util.Map;

import com.rohanpassi.metricStreaming.dto.Metric;

public interface MetricAggregator {
    Map<String, List<Metric>> aggregate(Map<String, List<Metric>> metrics);
    List<Metric> aggregate(List<Metric> metrics);
}
