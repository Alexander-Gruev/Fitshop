package com.example.fitshop.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveCacheScheduler {

    private final CacheEvicter cacheEvicter;

    public RemoveCacheScheduler(CacheEvicter cacheEvicter) {
        this.cacheEvicter = cacheEvicter;
    }

    @Scheduled(fixedDelay = 7200000) // cron = "0 0 */2 * * *"
    public void removeCacheForCheapestProductsEvery2Hours() {
        this.cacheEvicter.evictAllCacheValues();
    }

}
