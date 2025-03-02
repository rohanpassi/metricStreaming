package com.rohanpassi.metricStreaming.transformations.filter.value;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValueGreaterThanFilter implements MetricFilter {
    private final int threshold;

    @Override
    public boolean apply(Metric metric) {
        return metric.getValue() > threshold;
    }
}