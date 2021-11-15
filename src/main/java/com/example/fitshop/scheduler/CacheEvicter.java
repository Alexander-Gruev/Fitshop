package com.example.fitshop.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheEvicter {

    @CacheEvict(value = "cheapestProducts", allEntries = true)
    public void evictAllCacheValues() {}

}
