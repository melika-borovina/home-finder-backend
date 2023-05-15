package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.ReviewEntity;
import com.ssst.homefinderbackend.model.ErrorObject;
import com.ssst.homefinderbackend.model.ReviewDto;
import com.ssst.homefinderbackend.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<ReviewEntity>> getReviewList() {
        return new ResponseEntity<>(this.reviewService.getReviewList(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    ResponseEntity<Object> getReview(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.reviewService.getReview(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(404, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    ResponseEntity<Object> createReview(@RequestBody ReviewDto review) {
        try {
            return new ResponseEntity<>(this.reviewService.createReview(review), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> updateReview(@PathVariable Integer id, @RequestBody ReviewDto review) {
        try {
            return new ResponseEntity<>(this.reviewService.updateReview(id, review), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(100, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteReview(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(this.reviewService.deleteReview(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorObject(500, e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
