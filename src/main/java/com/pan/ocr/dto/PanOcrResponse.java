package com.pan.ocr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PanOcrResponse {

    private boolean success;
    private String source; // "QR" | "OCR"
    private String panNumber;
    private String name;
    private String fatherName;
    private String dob; // format: ddMMyyyy

    private Map<String, Double> confidence;
    
    // Quality & Correction Metrics
    private QualityMetrics qualityMetrics;
    private CorrectionMetrics correctionMetrics;

    // Rejection fields
    private String errorCode;
    private String message;
    private String requestId;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QualityMetrics {
        private double qualityPercentage;
        private String blurScore;
        private String exposureScore;
        private String resolution;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CorrectionMetrics {
        private double correctionPercentage;
        private List<String> swapsApplied;
    }

    public static PanOcrResponse rejection(String errorCode, String message, String requestId) {
        return PanOcrResponse.builder()
                .success(false)
                .errorCode(errorCode)
                .message(message)
                .requestId(requestId)
                .build();
    }
}
