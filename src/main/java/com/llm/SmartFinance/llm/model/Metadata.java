package com.llm.SmartFinance.llm.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Metadata {
    private String type;
    private LocalDateTime localDateTime;
}