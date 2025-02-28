package com.rohanpassi.metricStreaming.aggregator;

import java.util.List;

import com.rohanpassi.metricStreaming.model.Metric;

public interface MetricAggregator {
    int aggregate(List<Metric> metrics);
}
