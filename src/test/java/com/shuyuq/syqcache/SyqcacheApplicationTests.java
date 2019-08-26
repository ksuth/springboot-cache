package com.shuyuq.syqcache;

import com.shuyuq.syqcache.entity.Employee;
import com.shuyuq.syqcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyqcacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    public void contextLoads() {

        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
    }

}
