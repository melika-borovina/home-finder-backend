package com.ssst.homefinderbackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    public ReviewDto createReview(ReviewDto review) {
        review.setId(111L);
        review.setTitle("Perfect house!!! Love it !! ;Ppp");
        return review;
    }

    public List<ReviewDto> getReviewList() {
        List<ReviewDto> result = new ArrayList<>();
        ReviewDto x = new ReviewDto(222L, "Perfffff", "Love it, Barbie dream house", 5,1L, 1L);
        ReviewDto y = new ReviewDto(777L, "Looks dirty",  "I wanted to like it, but it looks like cockroaches live here ;((",1,2L, 4L);
        result.add(x);
        result.add(y);

        return result;
    }

    public List<ReviewDto> getReviewsByRealEstateId(long realEstateId) {
        List<ReviewDto> results = new ArrayList<>();
        ReviewDto x = new ReviewDto(222L, "Perfffff", "Love it, Barbie dream house", 5,333L, 1L);
        ReviewDto y = new ReviewDto(777L, "Looks dirty",  "I wanted to like it, but it looks like cockroaches live here ;((",1,333L, 4L);
        results.add(x);
        results.add(y);
        return results.stream()
                .filter(result -> result.getRealEstateId() == realEstateId)
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByUserId(long userId) {
        List<ReviewDto> results = new ArrayList<>();
        ReviewDto x = new ReviewDto(222L, "Perfffff", "Love it, Barbie dream house", 5,23L, 111L);
        ReviewDto y = new ReviewDto(777L, "Looks dirty",  "I wanted to like it, but it looks like cockroaches live here ;((",1,44L, 111L);
        results.add(x);
        results.add(y);
        return results.stream()
                .filter(result -> result.getUserId() == userId)
                .collect(Collectors.toList());
    }


    public void deleteReview(long id) {
        System.out.println("Deleted " + id);
    }
}
