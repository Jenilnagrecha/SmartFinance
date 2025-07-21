package com.llm.SmartFinance.llm.model.fundamentals;

import lombok.Data;

@Data
public class DividendsLLM {
    private String exDividendDate;
    private String declarationDate;
    private String recordDate;
    private String paymentDate;
    private double amount;
}
