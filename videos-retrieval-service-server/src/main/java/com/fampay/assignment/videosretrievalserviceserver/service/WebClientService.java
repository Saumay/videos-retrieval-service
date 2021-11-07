package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class WebClientService {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.url}")
    private String apiUrl;

    @Value("${youtube.search.api.endpoint}")
    private String searchEndpoint;

    public Map<?,?> sendGetRequest(Optional<String> pageToken) {
        return prepareWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path(searchEndpoint)
                        .queryParam("part", "snippet")
                        .queryParam("type", "video")
                        .queryParam("order", "date")
                        .queryParam("q", "cricket")
                        .queryParam("key", apiKey)
                        .queryParam("maxResults", 20)
//                        .queryParamIfPresent("publishedAfter", publishedAfterDateStr)
                        .queryParamIfPresent("pageToken", pageToken)
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(new HttpHeaders()))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    private WebClient prepareWebClient() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .filter(logRequest())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(this::logUrlAndHeaders);
    }

    private Mono<ClientRequest> logUrlAndHeaders(ClientRequest clientRequest) {
        log.info("Request method: {}, url: {}, headers: {}", clientRequest.method(), clientRequest.url(),
                clientRequest.headers());
        return Mono.just(clientRequest);
    }
}
