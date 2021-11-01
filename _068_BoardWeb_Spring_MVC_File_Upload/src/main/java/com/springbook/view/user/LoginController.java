package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

//SpringFramework에서 제공하는 Controller, interface의 handleRequest메소드는 리턴 타입 ModelAndView
@Controller
public class LoginController {
	//GET방식으로 요청이 오면 로그인화면으로, Post방식으로 요청이 오면 로그인 처리
	//@RequestMapping(value="/login.do", method = RequestMethod.GET)
	@GetMapping(value="/login.do")	//위 코드와 같은 의미
	//userVO의 이름으로 Command 객체가 생성됨
	//이름을 지정 안할 시에 자동으로 첫 글자가 소문자로 변경된 이름으로 생성됨
	//Command의 이름 지정 : @ModelAttribute("user") -> user의 이름의 Command 객체 생성
	public String loginView(@ModelAttribute("user")UserVO vo) {
		System.out.println("로그인 화면으로 이동");
		//로그인화면으로 이동시 처음 입력값 세팅
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}
	
	//@RequestMapping(value="/login.do", method = RequestMethod.POST)
	@PostMapping(value="/login.do")
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		System.out.println("로그인 처리");
		UserVO user = userDAO.getUser(vo);
		//로그인 성공 시 게시판 목록 조회 화면으로 이동
		if(userDAO.getUser(vo) != null) {
			//redirect : viewResolver를 무시하고 바로 리다이렉트
			//UserVO의 name 변수를 session 데이터 보관소에 userNAme이라는 키 값으로 저장
			session.setAttribute("userName", user.getName());
			//UserVO 자체를 session 데이터 보관소에 user라는 키 값으로 저장
			//session.setAttribute("user", user);
			return "redirect:getBoardList.do";
		//로그인 실패 시 다시 로그인 화면으로 이동
		} else {
			return "login.jsp";
		}
	}
}
