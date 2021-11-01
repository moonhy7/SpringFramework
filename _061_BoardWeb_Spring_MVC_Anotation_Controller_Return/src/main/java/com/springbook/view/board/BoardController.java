package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller	// 컨트롤러로 통합하면 하나의 컨트롤러에서 메소드별로 매핑을 다양하게 사용할 수 있게됨
public class BoardController {
	@RequestMapping(value="/insertBoard.do")	//presentation-layer.xml에 HandlerMapping 등록 부분과 같은 역할
	 //@RequestMapping : HandlerMapping 등록, 메소드 타입에 상관없이 호출 시 무조건 실행
	 //메소드 타입에 따라 @GetMapping, @PostMapping으로도 사용이 가능하며 예를 들어, PostMapping일 땐 post 메소드가 매핑되는 방식
	 
	 //Command 객체 : 사용자가 전송한 데이터를 매핑한 VO를 바로 생성
	 //				사용자 입력 값이 많아지면 코드가 길어지기 때문에 간략화 가능
	 // 			사용자 입력 input의 name 속성과 VO 멤버변수의 이름을 매핑해주는 것이 중요하다.
	 // 			그래야 BoardVO 클래스의 멤버변수가 insertBoard에 해당 변수에 자동으로 매핑이 된다. 
	 public String insertBoard(BoardVO vo, BoardDAO boardDAO) {	//VO 객체를 바로 가져옴
		 System.out.println("글 등록 처리");
			
			/*
			 * //1. 사용자 입력정보 추출(insertBoard.jsp에서 전송된 정보 추출) String title =
			 * request.getParameter("title"); String writer =
			 * request.getParameter("writer"); String content =
			 * request.getParameter("content");
			 * 
			 * //2. DB 연동 처리(BoardDAO의 메소드 호출) BoardVO vo = new BoardVO();
			 * vo.setTitle(title); vo.setWriter(writer); vo.setContent(content);
			 */
			
		 	boardDAO.insertBoard(vo);
			
		 	//화면 네비게이션(게시글 등록 완료 후 게시글 목록으로 이동)
			return "redirect:getBoardList.do"; //redirect를 안주면 그냥 화면만 이동되는데, redirect를 주면 주소값 까지 변경됨
	 }
	
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("글 수정 처리");
		
		boardDAO.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("글 삭제 처리");
		
		boardDAO.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println("글 상세 조회 처리");
		
		//Model 객체는 RequestServlet 데이터 보관소에 저장
		//RequestServlet 데이터 보관소에 저장하는 것과 동일하게 동작
		//request.setAttribute("board", boardDAO.getBoard(vo)) == model.addAttribute("board", boardDAO.getBoard(vo))
		model.addAttribute("board", boardDAO.getBoard(vo));
		return "getBoard.jsp";
	}
	
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println("글 목록 검색 처리");
		
		model.addAttribute("boardList", boardDAO.getBoardList(vo));	
		return "getBoardList.jsp";
	}
	
	
	
}
