package com.fampay.assignment.videosretrievalserviceserver.utils;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateTimeUtils {

    public Date convertIsoDateStrToDate(String isoDate) {
        return DatatypeConverter.parseDateTime(isoDate).getTime();
    }

//    public Optional<String> convertDateToIsoDateStr(Date date) {
////        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
//        if(Objects.nonNull(date)) {
//            String dateStr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
//            return Optional.of(dateStr);
//        }
//        return Optional.empty();
//    }
//
//    public Date convertMillisToDate(long millis) {
//        return new Date(millis);
//    }
}
