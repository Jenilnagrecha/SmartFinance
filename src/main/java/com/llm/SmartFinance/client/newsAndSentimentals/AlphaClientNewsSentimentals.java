package com.llm.SmartFinance.client.newsAndSentimentals;

import com.llm.SmartFinance.converter.newsAndSentimentals.NewsAndSentimentalsConverter;
import com.llm.SmartFinance.exception.AlphaClientException;
import com.llm.SmartFinance.model.newsAndSentimentals.CryptoNewsAndSentimentals;
import com.llm.SmartFinance.model.newsAndSentimentals.StockNewsAndSentimentals;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class AlphaClientNewsSentimentals {

    private static final Logger log = LoggerFactory.getLogger(AlphaClientNewsSentimentals.class);

    private final WebClient webClient;
    private final NewsAndSentimentalsConverter newsAndSentimentalsConverter;
    private final String apikey;

    public AlphaClientNewsSentimentals(WebClient webClient,
                       NewsAndSentimentalsConverter newsAndSentimentalsConverter,
                       @Value("${Alpha.api-key}") String apikey) {
        this.webClient = webClient;
        this.newsAndSentimentalsConverter = newsAndSentimentalsConverter;
        this.apikey = apikey;
    }

    public StockNewsAndSentimentals requestStock(String ticker) {
        return requestDataFromApi(
                ticker,
                newsAndSentimentalsConverter::convertJsonToStock,
                false
        );
    }

    public CryptoNewsAndSentimentals requestCrypto(String ticker) {
        return requestDataFromApi(
                ticker,
                newsAndSentimentalsConverter::convertJsonToCrypto,
                true
        );
    }

    private <T> T requestDataFromApi(String ticker, BiFunction<String, JSONObject, T> converter, boolean isCrypto) {
        Map<String, Object> responseJson = performApiRequest(ticker, isCrypto);
        if (ObjectUtils.isEmpty(responseJson)) {
            log.warn("Received empty response for ticker: {}", ticker);
            return null;
        }
        return converter.apply(ticker, new JSONObject(responseJson));
    }

    private Map<String, Object> performApiRequest(String ticker, boolean isCrypto) {
        try {
            log.info("Requesting market data for ticker: {}", ticker);
            log.info("Requesting data from Alpha API at {}", Instant.now());

            if (isCrypto) {
                ticker = "COIN,CRYPTO:" + ticker + ",FOREX:USD";
            }

            LocalDate sevenDaysAgo = LocalDate.now().minusDays(30);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String timeFrom = sevenDaysAgo.format(formatter) + "T0000";

            String finalTicker = ticker;
            return this.webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/query")
                            .queryParam("function", "NEWS_SENTIMENT")
                            .queryParam("tickers", finalTicker)
                            .queryParam("time_from", timeFrom)
                            .queryParam("limit", "50")
                            .queryParam("apikey", apikey)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                    .block();
        } catch (Exception e) {
            log.error("Error fetching data from Alpha API for ticker: {}", ticker, e);
            throw new AlphaClientException("Error fetching data from Alpha API", e);
        }
    }

}
