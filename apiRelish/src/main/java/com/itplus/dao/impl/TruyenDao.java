package com.itplus.dao.impl;

import java.util.List;

import com.itplus.entity.Truyen;

public interface TruyenDao {
	List<Truyen> getAll();
	void addGTruyen(Truyen truyen);
	void updateTruyen(Truyen truyen);
	void deleteTruyen(int id);
	Truyen getTruyenById(int id);
	List<Truyen> findByName(String name);
}
