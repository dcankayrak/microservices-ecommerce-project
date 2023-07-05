package com.dcankayrak.productservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean("defaultCacheManager")
    public CacheManager defaultCacheManager(){
        return new ConcurrentMapCacheManager();
    }

}
