package com.company.reviews;


import com.company.company.Company;
import com.company.company.CompanyService;
import com.company.reviews.impl.ReviewServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements ReviewServiceImpl {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewService(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(String companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(String companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;

    }

    @Override
    public Review getReview(String companyId, String  reviewId) {
       return reviewRepository.findByCompanyId(companyId).stream().filter(review -> review.getId().equalsIgnoreCase(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(String companyId, String reviewId, Review review) {
        Company companyById = companyService.getCompanyById(companyId);
        if(companyById != null){
            review.setCompany(companyById);
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(String companyId, String reviewId) {
        Company companyById = companyService.getCompanyById(companyId);
        boolean review = reviewRepository.existsById(reviewId);
        if(companyById != null && review){
            Review reviewById = reviewRepository.findById(reviewId).orElse(null);
            Company company = reviewById.getCompany();
            company.getReviews().remove(reviewById);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
