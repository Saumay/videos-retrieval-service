package com.fampay.assignment.videosretrievalserviceserver.db;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
@Table(name = "video_details", indexes = {@Index(name = "IDX_search", columnList = "title, description, channel")})
public class VideoEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "channel", nullable = false)
    private String channel;

    @Type(type = "json")
    @Column(name = "thumbnail", columnDefinition = "json")
    private Thumbnail thumbnail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "published_at")
    private Date publishedAt;
}
