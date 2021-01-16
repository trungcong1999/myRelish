package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.Film;

@Service
public interface FilmService {
	List<Film> getAll();
	void addFilm(Film film);
	void updateFilm(Film film);
	void deleteFilm(int id);
	Film getFilmById(int id);
	List<Film> findByName(String name);
	List<Film> getAllCreator();
}
