package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dao.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start EmployeeDaoTest");
		dao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End EmployeeDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectEmployeeByAll() throws SQLException {
		List<Employee> lists = dao.selectEmployeeByAll();
		for (Employee emp : lists) {
			LogUtil.prnLog(emp.toString());
		}
		Assert.assertNotNull(lists);
	}

	@Test/*(expected = SQLException.class)*/
	public void test02InsertEmployee() throws SQLException {
		Employee newEmp = new Employee(1005, "서현진", "사원", new Employee(1003), 1500000, new Department(1));
		int result = dao.insertEmployee(newEmp);
		LogUtil.prnLog(String.format("result %d", result));
		Assert.assertEquals(1, result);
	}

	@Test
	public void test04DeleteEmployee() throws SQLException {
		Employee delEmp = new Employee(1005);
		int result = dao.deleteEmployee(delEmp);
		LogUtil.prnLog(String.format("result %d", result));
		Assert.assertEquals(1, result);
	}

	@Test
	public void test03UpdateEmployee() throws SQLException {
		Employee updateEmp = new Employee(1005, "아이유", "대리", new Employee(3426), 3500000, new Department(1));
		int result = dao.updateEmployee(updateEmp);
		LogUtil.prnLog(String.format("result %d", result));
		Assert.assertEquals(1, result);
	}

	@Test
	public void test05SelectEmployeeByNo() throws SQLException {
		Employee selEmp = dao.selectEmployeeByNo(new Employee(1003));
		LogUtil.prnLog(selEmp.toString());
		Assert.assertNotNull(selEmp);
	}
}
