# PAN OCR Service - In-House Data Extraction Engine

High-performance in-house PAN Card OCR data extraction microservice built on Java 21 LTS, Spring Boot 3.4.x, DJL (Deep Java Library), PaddleOCR and ZXing QR fast path. Includes a dynamic, responsive glassmorphism testing dashboard.

---

## Key Features & Architecture
- **3 Input Options on UI**:
  1. **Upload File**: Drag & drop or browse PAN card image files (JPG, PNG, WebP).
  2. **Camera Capture**: Live camera stream feed with photo snapshot button for mobile devices and desktop webcams.
  3. **Paste Base64**: Direct base64 payload input with auto-formatting and character counter.
- **6-Stage Extraction Pipeline**:
  1. **Cheap Input Validation & Preprocessing**: Base64 normalization, max 7MB payload size cap, min 600px width requirement, longest-side downscaling to 1600px, Laplacian variance blur detection and exposure histogram verification.
  2. **QR Fast Path**: Direct barcode decoding using `zxing` for 2018+ PAN cards with anti-tampering surface text cross-verification.
  3. **DJL PaddleOCR Inference**: Bundled offline text detection, recognition and angle classification models.
  4. **Document Type Verification**: Gating checks for PAN keywords/patterns, Aadhaar card rejection (`LOOKS_LIKE_AADHAAR`) and missing bounding box regions (`CARD_NOT_FULLY_VISIBLE`).
  5. **Field Parsing & Character Correction**: Positional character confusion fixes (`0` <-> `O`, `1` <-> `I` at specific letter/digit positions), 4th character `'P'` individual verification and surname first-letter matching.
  6. **Confidence Gating & Fail-Closed Strategy**: Degrades to explicit rejection codes (`LOW_CONFIDENCE`) rather than passing bad data downstream.
- **Privacy & Security**: 100% in-memory processing (never saved to disk/S3), PII log masking using `PiiMaskUtil` and correlation ID tracking (`requestId`).

---

## Metric Analytics Provided
1. **Image Quality Percentage**: Measures Laplacian variance blur score, exposure balance and resolution dimensions.
2. **Fetch Extraction Percentage**: Computes weighted confidence scores for PAN Number, Full Name, Father's Name and DOB.
3. **Character Correction Accuracy Percentage**: Tracks positional character swaps and business rule validations (4th char `'P'`, surname match).

---

## API Specification

### POST `/api/v1/ocr/pan`

**Request Payload:**
```json
{
  "image": "<base64_string>",
  "requestId": "req-9821abc"
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "source": "QR",
  "panNumber": "ABCDE1234F",
  "name": "ANAND KUMAR",
  "fatherName": "SURESH KUMAR",
  "dob": "15081995",
  "confidence": {
    "panNumber": 0.994,
    "name": 0.952,
    "fatherName": 0.938,
    "dob": 0.981
  },
  "qualityMetrics": {
    "qualityPercentage": 96.5,
    "blurScore": "Laplacian Var 482.4 (Passed)",
    "exposureScore": "Balanced Brightness",
    "resolution": "800x500px"
  },
  "correctionMetrics": {
    "correctionPercentage": 100.0,
    "swapsApplied": [
      "Fixed letter 'O' -> digit '0' at index 9",
      "4th char 'P' Individual flag verified"
    ]
  },
  "requestId": "req-9821abc"
}
```

**Rejection Response (200 OK):**
```json
{
  "success": false,
  "errorCode": "IMAGE_BLURRY",
  "message": "The uploaded PAN card image is too blurry. Please retake the photo with clear focus.",
  "requestId": "req-9821abc"
}
```

---

## Running Locally

### Option 1: Embedded Web Dashboard
Open `index.html` directly in any web browser or run Spring Boot:
```bash
./mvnw spring-boot:run
```
Access the dashboard at `http://localhost:8083`.

### Option 2: Docker Container
```bash
docker build -t pan-ocr-service:1.0.0 .
docker run -p 8083:8083 pan-ocr-service:1.0.0
```

---

## License
Apache-2.0 License.
