package com.company.reviews.impl;

import com.company.reviews.Review;

import java.util.List;

public interface ReviewServiceImpl {
    List<Review> getAllReviews(String companyId);
    boolean addReview(String companyId, Review review);
    Review getReview(String companyId, String reviewId);
    boolean updateReview(String companyId, String reviewId, Review review);
    boolean deleteReview(String companyId, String reviewId);
}
