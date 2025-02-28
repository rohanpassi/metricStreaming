package com.rohanpassi.metricStreaming.model;

import lombok.Data;
import java.time.Instant;

@Data
public class Metric {
    private String label;
    private int value;
    private Instant timestamp;
}
