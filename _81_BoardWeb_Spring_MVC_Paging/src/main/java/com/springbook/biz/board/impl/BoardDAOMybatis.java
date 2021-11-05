package com.springbook.biz.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.Criteria;

@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 insertBoard() 기능 처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}	
	
	public void updateBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 updateBoard() 기능 처리");
		mybatis.update("BoardDAO.updateBoard", vo);
	}	
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 deleteBoard() 기능 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("====> Mybatis로 getBoard() 기능 처리");
		return (BoardVO)mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo, Criteria cri) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("board", vo);
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		paramMap.put("criteria", cri);
		System.out.println("====> Mybatis로 getBoardList() 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardList", paramMap);
	}
	
	public int selectBoardCount(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.selectBoardCount", vo);
	}
}
