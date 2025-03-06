package com.rohanpassi.metricStreaming.transformations.filter.value;

public class ValueGreaterThanEqualsFilter extends ValueFilter {

    public ValueGreaterThanEqualsFilter(Double threshold) {
        super(threshold);
    }

    @Override
    protected boolean compare(Double metricValue) {
        return metricValue >= threshold;
    }
}