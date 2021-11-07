package com.fampay.assignment.videosretrievalserviceserver.db;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Thumbnail {

    private String url;
    private int width;
    private int height;
}
