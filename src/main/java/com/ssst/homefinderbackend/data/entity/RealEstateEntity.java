package com.ssst.homefinderbackend.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="real_estates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RealEstateEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "img_url", nullable = false, unique = true)
    private String imgUrl;

    @Column(name = "img_alt", nullable = false)
    private String imgAlt;

    @Column(name = "bedrooms", nullable = false)
    private Integer bedrooms;

    @Column(name = "bathrooms", nullable = false)
    private Integer bathrooms;

    @Column(name = "property_type", nullable = false)
    private String propertyType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "size", nullable = false)
    private Double size;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "avg_rating", nullable = false)
    private Double avgRating;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private List<FeatureEntity> features;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
