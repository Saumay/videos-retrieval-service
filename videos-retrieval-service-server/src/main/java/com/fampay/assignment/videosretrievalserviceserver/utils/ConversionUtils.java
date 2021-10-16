package com.fampay.assignment.videosretrievalserviceserver.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConversionUtils {

    public String pojoToString(Object data) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            log.error("Something went wrong while converting pojo to String : ", e);
        }
        return null;
    }
}
