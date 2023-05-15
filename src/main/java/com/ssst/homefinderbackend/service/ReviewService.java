package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.ReviewEntity;
import com.ssst.homefinderbackend.data.repository.ReviewRepo;
import com.ssst.homefinderbackend.model.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepo repository;

    public ReviewService(ReviewRepo reviewRepository) {
      this.repository=reviewRepository;
    }

    public ReviewEntity validatePayloadAndReturnEntity(Integer reviewId, ReviewDto review) throws Exception {
        Objects.requireNonNull(review.getTitle(), "Review Title is required");
        if (review.getTitle().isEmpty()){
            throw new Exception("Review Title is required!");
        }

        if (reviewId != null) {
            ReviewEntity reviewEntity = getReview(reviewId);
            if (reviewEntity == null) {
                throw new Exception(String.format("Could not find review with id '%s'", reviewId));
            }
        }

        ReviewEntity reviewDb = new ReviewEntity();
        if (reviewId != null) {
            reviewDb.setId(reviewId);
        }

        reviewDb.setTitle(review.getTitle());
        reviewDb.setDescription(review.getDescription());

        return reviewDb;
    }

    public ReviewEntity createReview(ReviewDto review) throws Exception {
        ReviewEntity reviewDb = this.validatePayloadAndReturnEntity(null, review);
        ReviewEntity createdReview = repository.save(reviewDb);
        return createdReview;
    }

    public List<ReviewEntity> getReviewList() {
        return repository.findAll();
    }

    public ReviewEntity getReview(Integer reviewId) throws Exception  {
        Optional<ReviewEntity> result = repository.findById(reviewId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception(String.format("Could not find review with id %s", reviewId));
        }
    }

    public ReviewEntity updateReview(Integer articleId, ReviewDto review) throws Exception {
        ReviewEntity reviewDb = this.validatePayloadAndReturnEntity(articleId, review);
        ReviewEntity createdReview = repository.save(reviewDb);
        return getReview(createdReview.getId());
    }

    public Integer deleteReview(Integer reviewId) {
        repository.deleteById(reviewId);
        return 1;
    }
}
