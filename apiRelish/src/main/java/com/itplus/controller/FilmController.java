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

import com.itplus.entity.Creator;
import com.itplus.entity.Film;
import com.itplus.service.FilmService;

@Controller
public class FilmController {
	@Autowired
	FilmService filmService;
	//lấy tất cả thông tin phim kèm tên creator
	@RequestMapping(value = "/admin/pages/film/list", method = RequestMethod.GET)
	public String getFilmList(HttpServletRequest request) {
		List<Film> listFilm = filmService.getAllCreator();
		request.setAttribute("listFilm", listFilm);
		return "/pages/film/list";	
	}
	//thêm phim
	@RequestMapping(value = "/admin/pages/film/addfilm",method = RequestMethod.GET)
	public String showAddFilm(Model model,HttpServletRequest request) {
		Film film = new Film();
		List<Creator> listFilm = filmService.getAllCreators();
		request.setAttribute("listFilm", listFilm);
		model.addAttribute("addFilm", film);
		return "/pages/film/addFilm";
		
	}
	
	@RequestMapping(value = "/admin/pages/film/addfilm",method = RequestMethod.POST)
	public String AddFilm(@ModelAttribute("addFilm") Film film, @RequestParam String name,@RequestParam String poster_img,@RequestParam String description,
			@RequestParam String writer_id,@RequestParam String director_id, @RequestParam String release_date ) {
		filmService.addFilm(film);
		return "redirect:list";
		
	}
	//Sửa thông tin phim
	  @RequestMapping(value = "/admin/pages/film/editFilm/{id}", method = RequestMethod.GET)
	  public String editFilm(@PathVariable int id, Model model,HttpServletRequest request) {
		  Film film = filmService.getFilmById(id);
		  List<Film> listFilm = filmService.getAllCreator();
		  request.setAttribute("listFilm", listFilm);
		  model.addAttribute("editFilm", film);
		  return "/pages/film/editFilm";
	  }
	  
	  @RequestMapping(value = "/admin/pages/film/editFilm", method = RequestMethod.POST)
	  public String editFilmSave(@ModelAttribute("editFilm") Film film) {
		  filmService.updateFilm(film);
		  return "redirect:list";
	  }
	  // Xóa film
	  
	  @RequestMapping(value = "/admin/pages/film/delete/{id}", method = RequestMethod.GET)
	  public String deleteFilm(@PathVariable int id) {
		filmService.deleteFilm(id);
		return "redirect:/pages/film/list";
		  
	  }
}
