package com.rohanpassi.metricStreaming.service;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.dto.Operation;
import com.rohanpassi.metricStreaming.dto.Transformations;
import com.rohanpassi.metricStreaming.transformations.filter.FilterFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class MetricTransformationService {

    @Autowired
    FilterFactory filterFactory;

    public Object transform(List<Metric> metrics, List<Transformation> transformations) {
        List<MetricFilter> filters = new ArrayList<>();
        List<MetricGrouper> groupers = new ArrayList<>();
        List<MetricAggregator> aggregators = new ArrayList<>();
        
        for(Transformation transformation : transformations){
            switch (transformation.get("operationType")) {
                case "FILTER":
                    filters.add(filterFactory.getFilter(operation.getConfig()));
                    break;
                case GROUP:
                    groupers.add(createGrouper(operation.getConfig()));
                    break;
                case AGGREGATE:
                    aggregators.add(createAggregator(operation.getConfig()));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation type: " + operation.getOperationType());
            }
        }

        // Apply filters
        for (MetricFilter filter : filters) {
            metrics = metrics.stream().filter(filter::apply).collect(Collectors.toList());
        }

        // Apply grouping
        Map<String, List<Metric>> groupedMetrics = Map.of("default", metrics);
        for (MetricGrouper grouper : groupers) {
            groupedMetrics = grouper.group(metrics);
        }

        // Apply aggregation
        if (!aggregators.isEmpty()) {
            MetricAggregator aggregator = aggregators.get(0); // Assuming only one aggregator for simplicity
            return aggregator.aggregate(groupedMetrics);
        }

        return groupedMetrics;
    }
    
}
