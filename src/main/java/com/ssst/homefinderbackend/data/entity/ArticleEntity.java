package com.ssst.homefinderbackend.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="articles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "imageURL", nullable = false)
    private String imageURL;

    @Column(name = "author_name", nullable = false)
    private String author_name;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
