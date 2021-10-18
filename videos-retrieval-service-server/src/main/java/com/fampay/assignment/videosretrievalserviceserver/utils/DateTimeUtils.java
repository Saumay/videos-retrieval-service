package com.fampay.assignment.videosretrievalserviceserver.utils;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateTimeUtils {

    public Date convertIsoDateToDate(String isoDate) {
        return DatatypeConverter.parseDateTime(isoDate).getTime();
    }
}
