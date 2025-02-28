package com.rohanpassi.metricStreaming.request;

import com.rohanpassi.metricStreaming.dto.Transformation;
import com.rohanpassi.metricStreaming.model.Metric;

import lombok.Data;
import java.util.List;

@Data
public class TransformationRequest {
    private List<Metric> metrics;
    private Transformation transformation;
}

