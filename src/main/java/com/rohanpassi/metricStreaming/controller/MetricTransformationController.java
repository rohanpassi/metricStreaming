package com.rohanpassi.metricStreaming.controller;

import com.rohanpassi.metricStreaming.service.MetricTransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rohanpassi.metricStreaming.request.TransformationRequest;

@RestController
public class MetricTransformationController {
    @Autowired
    private MetricTransformationService metricTransformationService;

    @PostMapping("/transform")
    public Object transformMetrics(@RequestBody TransformationRequest request) {
        return metricTransformationService.transform(request.getMetrics(), request.getTransformations());
    }
}
