package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.CountObject;
import com.itplus.entity.Creator;
import com.itplus.entity.Game;
import com.itplus.entity.GameCriteria;
import com.itplus.entity.GameCriteriaReview;
import com.itplus.entity.GameInfo;
import com.itplus.entity.GameReviewArticle;
@Service
public interface GameService {
	List<Game> getAll();
	void addGame(Game game);
	void updateGame(Game game);
	void deleteGame(int id);
	List<Game> getGameById(int id);
	List<Game> findByName(String name);
	List<Game> getLatestProducts(int limit);
	List<Game> getTagId(int id);
	List<Game> getReviewByIdUser(int id);
	List<Game> getRadomReviewById(int id);
	
	GameInfo getGameInfoById(int gameId);
	List<GameCriteriaReview> getAllCriteriaReviews(int userId, int gameId);
	List<GameReviewArticle> getAllReviewArticles(int userId, int gameId);
	CountObject checkIsGameInCollecion(int userId, int gameId);
	List<Game> getAllGameName();
	List<Creator> getAllCreator();
	List<GameCriteria> getAllCriterias();
	
	boolean addCriteriaReview(GameCriteriaReview gameCriteriaReview);
	boolean editCriteriaReview(GameCriteriaReview gameCriteriaReview);
	boolean deleteCriteriaReview(GameCriteriaReview gameCriteriaReview);
}
