package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fampay.assignment.videosretrievalserviceserver.db.VideoEntity;
import com.fampay.assignment.videosretrievalserviceserver.db.VideoRepository;

@Service
public class VideosRetrievalService {

    @Autowired
    private VideosRetrievalWorker videosRetrievalWorker;

    @Autowired
    private VideoRepository videoRepository;

    @PostConstruct
    public void runWorker() {
        videosRetrievalWorker.callYoutubeDataApi();
    }

    public List<VideoEntity> getAllEntities() {
        return videoRepository.findAll();
    }
}
