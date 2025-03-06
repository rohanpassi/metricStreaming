package com.rohanpassi.metricStreaming.transformations.filter.value;

public class ValueNotEqualsFilter extends ValueFilter {

    public ValueNotEqualsFilter(Double threshold) {
        super(threshold);
    }

    @Override
    protected boolean compare(Double metricValue) {
        return metricValue != threshold;
    }
}