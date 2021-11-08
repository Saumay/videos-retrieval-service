package com.fampay.assignment.videosretrievalserviceserver.paging;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageIcon {

    private PageIconType pageIconType;
    private int index;
    private boolean active;
}
