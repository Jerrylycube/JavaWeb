package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
	@Autowired
	private EmpService empService;
	
	/**
	 * 分页查询
	 */
	@GetMapping
	public Result findInPage(@RequestParam(defaultValue = "1") Integer page,
							 @RequestParam(defaultValue = "10") Integer pageSize, Short gender, String name,
							 @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,
							 @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end) {
		PageBean pageBean = empService.findInPage(page, pageSize, gender, name, begin, end);
		return Result.success(pageBean);
	}
	
	@DeleteMapping("/{ids}")
	public Result delete(@PathVariable List<Integer> ids) {
		empService.delete(ids);
		return Result.success();
	}
	
	@PostMapping
	public Result add(@RequestBody Emp emp) {
		empService.add(emp);
		return Result.success();
	}
}
