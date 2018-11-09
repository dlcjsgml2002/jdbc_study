package jdbc_study.jdbc;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class MySQLJdbcUtilTest {
	static final Logger LOG = LogManager.getLogger();

	@Test
	public void testConnection() {
		try {
			Connection conn = MySQLJdbcUtil.getConnection();
			LOG.debug(String.format("Connected to DataBase %s successfully.", conn.getCatalog()));
			Assert.assertNotNull(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
