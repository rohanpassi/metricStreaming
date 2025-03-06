package com.rohanpassi.metricStreaming.transformations.filter.value;

public class ValueLessThanEqualsFilter extends ValueFilter {

    public ValueLessThanEqualsFilter(Double threshold) {
        super(threshold);
    }

    @Override
    protected boolean compare(Double metricValue) {
        return metricValue <= threshold;
    }
}