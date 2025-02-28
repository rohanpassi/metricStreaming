package com.rohanpassi.metricStreaming.controller;

import com.rohanpassi.metricStreaming.dto.Transformation;
import com.rohanpassi.metricStreaming.model.Metric;
import com.rohanpassi.metricStreaming.service.MetricTransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rohanpassi.metricStreaming.request.TransformationRequest;

import java.util.List;

@RestController
public class MetricTransformationController {
    @Autowired
    private MetricTransformationService metricTransformationService;

    @PostMapping("/transform")
    public Object transformMetrics(@RequestBody TransformationRequest request) { //Updated
        return metricTransformationService.transform(request.getMetrics(), request.getTransformation()); //updated
    }
}
