package com.rohanpassi.metricStreaming.grouping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.model.Metric;

@Component
public class LabelGrouper implements MetricGrouper {
    @Override
    public Map<String, List<Metric>> group(List<Metric> metrics) {
        return metrics.stream().collect(Collectors.groupingBy(Metric::getLabel));
    }
}
