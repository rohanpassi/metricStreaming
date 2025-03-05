package com.rohanpassi.metricStreaming.transformations.grouping;

import com.rohanpassi.metricStreaming.dto.Metric;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HourGrouper implements MetricGrouper {

    @Override
    public Map<String, List<Metric>> group(List<Metric> metrics) {
        if (metrics == null || metrics.isEmpty()) {
            return Map.of();
        }
        return metrics.stream().collect(Collectors.groupingBy(
                metric -> {
                    Instant truncatedTimestamp = metric.getTimestamp().truncatedTo(ChronoUnit.HOURS);
                    return truncatedTimestamp.toString(); // Use ISO-8601 for consistent format
                }
        ));
    }
}