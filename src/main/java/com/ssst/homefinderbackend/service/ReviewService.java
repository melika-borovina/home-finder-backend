package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.model.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    public ReviewDto createReview(ReviewDto review) {
        review.setId(111);
        review.setTitle("Perfect house!!! Love it !! ;Ppp");
        return review;
    }

    public List<ReviewDto> getReviewList() {
        List<ReviewDto> result = new ArrayList<>();
        ReviewDto x = new ReviewDto(222, "Perfffff", "Love it, Barbie dream house", 5,1, 1);
        ReviewDto y = new ReviewDto(777, "Looks dirty",  "I wanted to like it, but it looks like cockroaches live here ;((",1,2, 4);
        result.add(x);
        result.add(y);

        return result;
    }

    public List<ReviewDto> getReviewsByRealEstateId(Integer realEstateId) {
        List<ReviewDto> results = new ArrayList<>();
        ReviewDto x = new ReviewDto(222, "Perfffff", "Love it, Barbie dream house", 5,333, 1);
        ReviewDto y = new ReviewDto(777, "Looks dirty",  "I wanted to like it, but it looks like cockroaches live here ;((",1,333, 4);
        results.add(x);
        results.add(y);
        return results.stream()
                .filter(result -> result.getRealEstateId().equals(realEstateId))
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByUserId(Integer userId) {
        List<ReviewDto> results = new ArrayList<>();
        ReviewDto x = new ReviewDto(222, "Perfffff", "Love it, Barbie dream house", 5,23, 111);
        ReviewDto y = new ReviewDto(777, "Looks dirty",  "I wanted to like it, but it looks like cockroaches live here ;((",1,44, 111);
        results.add(x);
        results.add(y);
        return results.stream()
                .filter(result -> result.getUserId().equals(userId))
                .collect(Collectors.toList());
    }


    public void deleteReview(Integer id) {
        System.out.println("Deleted " + id);
    }
}
