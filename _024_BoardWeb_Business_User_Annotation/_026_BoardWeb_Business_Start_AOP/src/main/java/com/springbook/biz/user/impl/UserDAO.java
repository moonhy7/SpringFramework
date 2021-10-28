package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {
	//JDBC 관련 변수들
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//SQL 명령어
	private final String USER_GET = "SELECT * FROM USERS WHERE ID = ? AND PASSWORD = ?";
	
	//CRUD 기능의 메소드 구현
	//회원 상세 조회
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
}
