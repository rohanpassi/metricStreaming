package com.rohanpassi.metricStreaming.transformations.filter;

import java.time.Instant;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rohanpassi.metricStreaming.config.filter.FilterTarget;
import com.rohanpassi.metricStreaming.config.filter.FilterType;
import com.rohanpassi.metricStreaming.transformations.filter.timestamp.TimestampFilterFactory;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueFilterFactory;

@Component
public class FilterFactory {

    @Autowired
    private ValueFilterFactory valueFilterFactory;

    @Autowired
    private TimestampFilterFactory timestampFilterFactory;


    public MetricFilter getFilter(Map<String, Object> filterConfig) {
        String filterTypeString = filterConfig.get("filterType").toString();
        FilterType filterType = FilterType.valueOf(filterTypeString);

        String filterTargetString = filterConfig.get("filterTarget").toString();
        FilterTarget filterTarget = FilterTarget.valueOf(filterTargetString);

        switch(filterTarget){
            case VALUE:
                Double threshold = Double.parseDouble(filterConfig.get("threshold").toString()) ;
                return valueFilterFactory.getFilter(filterType, threshold);
            case TIMESTAMP:
                Instant timestamp = Instant.parse((String) filterConfig.get("timestamp"));
                return timestampFilterFactory.getFilter(filterType, timestamp);
            default:
            throw new IllegalArgumentException("Unsupported filter target: " + filterTarget);
        }
    }
}
