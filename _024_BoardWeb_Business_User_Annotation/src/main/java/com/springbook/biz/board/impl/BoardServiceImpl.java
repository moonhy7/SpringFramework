package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
/*
 * Service 클래스는 jsp/servlet할 때 안 배웠음 
 * 업무로직 처리를 담당하는 클래스
 * ex) 계좌이체 기능 처리할 때 DB입력은 DAO에서 하게 되는데
 * DB 입력 전에 필요한 작업들(계좌 암호화, 금액 컴마 추가...) 하는 로직들을 ServiceImpl클래스에서 처리해주면 됨 
 * */
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
}
