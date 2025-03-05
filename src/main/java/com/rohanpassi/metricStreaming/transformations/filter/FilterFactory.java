package com.rohanpassi.metricStreaming.transformations.filter;

import java.time.Instant;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.config.filter.FilterTarget;
import com.rohanpassi.metricStreaming.config.filter.FilterType;
import com.rohanpassi.metricStreaming.config.filter.TimestampFilterConfig;
import com.rohanpassi.metricStreaming.config.filter.ValueFilterConfig;
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

@Component
public class FilterFactory {
    public MetricFilter getFilter(Map<String, Object> filterConfig) {
        String filterTypeString = (String) filterConfig.get("filterType");
        FilterType filterType = FilterType.valueOf(filterTypeString);

        String filterTargetString = (String) filterConfig.get("filterTarget");
        FilterTarget filterTarget = FilterTarget.valueOf(filterTargetString);

        if (filterTarget.equals(FilterTarget.VALUE)) {
            ValueFilterConfig valueFilterConfig = new ValueFilterConfig(filterType, (int) filterConfig.get("threshold"));
            switch (filterType) {
                case GT:
                    return new ValueGreaterThanFilter(valueFilterConfig);
                case GTE:
                    return new ValueGreaterThanEqualsFilter(valueFilterConfig);
                case LT:
                    return new ValueLessThanFilter(valueFilterConfig);
                case LTE:
                    return new ValueLessThanEqualsFilter(valueFilterConfig);
                case EQ:
                    return new ValueEqualsFilter(valueFilterConfig);
                case NEQ:
                    return new ValueNotEqualsFilter(valueFilterConfig);
                default:
                    throw new IllegalArgumentException("Unsupported filter type: " + filterType);
            }
        }
        else if (filterTarget.equals(FilterTarget.TIMESTAMP)) {
            TimestampFilterConfig timestampFilterConfig = new TimestampFilterConfig(filterType, (Instant) filterConfig.get("timestamp"));
            switch (filterType) {
                case GT:
                    return new TimestampGreaterThanFilter(timestampFilterConfig);
                case GTE:
                    return new TimestampGreaterThanEqualsFilter(timestampFilterConfig);
                case LT:
                    return new TimestampLessThanFilter(timestampFilterConfig);
                case LTE:
                    return new TimestampLessThanEqualsFilter(timestampFilterConfig);
                case EQ:
                    return new TimestampEqualsFilter(timestampFilterConfig);
                case NEQ:
                    return new TimestampNotEqualsFilter(timestampFilterConfig);
                default:
                    throw new IllegalArgumentException("Unsupported filter type: " + filterType);
            }
        }
        else {
            throw new IllegalArgumentException("Unsupported filter target: " + filterTarget);
        }
    }
}
