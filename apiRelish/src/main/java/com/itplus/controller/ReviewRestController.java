package com.itplus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itplus.entity.Review;
import com.itplus.service.ReviewService;

@RestController
public class ReviewRestController {
	@Autowired
	ReviewService reviewService;
	//Lấy thông tin về 1 bài review game
	@RequestMapping(value = "review/{id}")
	public String getReviewById(@PathVariable int id) {
		List<Review> list = reviewService.getReviewById(id);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	//Đếm số lượng các bài review liên quan theo id
	@RequestMapping(value = "count/review/{id}")
	public String getNumberReivew(@PathVariable int id) {
		List<Review> listNumberReview = reviewService.getNumberReview(id);
		Gson gson = new Gson();
		return gson.toJson(listNumberReview);
	}
	
	//Đếm số lượng số người đã review 1 bài viết theo id
	@RequestMapping(value = "count/reviewUser/{id}")
	public String getNumberUserReivew(@PathVariable int id) {
		List<Review> listNumberUserReview = reviewService.getNumberUserReview(id);
		Gson gson = new Gson();
		return gson.toJson(listNumberUserReview);
	}
}
