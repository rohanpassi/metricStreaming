package com.rohanpassi.metricStreaming.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rohanpassi.metricStreaming.aggregator.MetricAggregator;
import com.rohanpassi.metricStreaming.filter.MetricFilter;
import com.rohanpassi.metricStreaming.grouping.MetricGrouper;
import com.rohanpassi.metricStreaming.model.Metric;

@Service
public class MetricTransformationService {
    public List<Metric> applyFilters(List<Metric> metrics, List<MetricFilter> filters) {
        for (MetricFilter filter : filters) {
            metrics = metrics.stream().filter(filter::apply).collect(Collectors.toList());
        }
        return metrics;
    }

    public Map<String, List<Metric>> applyGrouping(List<Metric> metrics, MetricGrouper grouper) {
        return grouper.group(metrics);
    }

    public Map<String, Integer> applyAggregation(Map<String, List<Metric>> groupedMetrics, MetricAggregator aggregator) {
        return groupedMetrics.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> aggregator.aggregate(e.getValue())));
    }
}
