package com.fampay.assignment.videosretrievalserviceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class VideosRetrievalService {

    @Autowired
    private VideosRetrievalWorker videosRetrievalWorker;

    @PostConstruct
    public void runWorker() {
        videosRetrievalWorker.callYoutubeDataApi();
    }
}
