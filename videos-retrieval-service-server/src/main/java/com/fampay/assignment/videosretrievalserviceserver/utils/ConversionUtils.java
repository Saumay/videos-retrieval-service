package com.fampay.assignment.videosretrievalserviceserver.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public <T> T convertToPojo(Object object, Class<T> classOfT) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(object, objectMapper.getTypeFactory()
                    .constructType(classOfT));
        } catch (Exception e) {
            log.error("Something went wrong while converting map to Pojo : ", e);
        }
        return null;
    }

    public <T> T stringToPojo(String str, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str, tClass);
        } catch (JsonProcessingException e) {
            log.error("Something went wrong while converting String to pojo : ", e);
        }
        return null;
    }
}
