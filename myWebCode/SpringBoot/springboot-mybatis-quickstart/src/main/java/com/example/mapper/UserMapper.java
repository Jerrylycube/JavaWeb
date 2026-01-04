package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//运行时,会自动生成改接口的实现类对象,并提供IOC容器自动调用
public interface UserMapper {
    //此处调用Select注解自动将SQL语句执行好的结果返回给user对象,保存到List集合中
    @Select("select * from user")
    public List<User> list();
}
