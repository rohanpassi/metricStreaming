package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.model.Metric;

@Component
public class SumAggregator implements MetricAggregator {
    @Override
    public int aggregate(List<Metric> metrics) {
        return metrics.stream().mapToInt(Metric::getValue).sum();
    }
}
