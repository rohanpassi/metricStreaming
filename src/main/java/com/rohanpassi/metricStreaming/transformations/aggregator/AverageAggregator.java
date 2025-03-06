package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.dto.Metric;

@Component
public class AverageAggregator implements MetricAggregator {

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

        Double average = metrics.stream().mapToDouble(Metric::getValue).average().orElse(0.0);
        Metric aggregatedMetric = new Metric(average, metrics.get(0).getTimestamp());

        List<Metric> result = new ArrayList<>();
        result.add(aggregatedMetric);
        return result;
    }
}