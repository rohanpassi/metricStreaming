package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.dto.Metric;

@Component
public class MaxAggregator implements MetricAggregator {

    @Override
    public Map<String, List<Metric>> aggregate(Map<String, List<Metric>> groupedMetrics) {
        return groupedMetrics.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> aggregate(entry.getValue())
                ));
    }

    @Override
    public List<Metric> aggregate(List<Metric> metrics) {
        if (metrics == null || metrics.isEmpty()) {
            return List.of();
        }

        Optional<Metric> maxMetric = metrics.stream()
                .max((m1, m2) -> Double.compare(m1.getValue(), m2.getValue()));
        
        List<Metric> result = new ArrayList<>();
        maxMetric.ifPresent(result::add);
        return result;
    }
}