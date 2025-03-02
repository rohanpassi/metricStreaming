package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.dto.Metric;

@Component
public class SumAggregator implements MetricAggregator {
    @Override
    public int aggregate(Map<String, List<Metric>> groupedMetrics) {
        return groupedMetrics.values().stream().mapToInt(group -> group.stream().mapToInt(Metric::getValue).sum()).sum();
    }
    
    @Override
    public int aggregate(List<Metric> metrics) {
        return metrics.stream().mapToInt(Metric::getValue).sum();
    }
}
