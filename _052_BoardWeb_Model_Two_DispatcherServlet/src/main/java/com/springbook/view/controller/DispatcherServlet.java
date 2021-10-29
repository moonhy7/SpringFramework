package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//클라이언트에서 전송하는 데이터가 한글일 경우 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. 클라이언트 요청  path 정보를 추출한다.
		String uri = request.getRequestURI();
		//  http://localhost:9900/biz/login.do
		String path = uri.substring(uri.lastIndexOf("/"));
		//  /login.do
		System.out.println(path);
		
		//2. 클라이언트 요청 path에 따라 적절히 분기처리한다.
		if(path.equals("/login.do")) {
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
				response.sendRedirect("getBoardList.do");
			//로그인 실패 시 다시 로그인 화면으로 이동
			} else {
				response.sendRedirect("login.jsp");
			}
		} else if(path.equals("/logout.do")) {
			System.out.println("로그아웃 처리");
			
			//1. 브라우저와 연결된 세션 객체를 강제 종료한다.
			HttpSession session = request.getSession();
			session.invalidate();

			//2. 세션 종료 후, 로그인 화면으로 이동
			response.sendRedirect("login.jsp");
		} else if(path.equals("/insertBoard.do")) {
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
			response.sendRedirect("getBoardList.do");
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("글 수정 처리");
			
			//1. 사용자 입력정보 추출(getBoard.jsp에서 전송된 정보 추출)
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//2. DB 연동 처리(BoardDAO의 메소드 호출)
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setTitle(title);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);
			
			//3. 화면 네비게이션
			//수정처리 후 글 목록 화면으로 이동
			response.sendRedirect("getBoardList.do");
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");
			
			//1.사용자 입력정보 추출(getBoard.jsp에서 전송된 정보 추출)
			String seq = request.getParameter("seq");

			//2. DB 연동 처리(BoardDAO의 메소드 호출)
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);
			
			//3. 화면 네비게이션
			//글 삭제 처리 후 글 목록 화면으로 이동
			response.sendRedirect("getBoardList.do");
		} else if(path.equals("/getBoard.do")) {
			System.out.println("글 상세 조회 처리");
			
			//1. 상세 조회할 게시글 번호 추출
			String seq = request.getParameter("seq");

			//2. DB 연동 처리(BoardDAO의 메소드 호출)
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			//3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("글 목록 검색 처리");
			
			//1. 사용자 입력정보 추출(검색기능 나중에 구현)
			//2. DB 연동 처리(BoardDAO의 메소드 호출)
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			//3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			response.sendRedirect("getBoardList.jsp");
		} 
	}
}
