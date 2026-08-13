package com.pan.ocr.dto;

import jakarta.validation.constraints.NotBlank;

public class PanOcrRequest {

    @NotBlank(message = "Base64 image string is required")
    private String image;

    private String requestId;

    public PanOcrRequest() {
    }

    public PanOcrRequest(String image, String requestId) {
        this.image = image;
        this.requestId = requestId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
