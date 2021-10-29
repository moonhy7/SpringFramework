<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@ page import="com.springbook.biz.board.BoardVO"%>

<%
	//1. 사용자 입력정보 추출(insertBoard.jsp에서 전송된 정보 추출)
	//입력된 정보가 한글일 경우 깨짐 방지
	request.setCharacterEncoding("UTF-8");
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
	response.sendRedirect("getBoardList.jsp");
%>