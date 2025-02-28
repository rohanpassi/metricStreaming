package com.rohanpassi.metricStreaming.transformations.filter;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.model.Metric;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ValueGreaterThanFilter implements MetricFilter {
    private final int threshold;

    @Override
    public boolean apply(Metric metric) {
        return metric.getValue() > threshold;
    }
}
