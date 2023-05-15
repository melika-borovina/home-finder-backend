package com.ssst.homefinderbackend.data;

import com.ssst.homefinderbackend.data.entity.RealEstateEntity;
import com.ssst.homefinderbackend.data.entity.ReviewEntity;
import com.ssst.homefinderbackend.model.ReviewDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewTest {

    private static Integer id1 = 1;
    private static String title1 = "Ewww";
    private static String description1 = "Hated it";
    private static RealEstateEntity realestate1;

    static {
        realestate1 = new RealEstateEntity();
        realestate1.setId(1);
    }

    public static ReviewEntity review() {
        ReviewEntity review = new ReviewEntity();
        review.setId(id1);
        review.setTitle(title1);
        review.setDescription(description1);
        review.setRealEstateId(realestate1);

        return review;
    }

    public static ReviewDto reviewDto1() {
        return new ReviewDto(id1, title1, description1, realestate1.getId());
    }

    public static ReviewDto reviewDto2() {
        return new ReviewDto(3, "Loved it", "Loveeee", realestate1.getId());
    }
}
