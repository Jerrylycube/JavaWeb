package springbootmybatisemp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootmybatisemp.mapper.EmpMapper;
import springbootmybatisemp.pojo.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SpringbootMybatisEmpApplicationTests {
	@Autowired
	private EmpMapper empMapper;
	
	@Test
	public void testXml() {
//        List<Emp> emps = empMapper.listXml("张", (short) 1, LocalDate.of(2010, 1, 1), LocalDate.of(2020, 1, 1));
		List<Emp> emps = empMapper.listXml(null, (short) 1, null, null);
		emps.forEach(System.out::println);
	}
	
	public void testfind() {
		Emp emp = empMapper.getById(1);
		System.out.println(emp);
	}
	
	public void testdelete() {
		empMapper.deleteEmp(17);
	}
	
	public void testinsert() {
		Emp emp1 = new Emp(null, "Tom", null, "汤姆", (short) 1, "1.jpg", (short) 2, LocalDate.of(200, 1, 1), 2, LocalDateTime.now(), LocalDateTime.now());
		empMapper.insertEmp(emp1);
		System.out.println(emp1.getId());
		
	}
	
	public void testupdate() {
		//要修改的员工信息
		Emp emp = new Emp();
		emp.setId(21);
		emp.setUsername("songdaxia");
		emp.setPassword(null);
		emp.setName("老宋");
		emp.setImage("2.jpg");
		emp.setGender((short) 1);
		emp.setJob((short) 2);
		emp.setEntrydate(LocalDate.of(2012, 1, 1));
		emp.setCreateTime(null);
		emp.setUpdateTime(LocalDateTime.now());
		emp.setDeptId(2);
		//调用方法，修改员工数据
		empMapper.update(emp);
	}
	
	@Test
	public void testiupdet2() {
		//要修改的员工信息
		Emp emp = new Emp();
		emp.setId(21);
		emp.setUsername("songdaxia");
		emp.setPassword(null);
		emp.setName("老宋11");
		emp.setImage("2.jpg");
		emp.setGender((short) 1);
		emp.setJob((short) 1);
		emp.setUpdateTime(LocalDateTime.now());
		emp.setDeptId(2);
		//调用方法，修改员工数据
		empMapper.update2(emp);
	}
	
}
