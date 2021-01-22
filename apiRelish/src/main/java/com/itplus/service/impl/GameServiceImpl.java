package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.CreatorDao;
import com.itplus.dao.impl.GameDao;
import com.itplus.entity.CountObject;
import com.itplus.entity.Creator;
import com.itplus.entity.FloatValueObject;
import com.itplus.entity.Creator;
import com.itplus.entity.Game;
import com.itplus.entity.GameCriteria;
import com.itplus.entity.GameCriteriaReview;
import com.itplus.entity.GameInfo;
import com.itplus.entity.GameReviewArticle;
import com.itplus.service.GameService;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	GameDao gameDao;
	@Autowired
	CreatorDao creatorDao;

	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		return gameDao.getAll();
	}

	@Override
	public void addGame(Game game) {
		// TODO Auto-generated method stub
		gameDao.addGame(game);
	}

	@Override
	public void updateGame(Game game) {
		// TODO Auto-generated method stub
		gameDao.updateGame(game);
	}

	@Override
	public void deleteGame(int id) {
		// TODO Auto-generated method stub
		gameDao.deleteGame(id);
	}

	@Override
	public List<Game> getGameById(int id) {
		return gameDao.getGameById(id);
	}

	@Override
	public List<Game> findByName(String name) {
		// TODO Auto-generated method stub
		return gameDao.findByName(name);
	}

	@Override
	public List<Game> getLatestProducts(int limit) {
		// TODO Auto-generated method stub
		return gameDao.getLatestProducts(limit);
	}

	@Override
	public List<Game> getTagId(int id) {
		// TODO Auto-generated method stub
		return gameDao.getTagId(id);
	}

	@Override
	public List<Game> getReviewByIdUser(int id) {
		// TODO Auto-generated method stub
		return gameDao.getReviewByIdUser(id);
	}

	@Override
	public List<Game> getRadomReviewById(int id) {
		// TODO Auto-generated method stub
		return gameDao.getRadomReviewById(id);
	}

	@Override
	public List<Game> getAllGameName() {
		// TODO Auto-generated method stub
		return gameDao.getAllGameName();
	}

	@Override
	public List<Creator> getAllCreator() {
		// TODO Auto-generated method stub
		return gameDao.getAllCreator();
	}



	
	// API
	
	@Override
	public GameInfo getGameInfoById(int gameId) {
		List<Game> result = gameDao.getGameById(gameId);
		if (result.size() <= 0) return null;
		Game game = result.get(0);
		Creator developer = creatorDao.getById(game.getDeveloper_id());
		Creator publisher = creatorDao.getById(game.getPublisher_id());
		CountObject countReviewArticles = gameDao.countReviewArticlesByGameId(gameId);
		CountObject countInCollection = gameDao.countInPeopleCollectionByGameId(gameId);
		FloatValueObject avgScore = gameDao.getAverageScoreByGameId(gameId);
		return (new GameInfo(
				game.getId(),
				game.getName(),
				game.getRelease_date(),
				game.getMetacritic_score(),
				game.getDescription(),
				game.getHeader_img(),
				publisher.getName(),
				developer.getName(),
				countInCollection.getCount_value(),
				countReviewArticles.getCount_value(),
				avgScore.getValue()
				));
	}

	@Override
	public List<GameCriteriaReview> getAllCriteriaReviews(int userId, int gameId) {
		return gameDao.getAllCriteriaReviews(userId, gameId);
	}

	@Override
	public List<GameReviewArticle> getAllReviewArticles(int userId, int gameId) {
		return gameDao.getAllReviewArticles(userId, gameId);
	}

	@Override
	public CountObject checkIsGameInCollecion(int userId, int gameId) {
		return gameDao.checkIsGameInCollection(gameId, userId);
	}

	@Override
	public List<GameCriteria> getAllCriterias() {
		return gameDao.getAllCriterias();
	}

	@Override
	public boolean addCriteriaReview(GameCriteriaReview gameCriteriaReview) {
		return gameDao.addCriteriaReview(gameCriteriaReview);
	}

	@Override
	public boolean editCriteriaReview(GameCriteriaReview gameCriteriaReview) {
		return gameDao.editCriteriaReview(gameCriteriaReview);
	}

	@Override
	public boolean deleteCriteriaReview(GameCriteriaReview gameCriteriaReview) {
		return gameDao.deleteCriteriaReview(gameCriteriaReview);
	}
}
