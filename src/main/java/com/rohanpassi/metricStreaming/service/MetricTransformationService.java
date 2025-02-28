package com.rohanpassi.metricStreaming.service;

import com.rohanpassi.metricStreaming.dto.Transformation;
import com.rohanpassi.metricStreaming.model.Metric;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MetricTransformationService {

    public Object transform(List<Metric> metrics, Transformation transformation) { // updated
        switch (transformation.getOperationType()) {
            case FILTER:
                return applyFilters(metrics, transformation.getFilters());
            case GROUP:
                return applyGrouping(metrics, transformation.getGrouper());
            case AGGREGATE:
                //This is tricky, as aggregation would need to be done after grouping.
                //So if the request has grouping and aggregation, we will group first then aggregate.
                if (transformation.getGrouper() != null) {
                    Map<String, List<Metric>> groupedMetrics = applyGrouping(metrics, transformation.getGrouper());
                    return applyAggregation(groupedMetrics, transformation.getAggregator());
                } else {
                    //if there is no grouping and there is aggregation
                    return applyAggregation(Map.of("default", metrics), transformation.getAggregator());
                }
            default:
                throw new IllegalArgumentException("Unsupported operation type: " + transformation.getOperationType());
        }
    }

    public List<Metric> applyFilters(List<Metric> metrics, List<Map<String, Object>> filters) {
         //Update in this function and below function to use the map
        return metrics;
    }

    public Map<String, List<Metric>> applyGrouping(List<Metric> metrics, Map<String, Object> grouper) {
        //Update this function to use the map
        return Map.of();
    }

    public Map<String, Integer> applyAggregation(Map<String, List<Metric>> groupedMetrics, Map<String, Object> aggregator) {
        //update this function to use the map
        return Map.of();
    }
}
