package com.fampay.assignment.videosretrievalserviceserver.utils;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class DateTimeUtils {

    public Date convertIsoDateStrToDate(String isoDate) {
        return DatatypeConverter.parseDateTime(isoDate).getTime();
    }

    public Optional<String> convertDateToIsoDateStr(Date date) {
        if(Objects.nonNull(date)) {
            String dateStr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
            return Optional.of(dateStr);
        }
        return Optional.empty();
    }

    public Date convertMillisToDate(long millis) {
        return new Date(millis);
    }
}
