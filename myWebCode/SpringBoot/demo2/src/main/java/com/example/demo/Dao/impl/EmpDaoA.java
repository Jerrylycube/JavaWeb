package com.example.demo.Dao.impl;

import com.example.demo.Controller.Respond.Emp;
import com.example.demo.Controller.util.XmlParserUtils;
import com.example.demo.Dao.EmpDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {
        //1. 加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
