package com.example.demo.Dao;

import com.example.demo.Controller.Respond.Emp;

import java.util.List;

public interface EmpDao {
    //获取员工列表数据
    public List<Emp> listEmp();
}
