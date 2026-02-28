package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.*;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
	@Autowired
	private DishMapper dishMapper;
	@Autowired
	private DishFlavorMapper dishFlavorMapper;
	@Autowired
	private SetmealDishMapper setmealDishMapper;
	@Autowired
	private SetmealMapper setmealMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
		PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
		Page<DishVO> page = dishMapper.page(dishPageQueryDTO);
		//使用xml中的左连接为菜品的分类名称赋值
		return new PageResult((long) page.getTotal(), page.getResult());
	}
	
	@Override
	public void add(DishDTO dishDTO) {
		Dish dish = new Dish();
		BeanUtils.copyProperties(dishDTO, dish);
		dishMapper.add(dish);
		//获取菜品的主键值
		Long dishId = dish.getId();
		//将口味数据批量插入
		List<DishFlavor> flavors = dishDTO.getFlavors();
		if (flavors != null && flavors.size() > 0) {
			//向口味表dish_flavor插入n条
			flavors.forEach(dishFlavor -> {
				dishFlavor.setDishId(dishId);
			});
			//批量插入
			dishFlavorMapper.insertBatch(flavors);
		}
		
	}
	
	@Override
	public DishVO getByIdWithFlavors(Long id) {
		DishVO dishVO = dishMapper.getByIdWithFlavors(id);
		dishVO.setFlavors(dishFlavorMapper.getByDishId(id));
		return dishVO;
	}
	
	@Override
	public void update(DishDTO dishDTO) {
		Dish dish = new Dish();
		BeanUtils.copyProperties(dishDTO, dish);
		dishMapper.update(dish);
		//遍历DTO中的口味数据,将其更新到口味表中
		List<DishFlavor> flavors = dishDTO.getFlavors();
		if (flavors != null && flavors.size() > 0) {
			//删除原有的口味数据
			dishFlavorMapper.deleteByDishId(dishDTO.getId());
			//批量插入新的口味数据
			flavors.forEach(dishFlavor -> {
				dishFlavor.setDishId(dishDTO.getId());
			});
			dishFlavorMapper.insertBatch(flavors);
		}
	}
	
	@Override
	public void startOrStop(Integer status, Long id) {
		Dish dish = Dish.builder()
				.status(status)
				.id(id)
				.build();
		if (status == StatusConstant.DISABLE) {
			// 如果是停售操作，还需要将包含当前菜品的套餐也停售
			List<Long> dishIds = new ArrayList<>();
			dishIds.add(id);
			// select setmeal_id from setmeal_dish where dish_id in (?,?,?)
			List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(dishIds);
			if (setmealIds != null && setmealIds.size() > 0) {
				for (Long setmealId : setmealIds) {
					Setmeal setmeal = Setmeal.builder()
							.id(setmealId)
							.status(StatusConstant.DISABLE)
							.build();
					setmealMapper.update(setmeal);
				}
			}
		}
		dishMapper.update(dish);
		
	}
	
	@Override
	//在删除菜品之前，需要先删除菜品表中的口味数据，再删除菜品数据
	public void delete(List<Long> ids) {
		//起售中的商品不能删除
		ids.forEach(id -> {
			if (dishMapper.getById(id).getStatus() == 1)
				throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
		});
		//被套餐表关联的也不能删除
		List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
		if (setmealIds != null && setmealIds.size() > 0) {
			//当前菜品有在售套餐，不能删除
			throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
		}
		dishFlavorMapper.deleteByDishIds(ids);
		dishMapper.delete(ids);
	}
	
	@Override
	public List<Dish> getDishByCategoryId(Long categoryId) {
		List<Dish> dishes = dishMapper.getDishByCategoryId(categoryId);
		return dishes;
	}
	
	
	/**
	 * 条件查询菜品和口味
	 *
	 * @param dish
	 * @return
	 */
	@Override
	public List<DishVO> listWithFlavor(Dish dish) {
		List<Dish> dishList = dishMapper.list(dish);
		
		List<DishVO> dishVOList = new ArrayList<>();
		
		for (Dish d : dishList) {
			DishVO dishVO = new DishVO();
			BeanUtils.copyProperties(d, dishVO);
			
			//根据菜品id查询对应的口味
			List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());
			
			dishVO.setFlavors(flavors);
			dishVOList.add(dishVO);
		}
		
		return dishVOList;
	}
}
