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
import com.itplus.entity.Game;
import com.itplus.service.GameService;

@RestController
public class GameRestController {
	@Autowired
	GameService gameService;

	//Danh sách tất cả các game
	@RequestMapping(value = "search/name/game/", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
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
	@RequestMapping(value = "ws-delete-game/{id}", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
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
		@RequestMapping(value = "search/name/game/{name}",produces = "text/plain;charset=UTF-8")
		public String getGameByName(HttpServletRequest request,@PathVariable String name) {
			List<Game> games = gameService.findByName(name);
			request.setAttribute("listGameName", games);
			Gson gson = new Gson();
			return gson.toJson(games);
		}
		
	//Lấy sản phầm mới nhất
		@RequestMapping(value = "show/lastGame/{limit}",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
		public String getLatesProductGame(HttpServletRequest request,@PathVariable int limit) {
			List<Game> gameLates = gameService.getLatestProducts(limit);
			request.setAttribute("listLatesProduct", gameLates);
			Gson gson = new Gson();
			return gson.toJson(gameLates);
		}
		
	//Hiện danh sách các game có cùng 1 tag
		@RequestMapping(value = "game/tag/{id}",produces = "text/plain;charset=UTF-8")
		public String getTagGame(@PathVariable int id) {
			List<Game> tagList = gameService.getTagId(id);
			Gson gson = new Gson();
			return gson.toJson(tagList);
		}
		
	//Lấy danh sách các game đã review của 1 người dùng (dựa vào id người dùng)
		@RequestMapping(value = "game/reivew/user/{id}")
		public String getReviewByIdUser(@PathVariable int id) {
			List<Game> gamesIdUser = gameService.getReviewByIdUser(id);
			Gson gson = new Gson();
			return gson.toJson(gamesIdUser);
		}
		
	//Lấy thông tin về 1 game dựa vào id,kèm theo overall_score của một số bài review ngẫu nhiên
		@RequestMapping(value = "game/reivew/user/radom/{id}",produces = "text/plain;charset=UTF-8")
		public String getRadomReviewById(@PathVariable int id) {
			List<Game> gamesIdUser = gameService.getRadomReviewById(id);
			Gson gson = new Gson();
			return gson.toJson(gamesIdUser);
		}
		
		
		
	
}

