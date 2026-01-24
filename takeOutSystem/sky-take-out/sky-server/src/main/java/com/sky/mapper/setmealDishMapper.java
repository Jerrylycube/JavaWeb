package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface setmealDishMapper {
	
	//需要遍历ids
	List<Long> getSetmealIdsByDishIds(List<Long> ids);
	
	//批量插入,需要遍历setmealDishes
	void insertBatch(List<SetmealDish> setmealDishes);
	
	//根据套餐id查询对应的菜品份数之类的
	@Select("select * from setmeal_dish where setmeal_id = #{id}")
	List<SetmealDish> getSetmealDishesBySetmealIds(Long id);
	
	//根据套餐id删除套餐和菜品的关联数据
	@Select("delete from setmeal_dish where setmeal_id = #{id}")
	void deleteBySetmealId(Long id);
	
	//批量删除
	void deleteBySetmealIds(List<Long> ids);
}
