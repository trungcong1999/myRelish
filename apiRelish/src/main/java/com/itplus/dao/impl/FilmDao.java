package com.itplus.dao.impl;

import java.util.List;

import com.itplus.entity.Creator;
import com.itplus.entity.Film;
import com.itplus.entity.Game;

public interface FilmDao {
	List<Film> getAll();
	void addFilm(Film film);
	void updateFilm(Film film);
	void deleteFilm(int id);
	Film getFilmById(int id);
	List<Film> findByName(String name);
	List<Film> getLatestProducts(int limit);
	List<Film> getAllCreator();
	List<Creator> getAllCreators();
}
