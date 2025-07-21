package com.llm.SmartFinance.llm.model.technical;

import lombok.Data;

@Data
public class BbandsLLM {
    private String date;
    private double realUpperBand;
    private double realMiddleBand;
    private double realLowerBand;
}
