package com.fampay.assignment.videosretrievalserviceserver.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
  VideoEntity findByTitle(String title);

//  Page<VideoEntity> getPaginatedEntities(boolean b, Pageable pageable);
}
