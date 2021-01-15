package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.CreatorDao;
import com.itplus.dao.impl.GameDao;
import com.itplus.entity.CountObject;
import com.itplus.entity.Creator;
import com.itplus.entity.FloatValueObject;
import com.itplus.entity.Game;
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
	public Game getGameById(int id) {
		// TODO Auto-generated method stub
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
	
	// API
	
	@Override
	public GameInfo getGameInfoById(int gameId) {
		Game game = gameDao.getGameById(gameId);
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
	public List<GameCriteriaReview> getAllCriteria(int userId, int gameId) {
		// TODO Auto-generated method stub
		return gameDao.getAllCriteria(userId, gameId);
	}

	@Override
	public List<GameReviewArticle> getAllReviewArticles(int userId, int gameId) {
		// TODO Auto-generated method stub
		return gameDao.getAllReviewArticles(userId, gameId);
	}
}
