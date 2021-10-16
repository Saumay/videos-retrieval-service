package com.fampay.assignment.videosretrievalserviceserver.service;

import com.fampay.assignment.videosretrievalserviceserver.utils.ConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Component
@Slf4j
public class VideosRetrievalWorker {

    @Value("${youtube.data.api.frequency.period.millis}")
    private long delay;

    @Autowired
    private WebClientService webClientService;

    @Autowired
    private ConversionUtils conversionUtils;

    public void callYoutubeDataApi() {

        new java.util.Timer().schedule(new java.util.TimerTask() {

            @Override
            public void run() {
                Map<String, String> response = webClientService.sendGetRequest();
                log.info("Response: {}", conversionUtils.pojoToString(response));
            }
        }, 0, delay);
    }
}
