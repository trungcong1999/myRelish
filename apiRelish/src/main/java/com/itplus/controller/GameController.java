package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itplus.entity.Game;
import com.itplus.service.GameService;

@Controller
public class GameController {
	@Autowired
	GameService gameService;
	//lấy danh sách game cả name creator
	@RequestMapping(value = "pages/game/list", method = RequestMethod.GET)
	public String getGameListPage(HttpServletRequest request) {
		List<Game> list = gameService.getAllGameName();
		request.setAttribute("listGame", list);
		return "pages/game/list";
	}
	
	//thêm Game
		@RequestMapping(value = "/pages/game/addGame",method = RequestMethod.GET)
		public String showAddGame(Model model,HttpServletRequest request) {
			Game game = new Game();
			List<Game> list = gameService.getAllGameName();
			request.setAttribute("listGame", list);
			model.addAttribute("addGame", game);
			return "pages/game/addGame";
			
		}
		
		@RequestMapping(value = "/pages/game/addGame",method = RequestMethod.POST)
		public String AddGame(@ModelAttribute("addGame") Game game, @RequestParam String name,@RequestParam String header_img,@RequestParam String description,
				@RequestParam String metacritic_score,@RequestParam String publisher_id,@RequestParam String developer_id, @RequestParam String release_date ) {
			gameService.addGame(game);
			return "redirect:list";
			
		}
		//Sửa thông tin game
		  @RequestMapping(value = "/pages/game/editGame/{id}", method = RequestMethod.GET)
		  public String editGame(@PathVariable int id, Model model,HttpServletRequest request) {
			  Game game = gameService.getGameById(id);
			  List<Game> list = gameService.getAllGameName();
			request.setAttribute("listGame", list);
			  model.addAttribute("editGame", game);
			  return "/pages/game/editGame";
		  }
		  
		  @RequestMapping(value = "/pages/game/editGame", method = RequestMethod.POST)
		  public String editGameSave(@ModelAttribute("editGame") Game game) {
			  gameService.updateGame(game);
			  return "redirect:list";
		  }
		  // Xóa game
		  
		  @RequestMapping(value = "/pages/game/delete/{id}", method = RequestMethod.GET)
		  public String deleteGame(@PathVariable int id) {
			gameService.deleteGame(id);
			return "redirect:/pages/game/list";
			  
		  }
}
