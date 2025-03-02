package com.rohanpassi.metricStreaming.transformations.grouping;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.dto.Metric;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TimeGrouper implements MetricGrouper {

    private final ChronoUnit timeUnit;

    public TimeGrouper() {
        this.timeUnit = ChronoUnit.HOURS;
    }

    public TimeGrouper(ChronoUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public Map<String, List<Metric>> group(List<Metric> metrics) {
        return metrics
            .stream()
            .collect(
                Collectors.groupingBy(
                    metric -> {
                        Instant instant = metric.getTimestamp();
                        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
                        ZonedDateTime truncatedDateTime = zonedDateTime.truncatedTo(timeUnit);
                        return truncatedDateTime.toString();
                    }
                )
            );
    }
}