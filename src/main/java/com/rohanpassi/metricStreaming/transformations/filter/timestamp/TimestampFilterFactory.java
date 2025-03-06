package com.rohanpassi.metricStreaming.transformations.filter.timestamp;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.config.filter.FilterType;

@Component
public class TimestampFilterFactory {

    public TimestampFilter getFilter(FilterType filterType, Instant timestamp) {
        switch (filterType) {
            case GT:
                return new TimestampGreaterThanFilter(timestamp);
            case GTE:
                return new TimestampGreaterThanEqualsFilter(timestamp);
            case LT:
                return new TimestampLessThanFilter(timestamp);
            case LTE:
                return new TimestampLessThanEqualsFilter(timestamp);
            case EQ:
                return new TimestampEqualsFilter(timestamp);
            case NEQ:
                return new TimestampNotEqualsFilter(timestamp);
            default:
                throw new IllegalArgumentException("Unsupported filter type: " + filterType);
        }
    }

}
