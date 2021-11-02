package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis extends SqlSessionDaoSupport {
	@Autowired
	//Spring 컨테이너이가 자동으로 메소드를 호출해주고
	//applicationContext파일에 등록된 SqlSessionFactoryBean 객체를  인자로 받아서 메소드를 실행
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void insertBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 insertBoard() 기능 처리");
		getSqlSession().insert("BoardDAO.insertBoard", vo);
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 updateBoard() 기능 처리");
		getSqlSession().update("BoardDAO.updateBoard", vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 deleteBoard() 기능 처리");
		getSqlSession().delete("BoardDAO.deleteBoard", vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 getBoard() 기능 처리");
		return (BoardVO)getSqlSession().selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("====> Mybatis로 getBoardList() 기능 처리");
		return getSqlSession().selectList("BoardDAO.getBoardList", vo);
	}
}
