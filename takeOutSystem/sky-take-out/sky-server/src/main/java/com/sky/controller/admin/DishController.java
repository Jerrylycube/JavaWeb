package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController("adminDishController")
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {
	
	
	@Autowired
	private DishService dishService;
	@Autowired
	private RedisTemplate redisTemplate;
	
	/**
	 * 清理缓存
	 */
	private void clearCache(String pattern) {
		log.info("清理缓存");
		Set keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}
	
	/**
	 * 分页展示菜品
	 *
	 */
	@GetMapping("/page")
	@ApiOperation("分页查询")
	public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
		log.info("分页查询：{}", dishPageQueryDTO);
		PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
		return Result.success(pageResult);
	}
	
	/**
	 * 新增菜品
	 */
	@PostMapping
	@ApiOperation("新增菜品")
	public Result<String> add(@RequestBody DishDTO dishDTO) {
		log.info("新增菜品：{}", dishDTO);
		dishService.add(dishDTO);
		
		// 清理缓存
		Long categoryId = dishDTO.getCategoryId();
		clearCache("dish_" + categoryId);
		return Result.success();
	}
	
	/**
	 * 根据id查询菜品信息
	 */
	@GetMapping("/{id}")
	@ApiOperation("根据id查询菜品信息")
	public Result<DishVO> getById(@PathVariable Long id) {
		log.info("根据id查询菜品信息：{}", id);
		DishVO dishVO = dishService.getByIdWithFlavors(id);
		return Result.success(dishVO);
	}
	
	/**
	 * 修改菜品
	 */
	@PutMapping
	@ApiOperation("修改菜品")
	public Result<String> update(@RequestBody DishDTO dishDTO) {
		log.info("修改菜品：{}", dishDTO);
		dishService.update(dishDTO);
		// 清理全部缓存,因为前端展示的是整个分类下的菜品,不是一个菜品
		// 那边是不知道具体哪个改变了的,所以需要重新加载一次缓存
		clearCache("dish_*");
		return Result.success();
	}
	
	/**
	 * 起售和停售
	 */
	@PostMapping("status/{status}")
	@ApiOperation("起售和停售")
	public Result<String> startOrStop(@PathVariable Integer status, Long id) {
		log.info("起售或停售：{}", id);
		dishService.startOrStop(status, id);
		clearCache("dish_*");
		return Result.success();
	}
	
	/**
	 * 批量删除
	 */
	@DeleteMapping
	@ApiOperation("批量删除")
	public Result<String> delete(@RequestParam List<Long> ids) {
		log.info("批量删除：{}", ids);
		dishService.delete(ids);
		clearCache("dish_*");
		return Result.success();
	}
	
	/**
	 * 根据分类id查询菜品
	 */
	@GetMapping("/list")
	@ApiOperation("根据分类id查询菜品")
	public Result<List<Dish>> getDishByCategoryId(Long categoryId) {
		log.info("根据分类id:{} 查询", categoryId);
		List<Dish> dishes = dishService.getDishByCategoryId(categoryId);
		return Result.success(dishes);
	}
	
	
}
