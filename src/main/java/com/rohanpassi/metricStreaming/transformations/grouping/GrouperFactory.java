package com.rohanpassi.metricStreaming.transformations.grouping;

import java.util.Map;
import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.config.grouper.GrouperType;

@Component
public class GrouperFactory {
    public MetricGrouper getGrouper(Map<String, String> grouperConfig) {
        String grouperTypeString = grouperConfig.get("grouperType");
        GrouperType grouperType = GrouperType.valueOf(grouperTypeString);
        switch (grouperType) {
            case MINUTE:
                return new MinuteGrouper();
            case HOUR:
                return new HourGrouper();
            case DAY:
                return new DayGrouper();
            case WEEK:
                return new WeekGrouper();
            case MONTH:
                return new MonthGrouper();
            default:
                throw new IllegalArgumentException("Unsupported grouper type: " + grouperType);
        }
    }
}
