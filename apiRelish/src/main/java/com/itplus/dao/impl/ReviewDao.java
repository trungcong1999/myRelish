package com.itplus.dao.impl;

import java.util.List;

import com.itplus.entity.Review;

public interface ReviewDao {
	List<Review> getReviewById(int id);
	List<Review> getNumberReview(int id);
	List<Review> getNumberUserReview(int id);
}
