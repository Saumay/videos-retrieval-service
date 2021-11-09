package com.fampay.assignment.videosretrievalserviceserver.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        return getPage(pageNumber, size, null);
    }

    public Paged<VideoEntity> getPage(int pageNumber, int size, String searchQuery) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "publishedAt"));
        Page<VideoEntity> videoPage = getVideoPage(request, searchQuery);
        if(pageNumber > videoPage.getTotalPages()) {
            videosRetrievalWorker.getNextApiPageAndSaveApiResults();
            videoPage = getVideoPage(request, searchQuery);
        }
        return new Paged<>(videoPage, Paging.of(videoPage.getTotalPages(), pageNumber, size));
    }

    private Page<VideoEntity> getVideoPage(PageRequest request, String searchQuery) {
        return searchQuery == null
                ? videoRepository.findAll(request)
                : videoRepository.findAllBySearchQuery(searchQuery.toUpperCase(), request);
    }

    public List<VideoEntity> getAllEntities() {
        return videoRepository.findAll();
    }
}
