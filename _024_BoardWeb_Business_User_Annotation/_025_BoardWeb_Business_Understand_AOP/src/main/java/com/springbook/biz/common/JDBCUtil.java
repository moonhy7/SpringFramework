package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	public static Connection getConnection() {
		try {
			//ojdbc.jar파일에서 오라클드라이버 클래스 로딩
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//MySql
			Class.forName("com.mysql.cj.jdbc.Driver");
			//오라클 DB 커넥션
			//return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "study", "study");
			//MySql 커넥션
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb?serverTimezone=UTC", "study", "study");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		if(stmt != null) {
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if(stmt != null) {
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
