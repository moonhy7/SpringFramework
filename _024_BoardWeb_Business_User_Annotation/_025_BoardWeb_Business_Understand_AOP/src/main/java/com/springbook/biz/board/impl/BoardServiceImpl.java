package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.Log4jAdvice;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
/*
 * 업무로직 처리를 담당하는 클래스
 * ex) 계좌이체 기능 처리할 때 DB입력은 DAO 하게 되는데 
 * 	   DB입력 전 필요한 작업들(계좌암호화, 금액 컴마 추가...) 하는 로직들을 ServiceImpl클래스에서 처리해주면 됨 
 * */
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	private LogAdvice log;
	
	public BoardServiceImpl() {
		log = new LogAdvice();
	}
	
	public void insertBoard(BoardVO vo) {
		log.printLog();
		boardDAO.insertBoard(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		log.printLog();
		boardDAO.upadateBoard(vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		log.printLog();
		boardDAO.deleteBoard(vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		log.printLog();
		return boardDAO.getBoard(vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		log.printLog();
		return boardDAO.getBoardList(vo);
	}
}
