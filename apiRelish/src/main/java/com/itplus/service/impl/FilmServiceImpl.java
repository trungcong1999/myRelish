package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.FilmDao;
import com.itplus.entity.Film;
import com.itplus.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService {
	@Autowired
	FilmDao filmDao;

	@Override
	public List<Film> getAll() {
		// TODO Auto-generated method stub
		return filmDao.getAll();
	}

	@Override
	public void addFilm(Film film) {
		// TODO Auto-generated method stub
		filmDao.addFilm(film);
	}

	@Override
	public void updateFilm(Film film) {
		// TODO Auto-generated method stub
		filmDao.updateFilm(film);
	}

	@Override
	public void deleteFilm(int id) {
		// TODO Auto-generated method stub
		filmDao.deleteFilm(id);
	}

	@Override
	public Film getFilmById(int id) {
		// TODO Auto-generated method stub
		return filmDao.getFilmById(id);
	}

	@Override
	public List<Film> findByName(String name) {
		// TODO Auto-generated method stub
		return filmDao.findByName(name);
	}

	@Override
	public List<Film> getLatestProducts(int limit) {
		return filmDao.getLatestProducts(limit);
	}



}
