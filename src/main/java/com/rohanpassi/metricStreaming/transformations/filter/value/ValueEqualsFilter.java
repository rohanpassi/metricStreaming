package com.rohanpassi.metricStreaming.transformations.filter.value;

public class ValueEqualsFilter extends ValueFilter {

    public ValueEqualsFilter(Double threshold) {
        super(threshold);
    }

    @Override
    protected boolean compare(Double metricValue) {
        return metricValue == threshold;
    }
}