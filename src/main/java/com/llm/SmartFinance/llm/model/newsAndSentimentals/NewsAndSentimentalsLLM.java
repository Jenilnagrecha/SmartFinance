package com.llm.SmartFinance.llm.model.newsAndSentimentals;

import lombok.Data;

@Data
public class NewsAndSentimentalsLLM {

    private final String title;
    private final String url;
    private final String timePublished;
    private final String summary;
    private final double overallSentimentScore;
    private final String overallSentimentLabel;


    public NewsAndSentimentalsLLM(String title, String url, String timePublished, String summary, double overallSentimentScore, String overallSentimentLabel) {
        this.title = title;
        this.url = url;
        this.timePublished = timePublished;
        this.summary = summary;
        this.overallSentimentScore = overallSentimentScore;
        this.overallSentimentLabel = overallSentimentLabel;
    }
}
