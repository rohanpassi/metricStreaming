package com.rohanpassi.metricStreaming.transformations.grouping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rohanpassi.metricStreaming.dto.Metric;

public class DayGrouper implements MetricGrouper {

    @Override
    public Map<String, List<Metric>> group(List<Metric> metrics) {
        if (metrics == null || metrics.isEmpty()) {
            return Map.of();
        }
        // Group by the date part of the timestamp (YYYY-MM-DD)
        return metrics.stream().collect(Collectors.groupingBy(
                metric -> {
                    LocalDate date = metric.getTimestamp().atZone(ZoneId.systemDefault()).toLocalDate();
                    return date.format(DateTimeFormatter.ISO_LOCAL_DATE); // Format as YYYY-MM-DD
                }
        ));
    }
}
