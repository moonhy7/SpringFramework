package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.view.controller.Controller;

public class InsertBoardController implements Controller {
 @Override 
 public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
	 System.out.println("글 등록 처리");
		
		//1. 사용자 입력정보 추출(insertBoard.jsp에서 전송된 정보 추출)
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//2. DB 연동 처리(BoardDAO의 메소드 호출)
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertBoard(vo);
		
		//3. 화면 네비게이션
		//글 등록 처리 후 글 목록 페이지로 이동
		return "getBoardList.do";
 }
}
