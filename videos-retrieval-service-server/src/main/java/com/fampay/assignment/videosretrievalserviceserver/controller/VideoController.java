package com.fampay.assignment.videosretrievalserviceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fampay.assignment.videosretrievalserviceserver.service.VideosRetrievalService;

@Controller
public class VideoController {

    @Autowired
    private VideosRetrievalService videosRetrievalService;

    @GetMapping(value = "/fetch-latest-videos")
    public String index(Model model) {
        model.addAttribute("eventName", videosRetrievalService.getAllEntities());
        return "index";
    }
}
