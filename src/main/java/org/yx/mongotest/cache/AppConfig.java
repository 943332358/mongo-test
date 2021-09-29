package org.yx.mongotest.cache;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

/**
 * @author yangxin
 * @date 2021-09-28 09:53
 * @since v1.6.5
 */
@Configuration
public class AppConfig {


    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("itemCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1)))
                .withCacheConfiguration("customerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }
}
