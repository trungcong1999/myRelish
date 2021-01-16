package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.TruyenDao;
import com.itplus.entity.Creator;
import com.itplus.entity.Truyen;
import com.itplus.service.TruyenService;

@Service
public class TruyenServiceImpl implements TruyenService{
	@Autowired
	TruyenDao truyenDao;
	
	@Override
	public List<Truyen> getAll() {
		// TODO Auto-generated method stub
		return truyenDao.getAll();
	}

	@Override
	public void addGTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		truyenDao.addGTruyen(truyen);
	}

	@Override
	public void updateTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		truyenDao.updateTruyen(truyen);
	}

	@Override
	public void deleteTruyen(int id) {
		// TODO Auto-generated method stub
		truyenDao.deleteTruyen(id);
	}

	@Override
	public Truyen getTruyenById(int id) {
		// TODO Auto-generated method stub
		return truyenDao.getTruyenById(id);
	}

	@Override
	public List<Truyen> findByName(String name) {
		// TODO Auto-generated method stub
		return truyenDao.findByName(name);
	}

	@Override
	public List<Truyen> getLatestProducts(int limit) {
		return truyenDao.getLatestProducts(limit);
	}
	public List<Truyen> getAllWithTG() {
		// TODO Auto-generated method stub
		return truyenDao.getAllWithTG();
	}

	@Override
	public List<Creator> getAllCreator() {
		// TODO Auto-generated method stub
		return truyenDao.getAllCreator();
	}

	

}
