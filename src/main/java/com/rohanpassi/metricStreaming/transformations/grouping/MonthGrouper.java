package com.rohanpassi.metricStreaming.transformations.grouping;

import com.rohanpassi.metricStreaming.dto.Metric;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MonthGrouper implements MetricGrouper {

    @Override
    public Map<String, List<Metric>> group(List<Metric> metrics) {
        if (metrics == null || metrics.isEmpty()) {
            return Map.of();
        }
        return metrics.stream().collect(Collectors.groupingBy(
                metric -> {
                    LocalDate date = metric.getTimestamp().atZone(ZoneId.systemDefault()).toLocalDate();
                    return date.format(DateTimeFormatter.ofPattern("yyyy-MM")); // Format as YYYY-MM
                }
        ));
    }
}