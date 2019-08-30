package com.shuyuq.syqcache;

import com.shuyuq.syqcache.entity.Employee;
import com.shuyuq.syqcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyqcacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void test1(){
        redisTemplate.opsForValue().set("zhangsan","22");
        Employee employee = employeeMapper.getEmployeeById(1);
        redisTemplate.opsForValue().set("emp",employee);
    }

    @Test
    public void contextLoads() {

        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
    }

    /**
     * 测试mq发送
     */
    @Test
    public void testmq(){
        Employee employee = employeeMapper.getEmployeeById(1);
        rabbitTemplate.convertAndSend("exchange.direct","shuyuq.news",employee);
    }

    /**
     * 测试mq接收
     */
    @Test
    public void testacceptmq(){
        Employee o =(Employee) rabbitTemplate.receiveAndConvert("shuyuq.news");
        System.out.println(o);
    }

    /**
     * 测试topic模式
     */
    @Test
    public void testtopci(){
        Employee employee = employeeMapper.getEmployeeById(2);
        rabbitTemplate.convertAndSend("exchange.toptic","wsy.news",employee);
    }
    @Test
    public void testacceptTopic(){
        Employee employee =(Employee) rabbitTemplate.receiveAndConvert("wsy.news");
        System.out.println(employee);
    }

}
