package com.fampay.assignment.videosretrievalserviceserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fampay.assignment.videosretrievalserviceserver.db.VideoEntity;
import com.fampay.assignment.videosretrievalserviceserver.paging.Paged;
import com.fampay.assignment.videosretrievalserviceserver.service.VideosRetrievalService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class VideoController {

    private static final String DEFAULT_PAGE_SIZE = "5";

    @Autowired
    private VideosRetrievalService videosRetrievalService;

    @GetMapping(value = "/fetch-videos-page")
    public String index(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) int size, Model model) {
        Paged<VideoEntity> page = videosRetrievalService.getPage(pageNumber, size);
        model.addAttribute("videos", page);
        log.info("Returning contents for pageNumber: {} with size: {}", page.getPaging().getPageNumber(), page.getPaging().getPageSize());

        return "videos";
    }

    @GetMapping(value = "/fetch-all-videos")
    public List<VideoEntity> fetchAllVideos() {
        return videosRetrievalService.getAllEntities();
    }
}
