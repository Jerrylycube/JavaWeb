package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
	
	//此处要遍历集合,将其一一插入
	void insertBatch(List<DishFlavor> flavors);
	
	@Select("select * from dish_flavor where dish_id = #{dishId}")
	List<DishFlavor> getByDishId(Long id);
	
	@Delete("delete from dish_flavor where dish_id = #{dishId}")
	void deleteByDishId(Long dishId);
	
	//使用动态sql
	void deleteByDishIds(List<Long> ids);
}
