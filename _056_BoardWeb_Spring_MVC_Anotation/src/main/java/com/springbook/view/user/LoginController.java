package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

//SpringFramework에서 제공하는 Controller, interface의 handleRequest메소드는 리턴 타입 ModelAndView
@Controller
public class LoginController {
	@RequestMapping(value="/login.do")
	public String login(UserVO vo, UserDAO userDAO) {
		System.out.println("로그인 처리");
		
		//로그인 성공 시 게시판 목록 조회 화면으로 이동
		if(userDAO.getUser(vo) != null) {
			//redirect : viewResolver를 무시하고 바로 리다이렉트
			return "redirect:getBoardList.do";
		//로그인 실패 시 다시 로그인 화면으로 이동
		} else {
			return "redirect:getBoardList.do";
		}
	}
}
