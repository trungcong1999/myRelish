package com.itplus.dao.impl;
import com.itplus.entity.Creator;
import java.util.List;
import com.itplus.entity.Creator;

public interface CreatorDao {
	Creator getById(int id);
	List<Creator> getAll();
}
