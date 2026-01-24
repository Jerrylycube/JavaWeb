package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
	
	
	/**
	 * 根据分类id查询菜品
	 *
	 * @param categoryId
	 * @return
	 */
	@Select("select count(id) from dish where category_id = #{categoryId}")
	Integer countByCategoryId(Long categoryId);
	
	/**
	 * 菜品分页查询
	 *
	 * @param dishPageQueryDTO
	 * @return
	 */
	Page<DishVO> page(DishPageQueryDTO dishPageQueryDTO);
	
	/**
	 * 新增菜品
	 *
	 * @param dish
	 */
	@AutoFill(value = OperationType.INSERT)
	//由于我们要使用到id,所以要回传数据库生成的主键id给对象dish,就要用mybatis
	void add(Dish dish);
	
	/**
	 * 根据id查询菜品和对应的口味数据
	 *
	 * @param id
	 * @return
	 */
	@Select("select * from dish where id = #{id}")
	DishVO getByIdWithFlavors(Long id);
	
	/**
	 * 根据id修改菜品数据
	 *
	 * @param dish
	 */
	//使用动态sql
	@AutoFill(value = OperationType.UPDATE)
	void update(Dish dish);
	
	//使用动态sql去遍历集合
	void delete(List<Long> ids);
	
	@Select("select * from dish where id = #{id}")
	Dish getById(Long id);
	
	@Select("select * from dish where category_id = #{categoryId}")
	List<Dish> getDishByCategoryId(Long categoryId);
	
	/**
	 * 根据套餐id查询菜品
	 *
	 * @param setmealId
	 * @return
	 */
/*	<select id="getDishesBySetmealId" resultType="com.sky.entity.Dish">
	SELECT d.*
	FROM dish d
	LEFT JOIN setmeal_dish sd ON d.id = sd.dish_id
	WHERE sd.setmeal_id = #{setmealId}
</select>
	SQL语句详解：
	SELECT d.*：选择dish表中的所有字段
	FROM dish d：从dish表开始查询，别名为d
	LEFT JOIN setmeal_dish sd ON d.id = sd.dish_id：左连接setmeal_dish关联表，确保即使没有关联记录也能返回dish表的数据
	WHERE sd.setmeal_id = #{setmealId}：筛选条件，只返回与指定套餐ID相关的菜品
3. 执行流程
	根据传入的 setmealId 参数
	在 setmeal_dish 关联表中查找匹配的记录
	通过 dish_id 字段关联到 dish 表
			返回所有匹配的菜品信息
4. 特点分析
	左连接：使用 LEFT JOIN 确保即使某些菜品没有关联记录也能正常返回
	结果映射：resultType="com.sky.entity.Dish" 指定返回结果为Dish实体对象
	参数绑定：#{setmealId} 安全地绑定参数，防止SQL注入
5. 使用场景
	该方法常用于：
	套餐详情页展示关联菜品
			查询特定套餐的所有菜品信息
	餐厅管理系统的菜品关联功能*/
	@Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
	List<Dish> getBySetmealId(Long setmealId);
}
