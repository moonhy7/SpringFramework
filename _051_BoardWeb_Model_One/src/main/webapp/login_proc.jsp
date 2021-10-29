<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.springbook.biz.user.impl.UserDAO" %>
<%@page import="com.springbook.biz.user.UserVO" %>
<%
	//1. 사용자 입력 정보 추출(login.jsp 에서 전송한 id, password 받기)
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	//2. DB연동 처리(UserDAO의 메소드 호출)
	UserVO vo = new UserVO();
	vo.setId(id);
	vo.setPassword(password);
	
	UserDAO userDAO = new UserDAO();
	UserVO user = userDAO.getUser(vo);
	
	//3. 화면 네비게이션(로그인 후 화면 이름 처리)
	//로그인 성공 시 게시판 목록 조회 화면으로 이동
	if(user != null) {
		response.sendRedirect("getBoardList.jsp");
	//로그인 실패 시 다시 로그인 화면으로 이동
	} else {
		response.sendRedirect("login.jsp");
	}
%>

