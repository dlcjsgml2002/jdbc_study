package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.jdbc.MySQLJdbcUtil;
import jdbc_study.jdbc.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	Logger LOG = LogManager.getLogger();

	@Override
	public List<Employee> selectEmployeeByAll() {
		List<Employee> list = new ArrayList<>();
		String sql = "select empno, empname, title, manager, salary, dno from employee";
		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getEmployee(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("empno"), rs.getString("empname"), rs.getString("title"),
				new Employee(rs.getInt("manager")), rs.getInt("salary"), new Department(rs.getInt("dno")));
	}

	@Override
	public int insertEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("insertEmployee(");
		String sql = "insert into employee value(?,?,?,?,?,?)";
		int rowAffected = 0;
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int deleteEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("deleteDepartment()");
		String sql = "delete from employee where empno=?";
		int rowAffected = 0;
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public int updateEmployee(Employee emp) throws SQLException {
		LogUtil.prnLog("updateDepartment()");
		String sql = "update employee set empname=?, title=?, manager=?, salary=?, dno=? where empno=?";
		int rowAffected = 0;
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(6, emp.getEmpNo());
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getTitle());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDept().getDeptNo());
			LogUtil.prnLog(pstmt);
			rowAffected = pstmt.executeUpdate();
		}
		return rowAffected;
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) throws SQLException {
		LogUtil.prnLog("selectEmployeeByNo()");
		Employee empl = null;
		String sql = "select empno, empname, title, manager, salary, dno where empno = ?";
		try (Connection conn = MySQLJdbcUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					empl = getEmployee(rs);
				}
			}
		}
		return empl;
	}

}