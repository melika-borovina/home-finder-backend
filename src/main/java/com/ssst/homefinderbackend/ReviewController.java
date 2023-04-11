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

    @GetMapping("/{id}")
    public ReviewDto getReview(@PathVariable long id) {
        return reviewService.getReview(id);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto review) {
        return reviewService.createReview(review);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable long id, @RequestBody ReviewDto review) {
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable long id) {
        reviewService.deleteReview(id);
    }
}
