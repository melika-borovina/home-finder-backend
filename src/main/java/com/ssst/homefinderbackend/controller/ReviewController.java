package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.model.ReviewDto;
import com.ssst.homefinderbackend.service.ReviewService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/review")
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public List<ReviewDto> getReviewList() {
        return reviewService.getReviewList();
    }

    @GetMapping("/list/real-estate/{realEstateId}")
    public List<ReviewDto> getReviewsByRealEstateId(@PathVariable Integer realEstateId) {
        return reviewService.getReviewsByRealEstateId(realEstateId);
    }

    @GetMapping("/list/user/{userId}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable Integer userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto review) {
        return reviewService.createReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
