package com.rohanpassi.metricStreaming.transformations.filter.value;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;

public abstract class ValueFilter implements MetricFilter {
    protected final Double threshold;

    public ValueFilter(Double threshold) {
        this.threshold = threshold;
    }

    protected abstract boolean compare(Double metricValue);

    @Override
    public boolean apply(Metric metric){
        return compare(metric.getValue());
    }
}
