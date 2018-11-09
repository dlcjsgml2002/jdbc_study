package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dto.Department;

public class DepartmentDaoTest {
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("setUpBeforeClass()");
		dao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("tearDownAfterClass()");
		dao = null;
	}

	@Test
	public void testSelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		for (Department dept : list) {
			MySQLJdbcUtilTest.LOG.debug(dept);
		}
		Assert.assertNotEquals(0, list.size()); // (기대값, 사이즈): 0이 아니라면
	}
	
	@Test
	public void testInsertDepartment() {
		Department newDept = new Department(4, "자바개발부서", 15);
		try {
			int res = dao.insertDepartment(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
			if(e.getErrorCode()==1062) {
				JOptionPane.showMessageDialog(null, "해당 부서는 이미 존재합니다.");
			}
		}
	}

}
