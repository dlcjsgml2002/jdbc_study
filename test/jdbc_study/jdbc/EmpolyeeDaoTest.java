package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dao.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpolyeeDaoTest {
	static EmployeeDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("setUpBeforeClass()");
		dao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("tearDownAfterClass()");
		dao = null;
	}

	@Test
	public void test01SelectEmployeeByAll() {
		List<Employee> list = dao.selectEmployeeByAll();
		for (Employee empl : list) {
			MySQLJdbcUtilTest.LOG.debug(empl);
		}
		Assert.assertNotEquals(0, list.size()); // (기대값, 사이즈): 0이 아니라면
	}
	
	@Test
	public void test02InsertEmployee() throws SQLException {
		Employee newEmp = new Employee(1005, "호식이", "회장", new Employee(1003), 15000000, new Department(1));
		int result = dao.insertEmployee(newEmp);
		LogUtil.prnLog(String.format("result %d", result));
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void test04DeleteEmployee() throws SQLException {
		Employee delEmp = new Employee(1003);
		int result = dao.deleteEmployee(delEmp);
		LogUtil.prnLog(String.format("result $d, result"));
		Assert.assertEquals(1, result);
		/*try {
			int res = dao.delelteDepartment(newDept);
			System.out.println(res);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}*/
	}
	
	@Test
	public void test03UpdateEmployee() throws SQLException {
		Employee updateEmp = new Employee(1003, "아이유", "대리", new Employee(3426), 3500000, new Department(1));
		int result = dao.updateEmployee(updateEmp);
		LogUtil.prnLog(String.format("result %d", result));
		Assert.assertEquals(1, result);
		/*Department newDept = new Department(4, "자브아아아앙", 10);
		try {
			int res = dao.updateDepartment(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}*/
	}
	
	@Test
	public void test05SelectEmployeeByNo() throws SQLException {
		Employee selEmp = dao.selectEmployeeByNo(new Employee(1003));
		LogUtil.prnLog(selEmp.toString());
		Assert.assertNotNull(selEmp);
		/*Department newDept = new Department(1);
		try {
			Department res = dao.selectDepartmentByNo(newDept);
			JOptionPane.showMessageDialog(null, res);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "검색 실패");
		}*/
	}

}