package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j//日志打印
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private JwtProperties jwtProperties;
	
	/**
	 * 员工登录
	 *
	 * @param employeeLoginDTO
	 * @return
	 */
	public Employee login(EmployeeLoginDTO employeeLoginDTO) {
		String username = employeeLoginDTO.getUsername();
		String password = employeeLoginDTO.getPassword();
		
		//1、根据用户名查询数据库中的数据
		Employee employee = employeeMapper.getByUsername(username);
		
		//2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
		if (employee == null) {
			//账号不存在
			throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
		}
		
		//密码比对
		// TODO 后期需要进行md5加密，然后再进行比对
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		if (!password.equals(employee.getPassword())) {
			//密码错误
			throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
		}
		
		if (employee.getStatus() == StatusConstant.DISABLE) {
			//账号被锁定
			throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
		}
		
		//3、返回实体对象
		return employee;
	}
	
	@Override
	public void add(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		//对象属性拷贝
		BeanUtils.copyProperties(employeeDTO, employee);
		//设置账号的状态，默认正常状态 1表示正常 0表示锁定
		employee.setStatus(StatusConstant.ENABLE);
		
		//设置默认密码 123456,使用MD5进行加密
		//使用常量封装,便于整体维护
		employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
		
		employeeMapper.add(employee);
	}
	
	@Override
	public PageResult page(EmployeePageQueryDTO employeePageQuery) {
		PageHelper.startPage(employeePageQuery.getPage(), employeePageQuery.getPageSize());
		Page<Employee> page = employeeMapper.page(employeePageQuery);
		return new PageResult((long) page.getTotal(), page.getResult());
	}
	
	@Override
	public void startOrStop(Integer status, Long id) {
		//使用builder进行构建对象,前提是该类有Builder注解
		//且注意,这个构建是构建一个新的,而不能在原有基础去修改
		Employee employee = Employee.builder()
				.status(status)
				.id(id)
				.build();
		employeeMapper.update(employee);
	}
	
	@Override
	public Employee getById(Long id) {
		Employee employee = employeeMapper.getById(id);
		return employee;
	}
	
	@Override
	public void update(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		//使用工具类将employeeDTO中的属性拷贝到employee中
		BeanUtils.copyProperties(employeeDTO, employee);
		//重复调用update的xml文件去操作
		employeeMapper.update(employee);
	}
}
