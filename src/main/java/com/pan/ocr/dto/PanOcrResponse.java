package com.pan.ocr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

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

    public PanOcrResponse() {
    }

    public static PanOcrResponse rejection(String errorCode, String message, String requestId) {
        PanOcrResponse resp = new PanOcrResponse();
        resp.setSuccess(false);
        resp.setErrorCode(errorCode);
        resp.setMessage(message);
        resp.setRequestId(requestId);
        return resp;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public Map<String, Double> getConfidence() { return confidence; }
    public void setConfidence(Map<String, Double> confidence) { this.confidence = confidence; }

    public QualityMetrics getQualityMetrics() { return qualityMetrics; }
    public void setQualityMetrics(QualityMetrics qualityMetrics) { this.qualityMetrics = qualityMetrics; }

    public CorrectionMetrics getCorrectionMetrics() { return correctionMetrics; }
    public void setCorrectionMetrics(CorrectionMetrics correctionMetrics) { this.correctionMetrics = correctionMetrics; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public static class QualityMetrics {
        private double qualityPercentage;
        private String blurScore;
        private String exposureScore;
        private String resolution;

        public QualityMetrics() {}

        public QualityMetrics(double qualityPercentage, String blurScore, String exposureScore, String resolution) {
            this.qualityPercentage = qualityPercentage;
            this.blurScore = blurScore;
            this.exposureScore = exposureScore;
            this.resolution = resolution;
        }

        public double getQualityPercentage() { return qualityPercentage; }
        public void setQualityPercentage(double qualityPercentage) { this.qualityPercentage = qualityPercentage; }

        public String getBlurScore() { return blurScore; }
        public void setBlurScore(String blurScore) { this.blurScore = blurScore; }

        public String getExposureScore() { return exposureScore; }
        public void setExposureScore(String exposureScore) { this.exposureScore = exposureScore; }

        public String getResolution() { return resolution; }
        public void setResolution(String resolution) { this.resolution = resolution; }
    }

    public static class CorrectionMetrics {
        private double correctionPercentage;
        private List<String> swapsApplied;

        public CorrectionMetrics() {}

        public CorrectionMetrics(double correctionPercentage, List<String> swapsApplied) {
            this.correctionPercentage = correctionPercentage;
            this.swapsApplied = swapsApplied;
        }

        public double getCorrectionPercentage() { return correctionPercentage; }
        public void setCorrectionPercentage(double correctionPercentage) { this.correctionPercentage = correctionPercentage; }

        public List<String> getSwapsApplied() { return swapsApplied; }
        public void setSwapsApplied(List<String> swapsApplied) { this.swapsApplied = swapsApplied; }
    }
}
