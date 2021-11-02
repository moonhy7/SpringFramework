package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
//board로 model 저장된 객체가 있으면 HttpSession 데이터 보관소에서 동일한 키 값(board)로 저장
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	//@ResponseBody : 메소드 리턴 값을 응답 바디로 설정
	//				     메소드 결과 값 자체가 응답 바디가 됨
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
	//@ModelAttribute : 1. Command 객체 이름 지정
	//					2. View(JSP)에서 사용할 데이터 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		//리턴 값은 ReqeustServlet 데이터 보관소에 저장
		//conditionMap이라는 키 값으로 데이터가 저장
		return conditionMap;
	}
	
	@RequestMapping(value="/insertBoard.do")
	//Command 객체 : 사용자가 전송한 데이터를 매핑한 VO를 바로 생성
	//				사용자 입력 값이 많아지면 코드가 길어지기 때문에 간략화 가능
	//              사용자 입력 input의 name 속성과 VO 멤버변수의 이름을 매핑해주는 것이 중요
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("글 등록 처리");
		
		//파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("C:/Dev211/" + fileName));
		}
		
		boardService.insertBoard(vo);
		
		//화면 네비게이션(게시글 등록 완료 후 게시글 목록으로 이동)
		return "redirect:getBoardList.do";
	}
	
	//ModelAttribute로 세션에 board라는 이름으로 저장된 객체가 있는지 찾아서 Command객체에 담아줌
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("글 수정 처리");
		System.out.println("일련번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 이름 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("글 삭제 처리");
		
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("글 상세 조회 처리");
		
		//Model 객체는 RequestServlet 데이터 보관소에 저장
		//RequestServlet 데이터 보관소에 저장하는 것과 동일하게 동작
		//request.setAttribute("board", boardDAO.getBoard(vo)) == model.addAttribute("board", boardDAO.getBoard(vo))
		model.addAttribute("board", boardService.getBoard(vo));
		return "getBoard.jsp";
	}
	
	@RequestMapping(value="/getBoardList.do")
	//@RequestParam : Command 객체인 VO에 매핑값이 없는 사용자 입력정보는 직접 받아서 처리
	//				  value = 화면으로부터 전달된 파라미터 이름(jsp의 input의 name속성 값)
	//				  required = 생략 가능 여부
	public String getBoardList( /*
								 * @RequestParam(value="searchCondition", defaultValue="TITLE", required=false)
								 * String condition,
								 * 
								 * @RequestParam(value="searchKeyword", defaultValue="", required=false) String
								 * keyword,
								 */
								BoardVO vo, Model model) {
		System.out.println("글 목록 검색 처리");
		
		//Null check
		//로그인 화면에서 로그인성공 시 getBoardList.do 호출 할 때 searchKeyword, searchCondition 값의 null 방지 
		if(vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if(vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		return "getBoardList.jsp";
	}
}
