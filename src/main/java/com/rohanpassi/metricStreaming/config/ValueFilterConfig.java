package com.rohanpassi.metricStreaming.config;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValueFilterConfig extends FilterConfig {
    private int threshold;

    public ValueFilterConfig(FilterType filterType, int threshold) {
        super(filterType, FilterTarget.VALUE);
        this.threshold = threshold;
    }

    // public ValueFilterConfig(Map<String, Object> config) {
    //     super(
    //         (FilterType) config.get("filterType"),
    //         (FilterTarget) config.get("filterTarget")
    //     );
    //     this.threshold = (int) config.get("threshold");
    // }
}