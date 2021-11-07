package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fampay.assignment.videosretrievalserviceserver.db.VideoEntity;
import com.fampay.assignment.videosretrievalserviceserver.db.VideoRepository;
import com.fampay.assignment.videosretrievalserviceserver.paging.Paged;
import com.fampay.assignment.videosretrievalserviceserver.paging.Paging;

@Service
public class VideosRetrievalService {

    @Autowired
    private VideosRetrievalWorker videosRetrievalWorker;

    @Autowired
    private VideoRepository videoRepository;

    @PostConstruct
    public void runWorker() {
        videosRetrievalWorker.schedule();
    }

    public Paged<VideoEntity> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<VideoEntity> postPage = videoRepository.findAll(request);
        if(pageNumber > postPage.getTotalPages()) {
            videosRetrievalWorker.getNextApiPageAndSaveApiResults();
            postPage = videoRepository.findAll(request);
        }
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

    public List<VideoEntity> getAllEntities() {
        return videoRepository.findAll();
    }
}
