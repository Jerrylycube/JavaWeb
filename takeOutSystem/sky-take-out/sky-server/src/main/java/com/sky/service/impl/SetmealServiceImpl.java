package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.setmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
	@Autowired
	private SetmealMapper setmealMapper;
	@Autowired
	private setmealDishMapper setmealDishMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private DishMapper dishMapper;
	
	@Override
	public PageResult page(SetmealPageQueryDTO setmealPageQueryDTO) {
		PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
		Page<SetmealVO> page = setmealMapper.page(setmealPageQueryDTO);
		//使用xml中的左连接为套餐名称赋值
		return new PageResult((long) page.getTotal(), page.getResult());
	}
	
	@Override
	public void add(SetmealDTO setmealDTO) {
		Setmeal setmeal = new Setmeal();
		BeanUtils.copyProperties(setmealDTO, setmeal);
		setmealMapper.add(setmeal);
		//所以上面的mapper操作要返回主键
		//获取套餐的主键值
		Long setmealId = setmeal.getId();
		//防止菜品为null
		if (setmealDTO.getSetmealDishes() != null && setmealDTO.getSetmealDishes().size() > 0) {
			for (SetmealDish setmealDish : setmealDTO.getSetmealDishes()) {
				setmealDish.setSetmealId(setmealId);
			}
			//将DTO中菜品依次插入到setmeal_dish这张中间表中
			//采用数据库批量插入,更快
			setmealDishMapper.insertBatch(setmealDTO.getSetmealDishes());
		}
		
	}
	
	@Override
	public SetmealDTO getByIdWithDishes(Long id) {
		SetmealDTO setmealDTO = new SetmealDTO();
		Setmeal setmeal = setmealMapper.getById(id);
		BeanUtils.copyProperties(setmeal, setmealDTO);
		//根据套餐id查找关系表中的菜品列表
		setmealDTO.setSetmealDishes(setmealDishMapper.getSetmealDishesBySetmealIds(setmeal.getId()));
		return setmealDTO;
	}
	
	@Override
	public void update(SetmealDTO setmealDTO) {
		Setmeal setmeal = new Setmeal();
		BeanUtils.copyProperties(setmealDTO, setmeal);
		setmealMapper.update(setmeal);
		//删除关系表中的套餐关系
		setmealDishMapper.deleteBySetmealId(setmealDTO.getId());
		//重新插入关系表
		if (setmealDTO.getSetmealDishes() != null && setmealDTO.getSetmealDishes().size() > 0) {
			for (SetmealDish setmealDish : setmealDTO.getSetmealDishes()) {
				setmealDish.setSetmealId(setmealDTO.getId());
			}
			setmealDishMapper.insertBatch(setmealDTO.getSetmealDishes());
		}
	}
	
	@Override
	public void startOrStop(Integer status, Long id) {
		//起售套餐时，判断套餐内是否有停售菜品，有停售菜品提示"套餐内包含未启售菜品，无法启售"
		if (status == StatusConstant.ENABLE) {
			//select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = ?
			List<Dish> dishList = dishMapper.getBySetmealId(id);
			if (dishList != null && dishList.size() > 0) {
				dishList.forEach(dish -> {
					if (StatusConstant.DISABLE == dish.getStatus()) {
						throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
					}
				});
			}
		}
		Setmeal setmeal = Setmeal.builder()
				.status(status)
				.id(id)
				.build();
		setmealMapper.update(setmeal);
	}
	
	@Override
	public void delete(List<Long> ids) {
		//要保证删除的套餐处于停售状态
		for (Long id : ids) {
			Setmeal setmeal = setmealMapper.getById(id);
			Integer status = setmeal.getStatus();
			if (status == 1) {
				throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
			}
		}
		//要先处理关系表
		setmealDishMapper.deleteBySetmealIds(ids);
		//再处理套餐表
		setmealMapper.delete(ids);
		
	}
}
