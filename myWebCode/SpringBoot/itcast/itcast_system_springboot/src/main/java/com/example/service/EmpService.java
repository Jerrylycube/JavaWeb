package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
	/**
	 * 根据页码查找
	 *
	 * @return
	 */
	PageBean findInPage(Integer page, Integer pageSize, Short gender, String name, LocalDate begin, LocalDate end);
	
	/**
	 * 根据id删除
	 *
	 * @param ids
	 */
	void delete(List<Integer> ids);
	
	/**
	 * 新添员工
	 *
	 * @param emp
	 */
	void add(Emp emp);
}
