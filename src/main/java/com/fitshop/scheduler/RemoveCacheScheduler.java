package com.fitshop.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveCacheScheduler {

    private final CacheEvicter cacheEvicter;

    @Scheduled(fixedDelay = 7200000) // cron = "0 0 */2 * * *"
    public void removeCacheForCheapestProductsEvery2Hours() {
        this.cacheEvicter.evictAllCacheValues();
    }

}
