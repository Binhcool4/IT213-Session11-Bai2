package com.pe.ss11b1.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class LlmCostCalculator {

    private static final BigDecimal ONE_MILLION = new BigDecimal("1000000");
    private static final BigDecimal GEMINI_FLASH_INPUT_RATE = new BigDecimal("0.075");
    private static final BigDecimal GEMINI_FLASH_OUTPUT_RATE = new BigDecimal("0.300");

    public static BigDecimal calculateCost(long inputTokens, long outputTokens, String model) {
        BigDecimal inputTokensBd = BigDecimal.valueOf(inputTokens);
        BigDecimal outputTokensBd = BigDecimal.valueOf(outputTokens);

        BigDecimal inputRate = GEMINI_FLASH_INPUT_RATE;
        BigDecimal outputRate = GEMINI_FLASH_OUTPUT_RATE;

        // inputCost = (inputTokens * 0.075) / 1,000,000
        BigDecimal inputCost = inputTokensBd
                .multiply(inputRate)
                .divide(ONE_MILLION, 12, RoundingMode.HALF_UP);

        // outputCost = (outputTokens * 0.300) / 1,000,000
        BigDecimal outputCost = outputTokensBd
                .multiply(outputRate)
                .divide(ONE_MILLION, 12, RoundingMode.HALF_UP);

        // Làm tròn scale = 8 chuẩn kiểm toán tài chính
        return inputCost.add(outputCost).setScale(8, RoundingMode.HALF_UP);
    }

    public static String formatCost(BigDecimal cost) {
        if (cost == null) {
            return "$0.00000000";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("$0.00000000", symbols);
        return df.format(cost);
    }
}