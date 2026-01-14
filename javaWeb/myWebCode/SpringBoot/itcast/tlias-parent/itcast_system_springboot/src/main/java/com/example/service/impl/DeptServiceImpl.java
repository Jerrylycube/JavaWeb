package com.example.service.impl;

import com.example.annotion.Log;
import com.example.mapper.DeptMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private EmpMapper empMapper;
	
	@Log
	@Override
	public List<Dept> list() {
		
		return deptMapper.list();
	}
	
	//默认只有运行时异常才回滚
	//加上参数rollbackFor= Exception.class,即所有异常回滚
	@Transactional(rollbackFor = Exception.class)//交给Springboot的事务管理,要一整个执行完
	@Log
	@Override
	public void delete(Integer id) {
		deptMapper.delete(id);
		//根据部门id删除对应的员工
		empMapper.deleteById(id);
		
	}
	
	@Log
	@Override
	public void add(Dept dept) {
		dept.setCreateTime(LocalDateTime.now());
		dept.setUpdateTime(LocalDateTime.now());
		deptMapper.add(dept);
	}
	
	@Log
	@Override
	public void update(Dept dept) {
		dept.setUpdateTime(LocalDateTime.now());
		deptMapper.update(dept);
		
	}
	
	@Log
	@Override
	public Dept findById(Integer id) {
		return deptMapper.findById(id);
	}
}
