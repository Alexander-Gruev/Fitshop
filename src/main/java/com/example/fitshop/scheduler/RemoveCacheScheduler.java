package com.example.fitshop.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveCacheScheduler {

    @Scheduled(fixedDelay = 7200000) // cron = "0 0 */2 * * *"
    public void removeCacheForCheapestProductsEvery2Hours() {
        evictAllCacheValues();
    }

    @CacheEvict(value = "cheapestProducts", allEntries = true)
    public void evictAllCacheValues() {}

}
