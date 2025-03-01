package com.rohanpassi.metricStreaming.service;

import com.rohanpassi.metricStreaming.dto.Transformation;
import com.rohanpassi.metricStreaming.model.Metric;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;
import com.rohanpassi.metricStreaming.transformations.filter.ValueGreaterThanFilter;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (filters == null || filters.isEmpty()) {
            return metrics;
        }

        List<MetricFilter> metricFilters = filters.stream()
            .map(this::createFilter)
            .collect(Collectors.toList());

        return metrics.stream()
            .filter(metric -> metricFilters.stream().allMatch(filter -> filter.apply(metric)))
            .collect(Collectors.toList());

    }

    public Map<String, List<Metric>> applyGrouping(List<Metric> metrics, Map<String, Object> grouper) {
        //Update this function to use the map
        return Map.of();
    }

    public Map<String, Integer> applyAggregation(Map<String, List<Metric>> groupedMetrics, Map<String, Object> aggregator) {
        //update this function to use the map
        return Map.of();
    }

    private MetricFilter createFilter(Map<String, Object> filterConfig) {
        String filterType = (String) filterConfig.get("filterType");

        switch (filterType) {
            case "ValueGreaterThanFilter":
                int threshold = (int) filterConfig.get("threshold");
                return new ValueGreaterThanFilter(threshold);
            default:
                throw new IllegalArgumentException("Unsupported filter type: " + filterType);
        }
    }
}
