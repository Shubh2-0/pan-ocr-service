package com.pan.ocr.controller;

import com.pan.ocr.dto.PanOcrRequest;
import com.pan.ocr.dto.PanOcrResponse;
import com.pan.ocr.service.OcrPipelineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/ocr")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OcrController {

    private final OcrPipelineService ocrPipelineService;

    @PostMapping("/pan")
    public ResponseEntity<PanOcrResponse> extractPanData(@Valid @RequestBody PanOcrRequest request) {
        log.info("Received POST /api/v1/ocr/pan request. Request ID: {}", request.getRequestId());
        PanOcrResponse response = ocrPipelineService.processPanCard(request);
        return ResponseEntity.ok(response);
    }
}
