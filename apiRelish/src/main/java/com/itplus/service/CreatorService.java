package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.Creator;

@Service
public interface CreatorService {
	List<Creator> getAll();
}
