package com.pan.ocr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PanOcrRequest {

    @NotBlank(message = "Base64 image string is required")
    private String image;

    private String requestId;
}
