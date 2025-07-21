package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.classification.StockClassification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockClassificationRepository extends MongoRepository<StockClassification, String> {
}
