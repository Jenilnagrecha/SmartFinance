package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.technical.Technical;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechnicalRepository extends MongoRepository<Technical, String> {
}
