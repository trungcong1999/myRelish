package com.itplus.dao.impl;

import java.util.List;

import com.itplus.entity.Game;

public interface GameDao {
	List<Game> getAll();
	void addGame(Game game);
	void updateGame(Game game);
	void deleteGame(int id);
	Game getGameById(int id);
	List<Game> findByName(String name);
	List<Game> getLatestProducts(int limit);
	List<Game> getTagId(int id);
	List<Game> getReviewByIdUser(int id);
	List<Game> getRadomReviewById(int id);
	List<Game> getAllGameName();
}
