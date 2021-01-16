package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itplus.entity.Review;
import com.itplus.entity.Truyen;
import com.itplus.service.ReviewService;

@RestController
public class ReviewRestController {
	@Autowired
	ReviewService reviewService;
	//Lấy thông tin về 1 bài review game
	@RequestMapping(value = "review/{id}", produces = "text/plain;charset=UTF-8")
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
	
	//Lấy review mới nhất
	@RequestMapping(value = "show/lastReview/{limit}",method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getLatesProductGame(HttpServletRequest request,@PathVariable int limit) {
		List<Review> gameLates = reviewService.getLatestReview(limit);
		request.setAttribute("listLatesProduct", gameLates);
		Gson gson = new Gson();
		return gson.toJson(gameLates);
	}
}
