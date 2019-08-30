package com.shuyuq.syqcache.mapper;

import com.shuyuq.syqcache.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE id=#{id}")
    Employee getEmployeeById(Integer id);

    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    void deleteEmployeeByid(Integer id);

    @Insert("INSERT INTO employee (lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId}")
    void insertEmployee(Employee employee);




}
