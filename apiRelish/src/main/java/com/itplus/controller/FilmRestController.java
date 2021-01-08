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
import com.itplus.entity.Film;
import com.itplus.service.FilmService;

@RestController
public class FilmRestController {
	@Autowired
	FilmService filmService;

	// Danh sách tất cả các phim
	@RequestMapping(value = "search/name/film/", method = RequestMethod.GET)
	public String getFilmList(HttpServletRequest request) {
		List<Film> list = filmService.getAll();
		request.setAttribute("listFilm", list);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// Thêm phim
	@RequestMapping(value = "ws-add-new-film", method = RequestMethod.POST)
	public String addFilm(@RequestBody Film film) {
		try {
			filmService.addFilm(film);
			return "add film success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add film error";
		}

	}

	// Sửa phim
	@RequestMapping(value = "ws-edit-film", method = RequestMethod.POST)
	public String save(@RequestBody Film film) {
		try {
			filmService.updateFilm(film);
			return "add film success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add film error";
		}
	}

	// xóa phim theo id
	@RequestMapping(value = "/ws-delete-film/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		try {
			filmService.deleteFilm(id);
			return "add film success";
		} catch (Exception e) {
			// TODO: handle exception
			return "add film error";
		}
	}

	// tìm theo tên phim
	@RequestMapping(value = "/search/name/film/{name}")
	public String getFilmByName(HttpServletRequest request, @PathVariable String name) {
		List<Film> films = filmService.findByName(name);
		request.setAttribute("listFilmName", films);
		Gson gson = new Gson();
		return gson.toJson(films);
	}

	
}
