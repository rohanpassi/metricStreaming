package com.rohanpassi.metricStreaming.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class Metric {
    private final int value;
    private final Instant timestamp;
}
