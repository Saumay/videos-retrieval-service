package com.fampay.assignment.videosretrievalserviceserver.paging;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private static final int PAGES_TO_DISPLAY = 5;

    private boolean nextEnabled;
    private boolean prevEnabled;
    private int pageSize;
    private int pageNumber;

    private List<PageIcon> icons = new ArrayList<>();

    public void addPageIcons(int from, int to, int pageNumber) {
        IntStream.range(from, to).forEach(
                index -> icons.add(PageIcon.builder()
                        .active(pageNumber != index)
                        .index(index)
                        .pageIconType(PageIconType.PAGE)
                        .build()));
    }

    public void last(int pageSize) {
        icons.add(PageIcon.builder()
                .pageIconType(PageIconType.DOTS)
                .build());

        icons.add(PageIcon.builder()
                .active(true)
                .index(pageSize)
                .pageIconType(PageIconType.PAGE)
                .build());
    }

    public void first(int pageNumber) {
        icons.add(PageIcon.builder()
                .active(pageNumber != 1)
                .index(1)
                .pageIconType(PageIconType.PAGE)
                .build());

        icons.add(PageIcon.builder()
                .pageIconType(PageIconType.DOTS)
                .build());
    }

    public static Paging of(int totalPages, int pageNumber, int pageSize) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setNextEnabled(true);
        paging.setPrevEnabled(pageNumber != 1);
        paging.setPageNumber(pageNumber);

        if (totalPages < PAGES_TO_DISPLAY + 6) {
            paging.addPageIcons(1, totalPages + 1, pageNumber);

        } else if (pageNumber < PAGES_TO_DISPLAY + 1) {
            paging.addPageIcons(1, PAGES_TO_DISPLAY + 4, pageNumber);
            paging.last(totalPages);

        } else if (pageNumber > totalPages - PAGES_TO_DISPLAY) {
            paging.first(pageNumber);
            paging.addPageIcons(totalPages - PAGES_TO_DISPLAY - 2, totalPages + 1, pageNumber);

        } else {
            paging.first(pageNumber);
            paging.addPageIcons(pageNumber - PAGES_TO_DISPLAY /2, pageNumber + PAGES_TO_DISPLAY /2 + 1, pageNumber);
            paging.last(totalPages);
        }

        return paging;
    }
}
