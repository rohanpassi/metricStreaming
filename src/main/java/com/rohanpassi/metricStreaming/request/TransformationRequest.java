package com.rohanpassi.metricStreaming.request;

import com.rohanpassi.metricStreaming.dto.Metric;
import com.rohanpassi.metricStreaming.dto.Transformation;

import lombok.Data;
import java.util.List;

@Data
public class TransformationRequest {
    private List<Metric> metrics;
    private Transformation transformation;
}

