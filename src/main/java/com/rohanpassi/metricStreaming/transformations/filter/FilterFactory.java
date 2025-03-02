package com.rohanpassi.metricStreaming.transformations.filter;

import com.rohanpassi.metricStreaming.config.FilterConfig;
import com.rohanpassi.metricStreaming.config.FilterTarget;
import com.rohanpassi.metricStreaming.config.TimestampFilterConfig;
import com.rohanpassi.metricStreaming.config.ValueFilterConfig;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampGreaterThanEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampGreaterThanFilter;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampLessThanEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampLessThanFilter;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampNotEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueGreaterThanEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueGreaterThanFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueLessThanEqualsFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueLessThanFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueNotEqualsFilter;

public class FilterFactory {
    public static MetricFilter createFilter(FilterConfig filterConfig) {
        if (filterConfig.getFilterTarget() == FilterTarget.VALUE) {
            ValueFilterConfig valueFilterConfig = (ValueFilterConfig) filterConfig;
            switch (filterConfig.getFilterType()) {
                case GT:
                    return new ValueGreaterThanFilter(valueFilterConfig.getThreshold());
                case GTE:
                    return new ValueGreaterThanEqualsFilter(valueFilterConfig.getThreshold());
                case LT:
                    return new ValueLessThanFilter(valueFilterConfig.getThreshold());
                case LTE:
                    return new ValueLessThanEqualsFilter(valueFilterConfig.getThreshold());
                case EQ:
                    return new ValueEqualsFilter(valueFilterConfig.getThreshold());
                case NEQ:
                    return new ValueNotEqualsFilter(valueFilterConfig.getThreshold());
                default:
                    throw new IllegalArgumentException("Unsupported filter type: " + filterConfig.getFilterType());
            }
        }
        else if (filterConfig.getFilterTarget() == FilterTarget.TIMESTAMP) {
            TimestampFilterConfig timestampFilterConfig = (TimestampFilterConfig) filterConfig;
            switch (filterConfig.getFilterType()) {
                case GT:
                    return new TimestampGreaterThanFilter(timestampFilterConfig.getTimestamp());
                case GTE:
                    return new TimestampGreaterThanEqualsFilter(timestampFilterConfig.getTimestamp());
                case LT:
                    return new TimestampLessThanFilter(timestampFilterConfig.getTimestamp());
                case LTE:
                    return new TimestampLessThanEqualsFilter(timestampFilterConfig.getTimestamp());
                case EQ:
                    return new TimestampEqualsFilter(timestampFilterConfig.getTimestamp());
                case NEQ:
                    return new TimestampNotEqualsFilter(timestampFilterConfig.getTimestamp());
                default:
                    throw new IllegalArgumentException("Unsupported filter type: " + filterConfig.getFilterType());
            }
        }
        else {
            throw new IllegalArgumentException("Unsupported filter target: " + filterConfig.getFilterTarget());
        }
    }
}
