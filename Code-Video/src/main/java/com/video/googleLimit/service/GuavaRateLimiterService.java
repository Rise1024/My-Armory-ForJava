package com.video.googleLimit.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @Auther: zds
 * @Date: 2023/01/04/17:42
 * @Description:
 *
 * RateLimiter create(double permitsPerSecond)
 * 根据指定的稳定吞吐率创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询）
 *
 * double	getRate()
 * 返回RateLimiter 配置中的稳定速率，该速率单位是每秒多少许可数
 *
 * boolean	tryAcquire()
 * 从RateLimiter 获取许可，如果该许可可以在无延迟下的情况下立即获取得到的话
 *
 */
@Service
public class GuavaRateLimiterService {
    RateLimiter rateLimiter = RateLimiter.create(3);

    public boolean tryGetToken() {
        double rate = rateLimiter.getRate();
        System.out.println(rate);
        boolean b = rateLimiter.tryAcquire();
        return b;
    }
    
}