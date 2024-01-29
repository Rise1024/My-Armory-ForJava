package com.video.googleLimit.service;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

// 使用Lua脚本实现限流
@Service
public class RedisLimiterService {
    private DefaultRedisScript<Long> getRedisScript;

    public boolean acquire(String Key, int nums, int expire) throws Exception {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        getRedisScript = new DefaultRedisScript<>();
        getRedisScript.setResultType(Long.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
        // 执行Lua脚本
        Long result = (Long) jedis.eval(getRedisScript.getScriptAsString(),
                1, Key, String.valueOf(nums), String.valueOf(expire));
        if (result == 0) {
            return false;
        }
        return true;
    }
}