package com.hmdp.redis;

public interface ILock {
	boolean tryLock(Long time);
	
	void unLock();
}
