
package com.summit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class HiveTestOne {

	static Logger logger = Logger.getLogger(HiveTestOne.class);

	public static void main(String[] args) {

		Connection conn = HiveDemo.getConn();
		Statement stmt = null;
		try {
			stmt = HiveDemo.getStmt(conn);
		} catch (SQLException e) {
			logger.debug("1");
		}

		// String sql = "select * from sys_fault_device limit 10";
		String sql = "select * from test limit 10";
		ResultSet res = null;
		try {
			res = stmt.executeQuery(sql);

			ResultSetMetaData meta = res.getMetaData();

			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.print(meta.getColumnName(i) + "    ");
			}
			System.out.println();
			while (res.next()) {
				System.out.print(res.getString(1) + "    ");
				System.out.print(res.getString(2) + "    ");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * try { String sql_dml = "insert into test(aaa, bbb) values(1,'汪汪')";
		 * //String sql_dml = "truncate table test"; stmt.execute(sql_dml);//
		 * 需要拥有hdfs文件读写权限的用户才可以进行此操作 logger.debug("create is susscess");
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		HiveDemo.closeStmt(stmt);
		HiveDemo.closeConn(conn);
	}
}
