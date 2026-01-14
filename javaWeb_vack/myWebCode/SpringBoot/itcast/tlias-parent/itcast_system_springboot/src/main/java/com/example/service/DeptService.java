package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
	/**
	 * 用于查询全部部门列表
	 *
	 * @return
	 */
	List<Dept> list();
	
	void delete(Integer id);
	
	void add(Dept dept);
	
	void update(Dept dept);
	
	Dept findById(Integer id);
}
