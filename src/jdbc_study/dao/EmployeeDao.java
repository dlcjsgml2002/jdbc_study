package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();

	int insertEmployee(Employee emp) throws SQLException;

	int deleteEmployee(Employee emp) throws SQLException;

	int updateEmployee(Employee emp) throws SQLException;

	Employee selectEmployeeByNo(Employee emp) throws SQLException;

}
