package com.pe.ss11b1.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public record TraceMetadata(
        String department,
        String environment,
        String userId,
        String sessionId,
        String model,
        BigDecimal estimatedCost
) {
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("department", department);
        map.put("environment", environment);
        map.put("userId", userId);
        map.put("sessionId", sessionId);
        map.put("model", model);
        map.put("estimatedCost", estimatedCost != null ? estimatedCost.toPlainString() : "0.00000000");
        return map;
    }

    public String toJson() {
        String costStr = estimatedCost != null ? estimatedCost.toPlainString() : "0.00000000";
        return String.format(
                "{\"department\":\"%s\",\"environment\":\"%s\",\"userId\":\"%s\",\"sessionId\":\"%s\",\"model\":\"%s\",\"estimatedCost\":\"%s\"}",
                department, environment, userId, sessionId, model, costStr
        );
    }
}