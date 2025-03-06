package com.rohanpassi.metricStreaming.service;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.dto.Transformation;
import com.rohanpassi.metricStreaming.transformations.aggregator.AggregatorFactory;
import com.rohanpassi.metricStreaming.transformations.aggregator.MetricAggregator;
import com.rohanpassi.metricStreaming.transformations.filter.FilterFactory;
import com.rohanpassi.metricStreaming.transformations.filter.MetricFilter;
import com.rohanpassi.metricStreaming.transformations.grouping.GrouperFactory;
import com.rohanpassi.metricStreaming.transformations.grouping.MetricGrouper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricTransformationService {

    @Autowired
    private FilterFactory filterFactory;

    @Autowired
    private GrouperFactory grouperFactory;

    @Autowired
    private AggregatorFactory aggregatorFactory;

    public Object transform(List<Metric> metrics, List<Transformation> transformations) {
        List<MetricFilter> filters = new ArrayList<>();
        MetricGrouper grouper = null;
        MetricAggregator aggregator = null;

        for (Transformation transformation : transformations) {
            switch (transformation.getOperationType()) {
                case FILTER:
                    filters.add(filterFactory.getFilter(transformation.getConfig()));
                    break;
                case GROUP:
                    grouper = grouperFactory.getGrouper(transformation.getConfig());
                    break;
                case AGGREGATE:
                    aggregator = aggregatorFactory.getAggregator(transformation.getConfig());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported transformation type: " + transformation.getOperationType());
            }
        }

        // Apply filters
        metrics = applyFilters(metrics, filters);

        // Apply grouping
        Object groupedMetrics = applyGrouping(metrics, grouper);

        // Apply aggregation
        Object finalResult = applyAggregation(groupedMetrics, aggregator);

        return finalResult;
    }

    public List<Metric> applyFilters(List<Metric> metrics, List<MetricFilter> filters) {
        if (filters == null || filters.isEmpty()) {
            return metrics;
        }

        for (MetricFilter filter : filters) {
            metrics = metrics.stream().filter(filter::apply).collect(Collectors.toList());
        }
        return metrics;
    }

    public Object applyGrouping(List<Metric> metrics, MetricGrouper grouper) {
        if (grouper == null) {
            return metrics;
        }
        return grouper.group(metrics);
    }

    @SuppressWarnings("unchecked")
    public Object applyAggregation(Object metrics, MetricAggregator aggregator) {
        if(metrics instanceof List){
            if(aggregator == null){
                return metrics;
            }
            return aggregator.aggregate((List<Metric>) metrics);
        }
        else{
            if (aggregator == null) {
                return metrics;
            }
            return aggregator.aggregate((Map<String, List<Metric>>)metrics);
        }
    }
}