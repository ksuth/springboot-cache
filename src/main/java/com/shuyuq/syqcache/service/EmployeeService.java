package com.shuyuq.syqcache.service;

import com.shuyuq.syqcache.entity.Employee;
import com.shuyuq.syqcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp" ,keyGenerator = "mykeyGenerator")
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable()
    public Employee getEmployeeById(Integer id){
        System.out.println("执行数据查询:"+id);
        Employee employee = employeeMapper.getEmployeeById(id);
        return employee;
    }
    @CachePut(cacheNames = "emp" ,keyGenerator = "mykeyGenerator")
    public Employee updateEmployee(Employee employee){
        System.out.println("更新employee信息:"+employee);
        employeeMapper.updateEmployee(employee);
        return employee;
    }
}
