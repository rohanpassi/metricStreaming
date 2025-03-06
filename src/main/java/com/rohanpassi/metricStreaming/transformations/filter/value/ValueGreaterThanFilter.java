package com.rohanpassi.metricStreaming.transformations.filter.value;

public class ValueGreaterThanFilter extends ValueFilter {

    public ValueGreaterThanFilter(Double threshold) {
        super(threshold);
    }

    @Override
    protected boolean compare(Double metricValue) {
        return metricValue > threshold;
    }
}