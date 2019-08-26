package com.shuyuq.syqcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
 */
@MapperScan("com.shuyuq.syqcache.mapper")
@EnableCaching
@SpringBootApplication
public class SyqcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyqcacheApplication.class, args);
    }

}
