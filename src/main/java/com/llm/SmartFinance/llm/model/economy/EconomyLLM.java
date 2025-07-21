package com.llm.SmartFinance.llm.model.economy;

import lombok.Data;

import java.util.List;

@Data
public class EconomyLLM {
    List<GdpYearLLM> gdpYearLLMList;
    List<TreasureLLM> treasureLLMList;
    List<FederalFundsRateLLM> federalFundsRateLLMList;
    List<UnemploymentLLM> unemploymentLLMList;
    List<InflationLLM> inflationLLMList;
}
