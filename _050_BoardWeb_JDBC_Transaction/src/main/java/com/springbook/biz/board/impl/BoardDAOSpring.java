package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository("boardDAO")
public class BoardDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//SQL 관련 명령어
	private final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) "
            + "VALUES(?, ?, ?, ?)";
//	private final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) "
//			                          + "VALUES((SELECT IFNULL(MAX(SEQ), 0) + 1 FROM BOARD), ?, ?, ?)";
//	private final String BOARD_INSERT = "INSERT INTO studydb.BOARD(SEQ, TITLE, WRITER, CONTENT) "
//            + "VALUES((SELECT IFNULL(MAX(SEQ), 0) + 1 FROM BOARD A), ?, ?, ?)";
//	private final String BOARD_INSERT = "INSERT INTO studydb.BOARD(SEQ, TITLE, WRITER, CONTENT) "
//            + "VALUES((SELECT IFNULL(MAX(SEQ), 0) + 1 FROM BOARD A), vo.getTitle(), vo.getWriter(), vo.getContent())";
//	Object[] args = {vo.getTitle(), vo.getWriter(), vo.getContent()};
//	jdbcTemplate.update(BOARD_INSERT, args);
	private final String BOARD_UPDATE = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ?";
	private final String BOARD_DELETE = "DELETE FROM BOARD WHERE SEQ = ?";
	private final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ = ?";
	private final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY SEQ DESC";
	
	//CRUD 기능의 메소드 구현
	//글 등록
	//getJdbcTemplate() : JdbcTemplate객체를 리턴. JdbcDaoSupport 클래스에서 상속받아 사용하는 메소드
	public void insertBoard(BoardVO vo) {
		System.out.println("====> JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	//글 수정
	public void upadateBoard(BoardVO vo) {
		System.out.println("====> JDBC로 upadateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("====> JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("====> JDBC로 getBoard() 기능 처리");
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("====> JDBC로 getBoard() 기능 처리");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
}
