package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.ReviewDao;
import com.itplus.entity.Review;
import com.itplus.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDao reviewDao;
	@Override
	public List<Review> getReviewById(int id) {
		// TODO Auto-generated method stub
		return reviewDao.getReviewById(id);
	}
	@Override
	public List<Review> getNumberReview(int id) {
		// TODO Auto-generated method stub
		return reviewDao.getNumberReview(id);
	}
	@Override
	public List<Review> getNumberUserReview(int id) {
		// TODO Auto-generated method stub
		return reviewDao.getNumberUserReview(id);
	}
	@Override
	public List<Review> getLatestReview(int limit) {
		return reviewDao.getLatestReview(limit);
	}

}
