package com.llm.SmartFinance.model.newsAndSentimentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "CryptoNewsAndSentimentals")
public class CryptoNewsAndSentimentals {
    @Id
    private String date;
    private String name;
    private List<String> contentforLLM;
}
