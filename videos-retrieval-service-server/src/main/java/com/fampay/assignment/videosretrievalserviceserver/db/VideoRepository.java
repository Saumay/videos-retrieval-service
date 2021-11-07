package com.fampay.assignment.videosretrievalserviceserver.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
  VideoEntity findByTitle(String title);

//  @Query("SELECT (MAX(v.publishedAt)) FROM VideoEntity v")
//  Date getLatestPublishedDate();
//
//  @Query("SELECT (MIN(v.publishedAt)) FROM VideoEntity v")
//  Date getOldestPublishedDate();

  Page<VideoEntity> findAllBy(Pageable pageable);
}
