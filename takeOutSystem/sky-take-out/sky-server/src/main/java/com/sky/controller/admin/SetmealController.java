package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("adminSetmealController")
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐管理")
public class SetmealController {
	@Autowired
	private SetmealService setmealService;
	
	/**
	 * 套餐分页查询
	 */
	@GetMapping("/page")
	@ApiOperation("分页查询套餐")
	public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
		log.info("分页查询套餐:{}", setmealPageQueryDTO);
		PageResult pageResult = setmealService.page(setmealPageQueryDTO);
		return Result.success(pageResult);
	}
	
	/**
	 * 新增套餐
	 */
	@PostMapping
	@ApiOperation("新增套餐")
	@CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
	public Result<String> add(@RequestBody SetmealDTO setmealDTO) {
		log.info("新增套餐:{}", setmealDTO);
		setmealService.add(setmealDTO);
		return Result.success();
	}
	
	/**
	 * 根据套餐id查询
	 */
	@GetMapping("/{id}")
	@ApiOperation("根据id查询套餐信息")
	public Result<SetmealDTO> getById(@PathVariable Long id) {
		log.info("根据id查询套餐信息:{}", id);
		SetmealDTO setmealDTO = setmealService.getByIdWithDishes(id);
		return Result.success(setmealDTO);
	}
	
	/**
	 * 修改套餐
	 */
	@PutMapping
	@ApiOperation("修改套餐")
	@CacheEvict(cacheNames = "setmealCache", allEntries = true)
	public Result<String> update(@RequestBody SetmealDTO setmealDTO) {
		log.info("修改套餐:{}", setmealDTO);
		setmealService.update(setmealDTO);
		return Result.success();
	}
	
	/**
	 * 套餐起售、停售
	 */
	@PostMapping("/status/{status}")
	@ApiOperation("起售、停售套餐")
	@CacheEvict(cacheNames = "setmealCache", allEntries = true)
	public Result<String> startOrStop(@PathVariable Integer status, Long id) {
		log.info("起售或停售套餐:{},{}", status, id);
		setmealService.startOrStop(status, id);
		return Result.success();
	}
	
	/**
	 * 批量删除套餐
	 */
	@DeleteMapping
	@ApiOperation("批量删除套餐")
	@CacheEvict(cacheNames = "setmealCache", allEntries = true)
	public Result<String> delete(@RequestParam List<Long> ids) {
		log.info("批量删除套餐:{}", ids);
		setmealService.delete(ids);
		return Result.success();
	}
	
}
