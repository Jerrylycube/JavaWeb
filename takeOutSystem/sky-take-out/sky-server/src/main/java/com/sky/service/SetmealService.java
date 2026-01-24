package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface SetmealService {
	
	/**
	 * 分页查询套餐
	 *
	 * @param setmealPageQueryDTO
	 */
	PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);
	
	/**
	 * 添加套餐
	 *
	 * @param setmealDTO
	 */
	void add(SetmealDTO setmealDTO);
	
	/**
	 * 根据id查询套餐和套餐菜品关系
	 *
	 * @param id
	 * @return
	 */
	SetmealDTO getByIdWithDishes(Long id);
	
	/**
	 * 修改套餐
	 *
	 * @param setmealDTO
	 */
	void update(SetmealDTO setmealDTO);
	
	/**
	 * 启售或停售套餐
	 *
	 * @param status id
	 */
	void startOrStop(Integer status, Long id);
	
	/**
	 * 批量删除套餐
	 *
	 * @param ids
	 */
	void delete(List<Long> ids);
}
