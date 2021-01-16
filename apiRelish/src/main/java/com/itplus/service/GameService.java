package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.Creator;
import com.itplus.entity.Game;
@Service
public interface GameService {
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
	List<Creator> getAllCreator();
}
