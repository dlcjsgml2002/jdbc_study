package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dto.Department;
import jdbc_study.jdbc.MySQLJdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	Logger LOG = LogManager.getLogger();

	@Override
	public List<Department> selectDepartmentByAll() {
		List<Department> list = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";
		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LOG.debug(pstmt);
			while (rs.next()) { // select 기준 (sql line:22)**
				list.add(getDepartment(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Department department) throws SQLException {
		String sql = "insert into department values(?,?,?)";
		int res = 0;
		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			LOG.debug(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int delelteDepartment(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDepartment(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Department selectDepartment(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}