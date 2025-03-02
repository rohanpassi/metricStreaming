package com.rohanpassi.metricStreaming.service;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.dto.Transformation;
import com.rohanpassi.metricStreaming.transformations.aggregator.MetricAggregator;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;
import com.rohanpassi.metricStreaming.transformations.filter.value.ValueGreaterThanFilter;
import com.rohanpassi.metricStreaming.transformations.grouping.MetricGrouper;
import com.rohanpassi.metricStreaming.transformations.grouping.TimeGrouper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricTransformationService {

    @Autowired
    private TimeGrouper timeGrouper;
    @Autowired
    private MetricAggregator metricAggregator;

    public Object transform(List<Metric> metrics, Transformation transformation) {
        switch (transformation.getOperationType()) {
            case FILTER:
                return applyFilters(metrics, transformation.getFilters());
            case GROUP:
                return applyGrouping(metrics, transformation.getGrouper());
            case AGGREGATE:
                return applyAggregation(metrics, transformation.getGrouper(), transformation.getAggregator());
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

    public Map<String, List<Metric>> applyGrouping(List<Metric> metrics, Map<String, Object> grouperConfig) {
        if (grouperConfig == null || grouperConfig.isEmpty()) {
            return Map.of("default", metrics); // Treat all metrics as one group
        }
        MetricGrouper grouper = createGrouper(grouperConfig);
        return grouper.group(metrics);
    }

    public Object applyAggregation(List<Metric> metrics, Map<String, Object> grouperConfig, Map<String, Object> aggregatorConfig) {
        if (aggregatorConfig == null || aggregatorConfig.isEmpty()) {
            return Map.of(); // Or handle no aggregation differently
        }
        MetricAggregator aggregator = createAggregator(aggregatorConfig);
        if (grouperConfig != null && !grouperConfig.isEmpty()) {
            Map<String, List<Metric>> groupedMetrics = applyGrouping(metrics, grouperConfig);
            return aggregator.aggregate(groupedMetrics);
        } else {
            //if there is no grouping and there is aggregation
            return aggregator.aggregate(metrics);
        }
    }

    private MetricFilter createFilter(Map<String, Object> filterConfig) {
        String filterType = (String) filterConfig.get("filterType");

        switch (filterType) {
            case "GT":
                int threshold = (int) filterConfig.get("threshold");
                return new ValueGreaterThanFilter(threshold);
            default:
                throw new IllegalArgumentException("Unsupported filter type: " + filterType);
        }
    }
    private MetricGrouper createGrouper(Map<String, Object> grouperConfig){
        String grouperType = (String) grouperConfig.get("grouperType");
        switch (grouperType){
            case "TimeGrouper":
                return timeGrouper;
            default:
                throw new IllegalArgumentException("Unsupported grouper type: " + grouperType);
        }
    }
    private MetricAggregator createAggregator(Map<String, Object> aggregatorConfig){
        String aggregatorType = (String) aggregatorConfig.get("aggregatorType");
        switch (aggregatorType){
            case "SumAggregator":
                return metricAggregator;
            default:
                throw new IllegalArgumentException("Unsupported aggregator type: " + aggregatorType);
        }
    }
}
