package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.market.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockMarketDataRepository extends MongoRepository<Stock, String> {
}
