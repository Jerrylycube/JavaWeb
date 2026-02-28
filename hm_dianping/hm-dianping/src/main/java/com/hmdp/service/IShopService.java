package com.hmdp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {
	
	/**
	 * 查询商铺信息
	 *
	 * @param id
	 * @return
	 */
	Result queryById(Long id);
	
	Result update(Shop shop);
	
	
}
