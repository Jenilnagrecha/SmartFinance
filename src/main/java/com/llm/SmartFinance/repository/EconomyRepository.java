package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.economy.EconomyData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EconomyRepository extends MongoRepository<EconomyData, String> {
}
