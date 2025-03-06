package com.rohanpassi.metricStreaming.transformations.aggregator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.rohanpassi.metricStreaming.config.aggregator.AggregatorType;

@Component
public class AggregatorFactory {

    public MetricAggregator getAggregator(Map<String, String> aggregatorConfig) {
        String aggregatorTypeString = aggregatorConfig.get("aggregatorType");
        AggregatorType aggregatorType = AggregatorType.valueOf(aggregatorTypeString);

        switch (aggregatorType) {
            case SUM:
                return new SumAggregator();
            case AVG:
                return new AverageAggregator();
            case MAX:
                return new MaxAggregator();
            case MIN:
                return new MinAggregator();
            default:
                throw new IllegalArgumentException("Unsupported aggregator type: " + aggregatorType);
        }
    }
}
