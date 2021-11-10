package com.fampay.assignment.videosretrievalserviceserver.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

  @Query("SELECT (MAX(v.publishedAt)) FROM VideoEntity v")
  Date getLatestPublishedDate();

  @Query(value = "SELECT ve FROM VideoEntity ve WHERE UPPER(ve.title) LIKE %:searchQuery% OR UPPER(ve.description) LIKE %:searchQuery% OR UPPER(ve.channel) LIKE %:searchQuery%")
  Page<VideoEntity> findAllBySearchQuery(@Param("searchQuery") String searchQuery, Pageable pageable);
}
