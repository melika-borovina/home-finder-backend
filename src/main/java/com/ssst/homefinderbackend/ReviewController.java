package com.ssst.homefinderbackend;

import org.springframework.web.bind.annotation.*;

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
    public List<ReviewDto> getReviewsByRealEstateId(@PathVariable long realEstateId) {
        return reviewService.getReviewsByRealEstateId(realEstateId);
    }

    @GetMapping("/list/user/{userId}")
    public List<ReviewDto> getReviewsByUserId(@PathVariable long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto review) {
        return reviewService.createReview(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable long id) {
        reviewService.deleteReview(id);
    }
}
