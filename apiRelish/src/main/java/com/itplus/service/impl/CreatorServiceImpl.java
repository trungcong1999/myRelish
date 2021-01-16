package com.itplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itplus.dao.impl.CreatorDao;
import com.itplus.entity.Creator;
import com.itplus.service.CreatorService;

@Service
public class CreatorServiceImpl implements CreatorService{
	@Autowired
	CreatorDao creatorDao;
	@Override
	public List<Creator> getAll() {
		// TODO Auto-generated method stub
		return creatorDao.getAll();
	}

}
