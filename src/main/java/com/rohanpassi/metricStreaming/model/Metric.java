package com.rohanpassi.metricStreaming.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Metric {
    private String label;
    private int value;
    private Instant timestamp;
}
