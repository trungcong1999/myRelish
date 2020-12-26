package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.GameDao;
import com.itplus.entity.Game;
import com.itplus.service.GameService;

@Service
public class GameServiceImpl implements GameService{
	@Autowired
	GameDao gameDao;

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



	
	

}
