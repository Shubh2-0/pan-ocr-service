package com.pan.ocr.util;

public class PiiMaskUtil {

    private PiiMaskUtil() {
        // Utility class constructor
    }

    /**
     * Masks PAN number (e.g. ABCDE1234F -> AB***1234F)
     */
    public static String maskPan(String pan) {
        if (pan == null || pan.length() < 10) {
            return "****";
        }
        return pan.substring(0, 2) + "***" + pan.substring(5);
    }

    /**
     * Truncates base64 payload for safe log output
     */
    public static String maskBase64(String base64) {
        if (base64 == null) {
            return "null";
        }
        int len = base64.length();
        if (len <= 20) {
            return "*****";
        }
        return base64.substring(0, 10) + "...[masked " + len + " chars]..." + base64.substring(len - 10);
    }
}
