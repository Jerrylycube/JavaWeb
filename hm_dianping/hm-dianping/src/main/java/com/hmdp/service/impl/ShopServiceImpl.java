package com.hmdp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.hmdp.utils.CacheClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;
import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TTL;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
	
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource
	private CacheClient cacheClient;
	
	@Override
	public Result queryById(Long id) {
		// 解决缓存穿透
		Shop shop = cacheClient
				.queryWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);
		
		// 互斥锁解决缓存击穿
		// Shop shop = cacheClient
		//         .queryWithMutex(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);
		
		// 逻辑过期解决缓存击穿
		// Shop shop = cacheClient
		//         .queryWithLogicalExpire(CACHE_SHOP_KEY, id, Shop.class, this::getById, 20L, TimeUnit.SECONDS);
		
		if (shop == null) {
			return Result.fail("店铺不存在！");
		}
		// 7.返回
		return Result.ok(shop);
	}
	
	@Override
	public Result update(Shop shop) {
		Long id = shop.getId();
		if (id == null) {
			return Result.fail("店铺id不能为空");
		}
		updateById(shop);
		//删除缓存
		stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
		return Result.ok();
	}
	/*//缓存穿透
	public Shop queryWithPassThrough(Long id) {
		//1.从redis查询商铺缓存
		String key = CACHE_SHOP_KEY + id;
		String shopJson = stringRedisTemplate.opsForValue().get(key);
		
		if (StrUtil.isNotBlank(shopJson)) {
			//存在，返回
			Shop shop = JSONUtil.toBean(shopJson, Shop.class);
			return shop;
		}
		
		//判断命中是否为空值
		if (shopJson != null) {
			return null;
		}
		//不存在，根据id查询你数据库
		Shop shop = getById(id);
		
		if (shop == null) {
			//将空值写入redis
			stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
			//不存在，返回错误信息
			return null;
		}
		
		//然后再写入redis
		stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
		
		//返回
		return shop;
	}
	
	//互斥锁
	
	public Shop queryWithMutex(Long id) {
		//1.从redis查询商铺缓存
		String key = CACHE_SHOP_KEY + id;
		String shopJson = stringRedisTemplate.opsForValue().get(key);
		
		if (StrUtil.isNotBlank(shopJson)) {
			//存在，返回
			Shop shop = JSONUtil.toBean(shopJson, Shop.class);
			return shop;
		}
		
		//判断命中是否为空值
		if (shopJson != null) {
			return null;
		}
		
		//实现缓存重建
		//获取互斥锁
		String lockKey = LOCK_SHOP_KEY + id;
		Shop shop = null;
		try {
			boolean islock = trylock(lockKey);
			
			//失败，则休眠并重试
			if (!islock) {
				Thread.sleep(50);
				return queryWithMutex(id);
			}
			
			//不存在，根据id查询你数据库
			shop = getById(id);
			
			if (shop == null) {
				//将空值写入redis
				stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
				//不存在，返回错误信息
				return null;
			}
			
			//然后再写入redis
			stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			//释放互斥锁
			unlock(lockKey);
		}
		
		//返回
		return shop;
	}
	
	//使用逻辑过期解决缓存击穿
	private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);
	
	public Shop queryWithLogicalExpire(Long id) {
		String key = CACHE_SHOP_KEY + id;
		// 1.从redis查询商铺缓存
		String json = stringRedisTemplate.opsForValue().get(key);
		// 2.判断是否存在
		if (StrUtil.isBlank(json)) {
			// 3.存在，直接返回
			return null;
		}
		// 4.命中，需要先把json反序列化为对象
		RedisData redisData = JSONUtil.toBean(json, RedisData.class);
		Shop shop = JSONUtil.toBean((JSONObject) redisData.getData(), Shop.class);
		LocalDateTime expireTime = redisData.getExpireTime();
		// 5.判断是否过期
		if (expireTime.isAfter(LocalDateTime.now())) {
			// 5.1.未过期，直接返回店铺信息
			return shop;
		}
		// 5.2.已过期，需要缓存重建
		// 6.缓存重建
		// 6.1.获取互斥锁
		String lockKey = LOCK_SHOP_KEY + id;
		boolean isLock = trylock(lockKey);
		// 6.2.判断是否获取锁成功
		if (isLock) {
			CACHE_REBUILD_EXECUTOR.submit(() -> {
				
				try {
					//重建缓存
					this.saveShop2Redis(id, 20L);
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					unlock(lockKey);
				}
			});
		}
		// 6.4.返回过期的商铺信息
		return shop;
	}
	
	private boolean trylock(String key) {
		Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, TRY_LOCK, 10, TimeUnit.SECONDS);
		return BooleanUtil.isTrue(flag);
	}
	
	private void unlock(String key) {
		stringRedisTemplate.delete(key);
	}
	
	public void saveShop2Redis(Long id, Long expiredSeconds) {
		//1.查询店铺数据
		Shop shop = getById(id);
		
		//2.封装逻辑过期时间
		RedisData redisData = new RedisData();
		redisData.setData(shop);
		redisData.setExpireTime(LocalDateTime.now().plusSeconds(expiredSeconds));
		
		//3.写入redis
		stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(redisData));
	}
	
	*/
	
}
