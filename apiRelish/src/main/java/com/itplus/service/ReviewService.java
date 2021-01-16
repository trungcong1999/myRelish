package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.Review;

@Service
public interface ReviewService {
	List<Review> getReviewById(int id);
	List<Review> getNumberReview(int id);
	List<Review> getNumberUserReview(int id);
}
