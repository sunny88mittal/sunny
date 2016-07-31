package com.junglee.caching;

//Interface to distributed redis cache service
public interface CacheService {

	public void getFromCache(String key);
	
	public void deleteFromCache(String key);
	
	public void updateInCache(String key, String value);
}
