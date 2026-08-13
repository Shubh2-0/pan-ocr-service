package com.pan.ocr.controller;

import com.pan.ocr.dto.PanOcrRequest;
import com.pan.ocr.dto.PanOcrResponse;
import com.pan.ocr.service.OcrPipelineService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ocr")
@CrossOrigin(origins = "*")
public class OcrController {

    private static final Logger log = LoggerFactory.getLogger(OcrController.class);
    private final OcrPipelineService ocrPipelineService;

    public OcrController(OcrPipelineService ocrPipelineService) {
        this.ocrPipelineService = ocrPipelineService;
    }

    @PostMapping("/pan")
    public ResponseEntity<PanOcrResponse> extractPanData(@Valid @RequestBody PanOcrRequest request) {
        log.info("Received POST /api/v1/ocr/pan request. Request ID: {}", request.getRequestId());
        PanOcrResponse response = ocrPipelineService.processPanCard(request);
        return ResponseEntity.ok(response);
    }
}
