package com.rohanpassi.metricStreaming.transformations.filter.value;

import com.rohanpassi.metricStreaming.config.filter.ValueFilterConfig;
import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValueEqualsFilter implements MetricFilter {
    private final int threshold;

    @Override
    public boolean apply(Metric metric) {
        return metric.getValue() == threshold;
    }

    public ValueEqualsFilter(ValueFilterConfig config) {
        this.threshold = config.getThreshold();
    }
}