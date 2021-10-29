<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@ page import="com.springbook.biz.board.BoardVO"%>

<%
	//1. 사용자 입력정보 추출(getBoard.jsp에서 전송된 정보 추출)
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	String title = request.getParameter("title");
	String content = request.getParameter("content");

	//2. DB 연동 처리 (BoardDAO의 메소드 호출)
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	vo.setTitle(title);
	vo.setContent(content);
	
	BoardDAO boardDAO = new BoardDAO();
	boardDAO.updateBoard(vo);
	
	//3. 화면 네비게이션
	//수정처리 후 글 목록 화면으로 이동
	response.sendRedirect("getBoardList.jsp");
%>