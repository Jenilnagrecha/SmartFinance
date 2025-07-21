package com.llm.SmartFinance.llm.model.technical;

import lombok.Data;

import java.util.List;

@Data
public class TechnicalDataLLM {
    private String Symbol;
    private List<Double> rsiList;
    private List<BbandsLLM> bbandsLLMList;
    private List<MacdLLM> macdLLMList;
}
