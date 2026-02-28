package com.hmdp.redis;

import cn.hutool.core.lang.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SimpleRedisLock implements ILock {
	
	private String name;
	private StringRedisTemplate stringRedisTemplate;
	private static final String KEY_PREFIX = "lock:";
	private static final String ID_PREFIX = UUID.randomUUID().toString(true) + "-";
	
	public SimpleRedisLock(String name, StringRedisTemplate stringRedisTemplate) {
		this.name = name;
		this.stringRedisTemplate = stringRedisTemplate;
	}
	
	@Override
	public boolean tryLock(Long time) {
		//1.设置key
		String key = KEY_PREFIX + name;
		//2.存入Redis,返回
		//获取当前线程id
		String threadId = ID_PREFIX + Thread.currentThread().getId();
		Boolean isLock = stringRedisTemplate.opsForValue().setIfAbsent(key, threadId, time, TimeUnit.SECONDS);
		return Boolean.TRUE.equals(isLock);
	}
	
	/*	@Override
		public void unLock() {
			//1.设置key
			String key = KEY_PREFIX + name;
			//2.获取标识
			//为什么使用UUID+线程id:UUID用于防止不在同一服务器的相同线程id,线程id用于防止同一服务器的不同线程
			String threadId = ID_PREFIX + Thread.currentThread().getId();
			//3.获取Redis中的标识
			String id = stringRedisTemplate.opsForValue().get(key);
			if (threadId.equals(id)) {
				//标识相同,释放锁
				//此处不是原子性删除,可能会有阻塞导致锁过期,进而引发锁的误删
				//4.删除
				stringRedisTemplate.delete(key);
			}
		}*/
	private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
	
	static {
		UNLOCK_SCRIPT = new DefaultRedisScript<>();
		UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
		UNLOCK_SCRIPT.setResultType(Long.class);
	}
	
	@Override
	public void unLock() {
		// 调用lua脚本
		stringRedisTemplate.execute(
				UNLOCK_SCRIPT,
				Collections.singletonList(KEY_PREFIX + name),
				ID_PREFIX + Thread.currentThread().getId());
	}
}