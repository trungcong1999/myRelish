package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itplus.entity.CountObject;
import com.itplus.entity.Game;
import com.itplus.entity.GameCriteriaReview;
import com.itplus.entity.GameInfo;
import com.itplus.entity.GameReviewArticle;
import com.itplus.service.GameService;

@RestController
public class GameRestController {
	@Autowired
	GameService gameService;

	//Danh sách tất cả các game
	@RequestMapping(value = "search/name/game/", method = RequestMethod.GET)
	public String getGameList(HttpServletRequest request) {
		List<Game> list = gameService.getAll();
		request.setAttribute("listGame", list);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	//Thêm Game
	@RequestMapping(value = "ws-add-new-game", method = RequestMethod.POST)
	public String addGame(@RequestBody Game game) {
		try {
			gameService.addGame(game);
			return "add game success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add game error ";
		}

	}
	

	//Sửa Game
	@RequestMapping(value = "ws-edit-game", method = RequestMethod.POST)
	public String save(@RequestBody Game game) {
		try {
			gameService.updateGame(game);
			return "add game success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add game error";
		}
	}

	//Xóa Game
	@RequestMapping(value = "ws-delete-game/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			gameService.deleteGame(id);
			return "add game success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add game error";
		}
	}
	
	//tìm theo tên game
	@RequestMapping(value = "search/name/game/{name}", produces = "text/plain;charset=UTF-8")
	public String getGameByName(HttpServletRequest request,@PathVariable String name) {
		List<Game> games = gameService.findByName(name);
		request.setAttribute("listGameName", games);
		Gson gson = new Gson();
		return gson.toJson(games);
	}
		
	//Lấy sản phầm mới nhất
	@RequestMapping(value = "show/lastGame/{limit}",method = RequestMethod.GET)
	public String getLatesProductGame(HttpServletRequest request,@PathVariable int limit) {
		List<Game> gameLates = gameService.getLatestProducts(limit);
		request.setAttribute("listLatesProduct", gameLates);
		Gson gson = new Gson();
		return gson.toJson(gameLates);
	}
		
	//Hiện danh sách các game có cùng 1 tag
	@RequestMapping(value = "game/tag/{id}")
	public String getTagGame(HttpServletRequest request, @PathVariable int id) {
		List<Game> tagList = gameService.getTagId(id);
		Gson gson = new Gson();
		return gson.toJson(tagList);
	}
		
	//Lấy danh sách các game đã review của 1 người dùng (dựa vào id người dùng)
	@RequestMapping(value = "game/reivew/user/{id}")
	public String getReviewByIdUser(HttpServletRequest request, @PathVariable int id) {
		List<Game> gamesIdUser = gameService.getReviewByIdUser(id);
		Gson gson = new Gson();
		return gson.toJson(gamesIdUser);
	}
		
	//Lấy thông tin về 1 game dựa vào id,kèm theo overall_score của một số bài review ngẫu nhiên
	@RequestMapping(value = "game/review/user/random/{id}")
	public String getRadomReviewById(HttpServletRequest request, @PathVariable int id) {
		List<Game> gamesIdUser = gameService.getRadomReviewById(id);
		Gson gson = new Gson();
		return gson.toJson(gamesIdUser);
	}
	
	@RequestMapping(value = "game/info/{gameId}",method = RequestMethod.GET)
	public String getGameInfoById(HttpServletRequest request, @PathVariable int gameId) {
		GameInfo result = gameService.getGameInfoById(gameId);
		Gson gson = new Gson();
		return gson.toJson(result);
	}
		
	// Lấy tất cả các tiêu chính mà người dùng có userId đã đánh giá về game có gameId
	@RequestMapping(value = "game/{gameId}/user/{userId}/all-criteria-reviewed", produces = "text/plain;charset=UTF-8")
	public String getAllCriteriaReviewed(HttpServletRequest request, @PathVariable int gameId, @PathVariable int userId) {
		List<GameCriteriaReview> result = gameService.getAllCriteria(userId, gameId);
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	// Lấy tất cả các tiêu chính mà người dùng có userId đã đánh giá về game có gameId
	@RequestMapping(value = "game/{gameId}/user/{userId}/all-review-articles", produces = "text/plain;charset=UTF-8")
	public String getAllReviewArticle(HttpServletRequest request, @PathVariable int gameId, @PathVariable int userId) {
		List<GameReviewArticle> result = gameService.getAllReviewArticles(userId, gameId);
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	// Lấy tất cả các tiêu chính mà người dùng có userId đã đánh giá về game có gameId
		@RequestMapping(value = "game/{gameId}/user/{userId}/check-is-in-collection", produces = "text/plain;charset=UTF-8")
		public String checkIsGameInCollection(HttpServletRequest request, @PathVariable int gameId, @PathVariable int userId) {
			CountObject result = gameService.checkIsGameInCollecion(userId, gameId);
			Gson gson = new Gson();
			return gson.toJson(result);
		}
}

