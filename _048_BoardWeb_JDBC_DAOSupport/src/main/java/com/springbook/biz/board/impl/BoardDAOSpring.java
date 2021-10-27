package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAOSpring extends JdbcDaoSupport {
	//SQL 관련 명령어
	//private final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) "
	//		                          + "VALUES((SELECT NVL(MAX(SEQ), 0) + 1 FROM BOARD), ?, ?, ?)";
	private final String BOARD_INSERT = "INSERT INTO studydb.BOARD(SEQ, TITLE, WRITER, CONTENT) "
            + "VALUES((SELECT IFNULL(MAX(SEQ), 0) + 1 FROM BOARD A), ?, ?, ?)";	
//	private final String BOARD_INSERT = "INSERT INTO studydb.BOARD(SEQ, TITLE, WRITER, CONTENT) "
//            + "VALUES((SELECT IFNULL(MAX(SEQ), 0) + 1 FROM BOARD A), vo.getTitle(), vo.getWriter(), vo.getContent())";	
	
//	Object[] args = {vo.getTitle(), vo.getWriter(), vo.getContent()};
//	jdbcTemplate.update(BOARD_INSERT, args);
	private final String BOARD_UPDATE = "UPDATE studydb.BOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ?";
	private final String BOARD_DELETE = "DELETE FROM studydb.BOARD WHERE SEQ = ?";
	private final String BOARD_GET = "SELECT * FROM studydb.BOARD WHERE SEQ = ?";
	private final String BOARD_LIST = "SELECT * FROM studydb.BOARD ORDER BY SEQ DESC";
	
	//메소드에도 Autowired 사용 가능
	//해당 메소드를 스프링컨테이너가 자동으로 호출
	@Autowired
	//상속받은 JdbcDaoSupport객체에서 setDataSource 메소드를 호출해서 데이터소스 객체 의존성주입
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	//CRUD 기능의 메소드 구현
	//글 등록
	//getJdbcTemplate() : jdbcTemplate객체를 리턴. JdbcDaoSupport 클래스에서 상속받아 사용하는 메소드
	public void insertBoard(BoardVO vo) {
		System.out.println("====> JDBC로 insertBoard() 기능 처리");
		getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	//글 수정
	public void upadateBoard(BoardVO vo) {
		System.out.println("====> JDBC로 upadateBoard() 기능 처리");
		getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(), vo.getWriter(), vo.getContent());		
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("====> JDBC로 deleteBoard() 기능 처리");
		getJdbcTemplate().update(BOARD_DELETE, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("====> JDBC로 getBoard() 기능 처리");
		Object[] args = {vo.getSeq()};
		return getJdbcTemplate().queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("====> JDBC로 getBoard() 기능 처리");
		return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
	}
}
