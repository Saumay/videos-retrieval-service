package com.fampay.assignment.videosretrievalserviceserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("youtube.search.api")
public class YoutubeApiConfiguration {

    private String url;
    private String endpoint;
    private Long frequencyMillis;
    private Integer pageSize;
    private String searchQuery;
}
