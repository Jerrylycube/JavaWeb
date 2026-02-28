package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
	void addShoppingCart(ShoppingCartDTO shoppingCartDTO); // 添加购物车 [cite: 252]
	
	List<ShoppingCart> showShoppingCart();                // 查看购物车 [cite: 316]
	
	void cleanShoppingCart();                             // 清空购物车 [cite: 351]
	
	/**
	 * 减购物车
	 */
	void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
