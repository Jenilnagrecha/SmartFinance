package com.llm.SmartFinance.converter.classification;

import com.llm.SmartFinance.formatter.classification.ClassificationFormatter;
import com.llm.SmartFinance.llm.model.classification.ClassficationLLM;
import com.llm.SmartFinance.model.classification.StockClassification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class ClassificationConverter {

    private static final String TOP_GAINERS = "top_gainers";
    private static final String TOP_LOSERS = "top_losers";
    private static final String MOST_ACTIVELY_TRADED = "most_actively_traded";

    private final ClassificationFormatter classificationFormatter;

    public ClassificationConverter(ClassificationFormatter classificationFormatter) {
        this.classificationFormatter = classificationFormatter;
    }

    public StockClassification convertJsonToStockClassification(JSONObject jsonResponse) {
        if (ObjectUtils.isEmpty(jsonResponse)) {
            return new StockClassification();
        }

        return new StockClassification(jsonResponse.getString("last_updated"),
                null,
                buildStockClassification(jsonResponse)
        );
    }

    private List<String> buildStockClassification(JSONObject classification) {
        List<String> classifications = new ArrayList<>();
        Stream.of(
                new ClassificationData(TOP_GAINERS, classification.optJSONArray(TOP_GAINERS)),
                new ClassificationData(TOP_LOSERS, classification.optJSONArray(TOP_LOSERS)),
                new ClassificationData(MOST_ACTIVELY_TRADED, classification.optJSONArray(MOST_ACTIVELY_TRADED))
        ).forEach(data -> addClassificationData(classifications, data));
        return classifications;
    }

    private void addClassificationData(List<String> classifications, ClassificationData data) {
        if (ObjectUtils.isEmpty(data.jsonArray)) {
            return;
        }
        for (int i = 0; i < data.jsonArray.length(); i++) {
            classifications.add(prepareClassification(data.type, data.jsonArray, i));
        }
    }

    private String prepareClassification(String type, JSONArray array, int i) {
        JSONObject jsonObject = array.getJSONObject(i);
        ClassficationLLM classficationLLM = new ClassficationLLM(
                jsonObject.getString("ticker"),
                jsonObject.getDouble("price"),
                jsonObject.getDouble("change_amount"),
                jsonObject.getString("change_percentage"),
                jsonObject.getDouble("volume")
        );
        return classificationFormatter.format(type, classficationLLM);
    }

    private static class ClassificationData {
        private final String type;
        private final JSONArray jsonArray;

        public ClassificationData(String type, JSONArray jsonArray) {
            this.type = type;
            this.jsonArray = jsonArray;
        }
    }
}
