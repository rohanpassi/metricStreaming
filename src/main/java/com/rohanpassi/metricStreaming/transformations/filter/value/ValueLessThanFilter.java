package com.rohanpassi.metricStreaming.transformations.filter.value;

public class ValueLessThanFilter extends ValueFilter {

    public ValueLessThanFilter(Double threshold) {
        super(threshold);
    }

    @Override
    protected boolean compare(Double metricValue) {
        return metricValue < threshold;
    }
}