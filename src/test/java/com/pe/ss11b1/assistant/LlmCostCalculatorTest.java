package com.pe.ss11b1.assistant;
import com.pe.ss11b1.model.TraceMetadata;
import com.pe.ss11b1.util.LlmCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LlmCostCalculatorTest {

    @Test
    @DisplayName("Kiểm tra Test Case 1: input=15000, output=1200")
    void testCalculateCostCase1() {
        long inputTokens = 15000;
        long outputTokens = 1200;
        String model = "gemini-2.5-flash";

        BigDecimal cost = LlmCostCalculator.calculateCost(inputTokens, outputTokens, model);
        String formatted = LlmCostCalculator.formatCost(cost);

        assertEquals(new BigDecimal("0.00148500"), cost);
        assertEquals("$0.00148500", formatted);
    }

    @Test
    @DisplayName("Kiểm tra Test Case 2: input=250000, output=45000")
    void testCalculateCostCase2() {
        long inputTokens = 250000;
        long outputTokens = 45000;
        String model = "gemini-2.5-flash";

        BigDecimal cost = LlmCostCalculator.calculateCost(inputTokens, outputTokens, model);
        String formatted = LlmCostCalculator.formatCost(cost);

        assertEquals(new BigDecimal("0.03225000"), cost);
        assertEquals("$0.03225000", formatted);
    }

    @Test
    @DisplayName("Kiểm tra TraceMetadata chuyển đổi Map và JSON")
    void testTraceMetadataConversion() {
        BigDecimal cost = LlmCostCalculator.calculateCost(15000, 1200, "gemini-2.5-flash");
        TraceMetadata metadata = new TraceMetadata(
                "Finance",
                "prod",
                "user_999",
                "sess_abc123",
                "gemini-2.5-flash",
                cost
        );

        Map<String, Object> map = metadata.toMap();
        assertEquals("Finance", map.get("department"));
        assertEquals("prod", map.get("environment"));
        assertEquals("0.00148500", map.get("estimatedCost"));

        String json = metadata.toJson();
        assertTrue(json.contains("\"department\":\"Finance\""));
        assertTrue(json.contains("\"estimatedCost\":\"0.00148500\""));
    }
}