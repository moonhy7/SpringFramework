package com.springbook.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class GetBoardListController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 목록 검색 처리");
		
		//1. 사용자 입력정보 추출(검색기능 나중에 구현)
		//2. DB 연동 처리(BoardDAO의 메소드 호출)
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
		ModelAndView mav = new ModelAndView();
		//ModelAndView 객체에 데이터 담기
		mav.addObject("boardList", boardList);
		//ModelAndView 객체에 View 담기
		mav.setViewName("getBoardList");
		return mav;
	}
}
