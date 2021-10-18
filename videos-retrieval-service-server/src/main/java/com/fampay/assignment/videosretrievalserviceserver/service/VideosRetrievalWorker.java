package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fampay.assignment.videosretrievalserviceserver.db.VideoEntity;
import com.fampay.assignment.videosretrievalserviceserver.db.VideoRepository;
import com.fampay.assignment.videosretrievalserviceserver.utils.ConversionUtils;
import com.fampay.assignment.videosretrievalserviceserver.utils.DateTimeUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VideosRetrievalWorker {

    @Value("${youtube.search.api.frequency.period.millis}")
    private long delay;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private WebClientService webClientService;

    @Autowired
    private ConversionUtils conversionUtils;

    @Autowired
    private DateTimeUtils dateTimeUtils;

    public void callYoutubeDataApi() {

        new java.util.Timer().schedule(new java.util.TimerTask() {

            @Override
            public void run() {
                Map response = webClientService.sendGetRequest();
                log.info("Response: {}", conversionUtils.pojoToString(response));

                videoRepository.saveAll(getVideoEntityList(response));
            }
        }, 0, delay);
    }

    private List<VideoEntity> getVideoEntityList(Map<String, Object> response) {
        List<Map> items = (List) response.get("items");
        return items.stream().map(this::buildVideoEntity)
                .collect(Collectors.toList());
    }

    private VideoEntity buildVideoEntity(Map item) {
        VideoEntity videoEntity = new VideoEntity();
        Map<String, String> idMap = (Map) item.get("id");
        videoEntity.setId(idMap.get("videoId"));

        Map snippetMap = (Map) item.get("snippet");
        videoEntity.setTitle((String) snippetMap.get("title"));
        videoEntity.setDescription((String) snippetMap.get("description"));

        videoEntity.setPublishedAt((dateTimeUtils.convertIsoDateToDate((String) snippetMap.get("publishedAt"))));
        return videoEntity;
    }
}
