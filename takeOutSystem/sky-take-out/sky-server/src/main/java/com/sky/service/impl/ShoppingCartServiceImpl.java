package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	@Autowired
	private DishMapper dishMapper;
	@Autowired
	private SetmealMapper setmealMapper;
	
	/**
	 * 添加购物车逻辑 [cite: 258]
	 */
	@Override
	public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
		ShoppingCart shoppingCart = new ShoppingCart();
		BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
		shoppingCart.setUserId(BaseContext.getCurrentId());      // 设置当前用户id [cite: 259]
		
		// 1. 判断当前商品是否在购物车中 需要全部一致,点单人,菜品,菜品口味
		List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
		
		//此处为了保证获得的只有一相同类型的菜品
		if (shoppingCartList != null && shoppingCartList.size() == 1) {
			// 如果已经存在，数量加1
			shoppingCart = shoppingCartList.get(0);
			shoppingCart.setNumber(shoppingCart.getNumber() + 1);
			shoppingCartMapper.updateNumberById(shoppingCart);
		} else {
			// 如果不存在，插入数据，数量初始化为1 [cite: 264]
			Long dishId = shoppingCartDTO.getDishId();
			if (dishId != null) {
				// 添加的是菜品 [cite: 265]
				Dish dish = dishMapper.getById(dishId);
				shoppingCart.setName(dish.getName());
				shoppingCart.setImage(dish.getImage());
				shoppingCart.setAmount(dish.getPrice());
			} else {
				// 添加的是套餐 [cite: 266]
				Setmeal setmeal = setmealMapper.getById(shoppingCartDTO.getSetmealId());
				shoppingCart.setName(setmeal.getName());
				shoppingCart.setImage(setmeal.getImage());
				shoppingCart.setAmount(setmeal.getPrice());
			}
			shoppingCart.setNumber(1);
			shoppingCart.setCreateTime(LocalDateTime.now());
			shoppingCartMapper.insert(shoppingCart);
		}
	}
	
	/**
	 * 查看购物车逻辑 [cite: 319]
	 */
	public List<ShoppingCart> showShoppingCart() {
		return shoppingCartMapper.list(ShoppingCart.builder()
				.userId(BaseContext.getCurrentId()).build());
	}
	
	/**
	 * 清空购物车逻辑 [cite: 354]
	 */
	public void cleanShoppingCart() {
		shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());
	}
	
	@Override
	public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
		ShoppingCart shoppingCart = new ShoppingCart();
		BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
		shoppingCart.setUserId(BaseContext.getCurrentId());      // 设置当前用户id [cite: 259]
		//判断购物车中数量,大于1,数量减1去修改即可
		List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);
		if (shoppingCartList != null && shoppingCartList.size() == 1) {
			shoppingCart = shoppingCartList.get(0);
			if (shoppingCart.getNumber() > 1) {
				shoppingCart.setNumber(shoppingCart.getNumber() - 1);
				shoppingCartMapper.updateNumberById(shoppingCart);
			} else {
				shoppingCartMapper.delete(shoppingCart);
			}
		}
		//等于1,那么就是删除
		
	}
}
