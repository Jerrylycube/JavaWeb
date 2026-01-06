package com.example.service.impl;

import com.example.annotion.Log;
import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpMapper empMapper;
	
	@Log
	@Override
	public PageBean findInPage(Integer page, Integer pageSize, Short gender, String name, LocalDate begin, LocalDate end) {
//		Long total = empMapper.fingInPageTotal();
//		List<Emp> rows = empMapper.findInPageRows((page - 1) * pageSize, pageSize);
//
//		return new PageBean(total, rows);
		
		// 设置分页参数
		PageHelper.startPage(page, pageSize);
		List<Emp> empList = empMapper.list(gender, name, begin, end);
		// 获取分页结果
		Page<Emp> p = (Page<Emp>) empList;
		//封装PageBean
		PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
		return pageBean;
	}
	
	@Log
	@Override
	public void delete(List<Integer> ids) {
//		for (int i = 0; i < ids.length; i++) {
//			empMapper.delete(ids[i]);
//		}
		empMapper.delete(ids);
		
	}
	
	@Log
	@Override
	public void add(Emp emp) {
		emp.setCreateTime(LocalDateTime.now());
		emp.setUpdateTime(LocalDateTime.now());
		empMapper.add(emp);
	}
	
	@Log
	@Override
	public void update(Emp emp) {
		emp.setUpdateTime(LocalDateTime.now());
		empMapper.update(emp);
	}
	
	@Log
	@Override
	public Emp findById(Integer id) {
		Emp emp = empMapper.findById(id);
		return emp;
	}
	
	@Override
	public Emp login(Emp emp) {
		Emp loginEmp = empMapper.getByUsernameAndPassword(emp);
		//返回查询结果给Controller
		return loginEmp;
	}
	
}
