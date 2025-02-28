package com.rohanpassi.metricStreaming;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rohanpassi.metricStreaming.aggregator.SumAggregator;
import com.rohanpassi.metricStreaming.filter.ValueGreaterThanFilter;
import com.rohanpassi.metricStreaming.grouping.LabelGrouper;
import com.rohanpassi.metricStreaming.model.Metric;
import com.rohanpassi.metricStreaming.service.MetricTransformationService;

@SpringBootApplication
public class MetricStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricStreamingApplication.class, args);

        List<Metric> metrics = List.of(
                new Metric("API", 10, Instant.now()),
                new Metric("DB", 20, Instant.now()),
                new Metric("API", 30, Instant.now())
        );

        MetricTransformationService service = new MetricTransformationService();
        List<ValueGreaterThanFilter> filters = List.of(new ValueGreaterThanFilter(15));
        LabelGrouper grouper = new LabelGrouper();
        SumAggregator aggregator = new SumAggregator();

        List<Metric> filteredMetrics = service.applyFilters(metrics, filters);
        Map<String, List<Metric>> groupedMetrics = service.applyGrouping(filteredMetrics, grouper);
        Map<String, Integer> aggregatedMetrics = service.applyAggregation(groupedMetrics, aggregator);

        System.out.println(aggregatedMetrics);
	}

}
