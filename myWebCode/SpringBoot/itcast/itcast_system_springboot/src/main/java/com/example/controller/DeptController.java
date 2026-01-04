package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	
	@GetMapping
	public Result list() {
		log.info("查询成功");
		List<Dept> deptList = deptService.list();
		return Result.success(deptList);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		deptService.delete(id);
		log.info("删除部门{}", id);
		return Result.success();
	}
	
	@PostMapping
	public Result add(@RequestBody Dept dept) {
		deptService.add(dept);
		log.info("添加部门{}", dept.getName());
		return Result.success();
	}
	
	//PathVariable用于表示传递的变量,此处是id
	@GetMapping("/{id}")
	public Result get(@PathVariable Integer id) {
		Dept dept = deptService.findById(id);
		return Result.success(dept);
	}
	
	//RequestBody用于将传递进来的json格式封装成一个对象
	@PutMapping
	public Result update(@RequestBody Dept dept) {
		deptService.update(dept);
		log.info("修改部门{}", dept.getId(), "的名称为{}", dept.getName());
		return Result.success();
	}
	
	
}
