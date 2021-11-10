package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.fampay.assignment.videosretrievalserviceserver.config.YoutubeApiConfiguration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class WebClientService {

    private static final String GOOGLE_API_KEY_ENV_VARIABLE = "GOOGLE_API_KEY";

    @Autowired
    private YoutubeApiConfiguration youtubeApiConfiguration;

    public Map<?,?> sendGetRequest(Optional<String> pageToken, Optional<String> publishedAfterDate) {
        String googleApiKey = System.getenv(GOOGLE_API_KEY_ENV_VARIABLE);

        return prepareWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path(youtubeApiConfiguration.getEndpoint())
                        .queryParam("part", "snippet")
                        .queryParam("type", "video")
                        .queryParam("order", "date")
                        .queryParam("q", youtubeApiConfiguration.getSearchQuery())
                        .queryParam("key", googleApiKey)
                        .queryParam("maxResults", youtubeApiConfiguration.getPageSize())
                        .queryParamIfPresent("publishedAfter", publishedAfterDate)
                        .queryParamIfPresent("pageToken", pageToken)
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(new HttpHeaders()))
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    private WebClient prepareWebClient() {
        return WebClient.builder()
                .baseUrl(youtubeApiConfiguration.getUrl())
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
