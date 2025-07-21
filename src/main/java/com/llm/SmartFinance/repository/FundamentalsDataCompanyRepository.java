package com.llm.SmartFinance.repository;

import com.llm.SmartFinance.model.fundamentals.FundamentalsDataCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundamentalsDataCompanyRepository extends MongoRepository<FundamentalsDataCompany, String> {
}
