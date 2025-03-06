package com.rohanpassi.metricStreaming.transformations.filter.value;

import org.springframework.stereotype.Component;
import com.rohanpassi.metricStreaming.config.filter.FilterType;

@Component
public class ValueFilterFactory {
    
    public ValueFilter getFilter(FilterType filterType, Double threshold){
        switch (filterType) {
            case GT:
                return new ValueGreaterThanFilter(threshold);
            case GTE:
                return new ValueGreaterThanEqualsFilter(threshold);
            case LT:
                return new ValueLessThanFilter(threshold);
            case LTE:
                return new ValueLessThanEqualsFilter(threshold);
            case EQ:
                return new ValueEqualsFilter(threshold);
            case NEQ:
                return new ValueNotEqualsFilter(threshold);
            default:
                throw new IllegalArgumentException("Unsupported Value filter type: " + filterType);
        }
    }
}
