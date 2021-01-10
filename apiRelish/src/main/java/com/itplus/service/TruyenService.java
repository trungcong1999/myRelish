package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.Truyen;
@Service
public interface TruyenService {
	List<Truyen> getAll();
	void addGTruyen(Truyen truyen);
	void updateTruyen(Truyen truyen);
	void deleteTruyen(int id);
	Truyen getTruyenById(int id);
	List<Truyen> findByName(String name);
}
