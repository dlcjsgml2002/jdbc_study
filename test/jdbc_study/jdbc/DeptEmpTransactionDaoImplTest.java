package jdbc_study.jdbc;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dao.DeptEmpTransactionDao;
import jdbc_study.dao.DeptEmpTransactionDaoImpl;
import jdbc_study.dao.EmployeeDao;
import jdbc_study.dao.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeptEmpTransactionDaoImplTest {
	static DeptEmpTransactionDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start DeptEmpTransactionDaoTest");
		dao = new DeptEmpTransactionDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End DeptEmpTransactionDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test1transactionInsertEmployeeAndDepartment() throws SQLException {
		LogUtil.prnLog("Department fail");
		Department dept = new Department(1, "영업", 1);
		Employee emp = new Employee(1004, "서현진", "사원", new Employee(1003), 1500000, dept);

		int result = dao.transactionInsertEmployeeAndDepartment(emp, dept);
		LogUtil.prnLog(String.format("result %d", result));

		Assert.assertNotEquals(2, result);
	}

	@Test
	public void test2transactionInsertEmployeeAndDepartment() throws SQLException {
		LogUtil.prnLog("Employee fail");
		Department dept = new Department(6, "마케팅", 10);
		Employee emp = new Employee(1003, "조민희", "과장", new Employee(4377), 3000000, dept);

		int result = dao.transactionInsertEmployeeAndDepartment(emp, dept);
		LogUtil.prnLog(String.format("result %d", result));

		Assert.assertNotEquals(2, result);
	}

	@Test
	public void test3transactionInsertEmployeeAndDepartment() throws SQLException {
		LogUtil.prnLog("Department , Employee Success");
		Department dept = new Department(7, "총무", 17);
		Employee emp = new Employee(1005, "이유영", "사원", new Employee(1003), 1500000, dept);

		int result = dao.transactionInsertEmployeeAndDepartment(emp, dept);
		LogUtil.prnLog(String.format("result %d", result));

		Assert.assertEquals(2, result);

		EmployeeDao empDao = new EmployeeDaoImpl();
		empDao.deleteEmployee(emp);

		DepartmentDao deptDao = new DepartmentDaoImpl();
		deptDao.deleteDepartment(dept);
	}

	@Test
	public void test4transactionInsertEmployeeAndDepartmentApi() throws SQLException {
		LogUtil.prnLog("Department fail");
		Department dept = new Department(1, "영업", 1);
		Employee emp = new Employee(1004, "서현진", "사원", new Employee(1003), 1500000, dept);

		int result = dao.transactionInsertEmployeeAndDepartmentApi(emp, dept);
		LogUtil.prnLog(String.format("result %d", result));

		Assert.assertNotEquals(2, result);
	}

	@Test
	public void test5transactionInsertEmployeeAndDepartmentApi() throws SQLException {
		LogUtil.prnLog("Employee fail");
		Department dept = new Department(6, "마케팅", 10);
		Employee emp = new Employee(1003, "조민희", "과장", new Employee(4377), 3000000, dept);

		int result = dao.transactionInsertEmployeeAndDepartmentApi(emp, dept);
		LogUtil.prnLog(String.format("result %d", result));

		Assert.assertNotEquals(2, result);
	}

	@Test
	public void test6transactionInsertEmployeeAndDepartmentApi() throws SQLException {
		LogUtil.prnLog("Department , Employee Success");
		Department dept = new Department(7, "총무", 17);
		Employee emp = new Employee(1005, "이유영", "사원", new Employee(1003), 1500000, dept);

		int result = dao.transactionInsertEmployeeAndDepartmentApi(emp, dept);
		LogUtil.prnLog(String.format("result %d", result));

		Assert.assertEquals(2, result);

		EmployeeDao empDao = new EmployeeDaoImpl();
		empDao.deleteEmployee(emp);

		DepartmentDao deptDao = new DepartmentDaoImpl();
		deptDao.deleteDepartment(dept);
	}
}
