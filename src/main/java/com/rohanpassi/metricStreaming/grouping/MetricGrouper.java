package com.rohanpassi.metricStreaming.grouping;

import java.util.List;
import java.util.Map;

import com.rohanpassi.metricStreaming.model.Metric;

public interface MetricGrouper {
    Map<String, List<Metric>> group(List<Metric> metrics);
}