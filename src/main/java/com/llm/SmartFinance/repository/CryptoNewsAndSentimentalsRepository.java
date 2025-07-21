package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.newsAndSentimentals.CryptoNewsAndSentimentals;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoNewsAndSentimentalsRepository extends MongoRepository<CryptoNewsAndSentimentals, String> {
}
