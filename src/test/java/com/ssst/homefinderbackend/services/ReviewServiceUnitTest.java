package com.ssst.homefinderbackend.services;

import com.ssst.homefinderbackend.data.ReviewTest;
import com.ssst.homefinderbackend.data.entity.ReviewEntity;
import com.ssst.homefinderbackend.data.repository.ReviewRepo;
import com.ssst.homefinderbackend.model.ReviewDto;
import com.ssst.homefinderbackend.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ReviewServiceUnitTest {

    @MockBean
    private ReviewRepo reviewRepository;

    @TestConfiguration
    static class ReviewServiceTestConfiguration {

        @Bean
        @Primary
        public ReviewService reviewService(ReviewRepo reviewRepository) {
            return new ReviewService(reviewRepository);
        }
    }

    @Autowired
    private ReviewService reviewService;

    @Test
    public void givenReviews_whenGetReviewList_thenListShouldBeFound() {
        // arrange
        Mockito.when(reviewRepository.findAll())
                .thenReturn(List.of(ReviewTest.review()));

        // act
        List<ReviewEntity> returnedList = reviewService.getReviewList();

        // assert
        assertThat(returnedList).hasSize(1);
    }

    @Test
    public void givenNoReviews_whenGetReviewList_thenListShouldBeEmpty() {
        // act
        List<ReviewEntity> returnedList = reviewService.getReviewList();

        // assert
        assertThat(returnedList).isEmpty();
    }

    @Test
    public void givenValidId_whenGetReview_thenReviewShouldBeFound() throws Exception {
        // arrange
        Integer id = 1;
        Mockito.when(reviewRepository.findById(id))
                .thenReturn(Optional.of(ReviewTest.review()));

        // act
        ReviewEntity resultReview = reviewService.getReview(id);

        // assert
        assertThat(resultReview.getTitle()).isEqualTo("Ewww");
    }

    @Test
    public void givenInvalidId_whenGetReview_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> reviewService.getReview(2))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Could not find");
    }

    @Test
    public void givenReview_whenCreateReview_thenReviewIsReturned() throws Exception {
        // arrange
        ReviewDto inputReviewDto = ReviewTest.reviewDto1();
        ReviewEntity outputReview = ReviewTest.review();

        Mockito.when(reviewRepository.save(any(ReviewEntity.class)))
                .thenReturn(outputReview);

        // act
        ReviewEntity resultReview = reviewService.createReview(inputReviewDto);

        // assert
        assertThat(resultReview).isNotNull();
        assertThat(resultReview.getTitle()).isEqualTo(inputReviewDto.getTitle());
        assertThat(resultReview.getId()).isNotEqualTo(0);
    }

    @Test
    public void givenReview_whenCreateReview_thenRepositoryCalled() throws Exception {
        // arrange
        ReviewDto reviewDto = ReviewTest.reviewDto2();

        Mockito.when(reviewRepository.save(any(ReviewEntity.class)))
                .thenReturn(ReviewTest.review());

        // act
        reviewService.createReview(reviewDto);

        // assert
        verify(reviewRepository, times(1)).save(any(ReviewEntity.class));
    }

    @Test
    public void givenReviewAndValidId_whenUpdate_thenReviewReturned() throws Exception {
        // arrange
        ReviewDto inputReviewDto = ReviewTest.reviewDto1();
        inputReviewDto.setId(0); // reset id
        Integer id = 1;
        ReviewEntity outputReview = ReviewTest.review();
        outputReview.setId(id);

        Mockito.when(reviewRepository.findById(id))
                .thenReturn(Optional.of(outputReview));
        Mockito.when(reviewRepository.save(any(ReviewEntity.class)))
                .thenReturn(outputReview);

        // act
        ReviewEntity resultReview = reviewService.updateReview(id, inputReviewDto);

        // assert
        assertThat(resultReview).isNotNull();
        assertThat(resultReview.getTitle()).isEqualTo(inputReviewDto.getTitle());
        assertThat(resultReview.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> reviewService.updateReview(2, ReviewTest.reviewDto2()))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Could not find");
    }

    @Test
    public void givenReview_whenDelete_thenRepositoryCalled() throws Exception {
        // arrange
        Integer id = 1;

        // act
        reviewService.deleteReview(id);

        // assert
        verify(reviewRepository, times(1)).deleteById(id);
    }



}
