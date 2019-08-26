package com.shuyuq.syqcache.config;


import com.shuyuq.syqcache.entity.Employee;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 配置自定义的key生成方式
 */
@Configuration
public class CacheConfig {
    @Bean("mykeyGenerator")
    KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                String result="";
                String methodName=method.getName();
                if("getEmployeeById".equals(methodName)){
                    result="emp"+objects[0];
                }else if("updateEmployee".equals(methodName)){
                    Employee employee=(Employee)objects[0];
                    result="emp"+employee.getId();
                }
                return result;
            }
        };
    }
}
