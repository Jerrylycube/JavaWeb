package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//	@Select("select * from emp limit #{start},#{pageSize}")
//	List<Emp> findInPageRows(Integer start, Integer pageSize);
//
//	@Select("select count(*) from emp ")
//	Long fingInPageTotal();

//	@Select("select * from emp")
//	public List<Emp> list();
	
	//此处使用Xml
	public List<Emp> list(Short gender, String name, LocalDate begin, LocalDate end);
	
	//@Delete("delete from emp where id = #{id}")
	void delete(List<Integer> ids);
	
	@Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
			"values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime} )")
	void add(Emp emp);
	
	@Update("update emp set username=#{username},name=#{name},gender = #{gender}, image= #{image}, job= #{job}, " +
			"entrydate= #{entrydate}, dept_id= #{deptId}, create_time= #{createTime}, update_time= #{updateTime} where id=#{id};")
	void update(Emp emp);
	
	@Select("select * from emp where id = #{id}")
	Emp findById(Integer id);
}
