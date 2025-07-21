package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.newsAndSentimentals.StockNewsAndSentimentals;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockNewsAndSentimentalsRepository extends MongoRepository<StockNewsAndSentimentals, String> {
}
