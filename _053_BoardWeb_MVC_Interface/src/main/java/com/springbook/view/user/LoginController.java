package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;
import com.springbook.view.controller.Controller;

public class LoginController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		
		//1. 사용자 입력 정보 추출(login.jsp에서 전송한 id, password 받기)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//2. DB연동 처리(UserDAO의 메소드 호출)
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		//3. 화면 네비게이션(로그인 후 화면 이동 처리)
		//로그인 성공 시 게시판 목록 조회 화면으로 이동
		if(user != null) {
			/*
				foward, include : RequestDispatcher를 이용하여 응답으로 보여줄 화면을 리턴
								   한 번의 요청과 응답으로 처리되므로 속도가 빠르고 URL 변경되지 않음
								   이전 페이지에서 작성한 데이터의 공유가 가능함
								  forward : 이동할 페이지에 제어권을 영구적으로 넘겨줌
								  include : 이동할 페이지의 처리가 끝나면 다시 이전 페이지에 제어궘을 넘겨줌
				redirect : 이동할 JSP에서 브라우저로 응답메시지를 보내주고 다시 서버로 재요청 방식
				                   속도가 forward, include에 비해서 느리고 URL이 이동할 페이지로 변경
				                   요청과 응답 객체를 새로 생성해서 전송하기 때문에 이전 페이지에서 작성한 데이터 공유가 불가능
			*/
			return "getBoardList.do";
		//로그인 실패 시 다시 로그인 화면으로 이동
		} else {
			return "login";
		}
	}
}
