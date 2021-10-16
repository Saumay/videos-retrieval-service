package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.Map;

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

    public Map<String, String> sendGetRequest() {
        return prepareWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/youtube/v3/search")
                        .queryParam("part", "snippet")
                        .queryParam("q", "cricket")
                        .queryParam("key", apiKey)
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(new HttpHeaders()))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    private WebClient prepareWebClient() {
        return WebClient.builder()
                .baseUrl("https://www.googleapis.com")
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
