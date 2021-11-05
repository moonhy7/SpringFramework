package com.springbook.view.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springbook.biz.board.BoardFileVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.Criteria;
import com.springbook.biz.board.PageVO;
import com.springbook.biz.common.FileUtils;

@Controller
//board로 model 저장된 객체가 있으면 HttpSession 데이터 보관소에서 동일한 키 값(board)로 저장
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
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

	
	@RequestMapping("/insertBoard.do")
	//Command 객체 : 사용자가 전송한 데이터를 매핑한 VO를 바로 생성
	//				사용자 입력 값이 많아지면 코드가 길어지기 때문에 간략화 가능
	//              사용자 입력 input의 name 속성과 VO 멤버변수의 이름을 매핑해주는 것이 중요
	public String insertBoard(BoardVO vo, HttpServletRequest request,
			MultipartHttpServletRequest mhsr) throws IOException {
		System.out.println("글 등록 처리");
		
		int seq = boardService.getBoardSeq();
		System.out.println("seq===============================" + seq);
		FileUtils fileUtils = new FileUtils();
		List<BoardFileVO> fileList = fileUtils.parseFileInfo(seq, request, mhsr);
		
		if(CollectionUtils.isEmpty(fileList) == false) {
			boardService.insertBoardFileList(fileList);
			
		}
		
		boardService.insertBoard(vo);
		
		//화면 네비게이션(게시글 등록 완료 후 게시글 목록으로 이동)
		return "redirect:getBoardList.do";
	}
	
	//ModelAttribute로 세션에 board라는 이름으로 저장된 객체가 있는지 찾아서 Command객체에 담아줌
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, 
			HttpServletRequest request,
			MultipartHttpServletRequest mhsr) throws IOException {
		System.out.println("글 수정 처리");
		System.out.println("일련번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 이름 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());
		
		int seq = vo.getSeq();
		
		FileUtils fileUtils = new FileUtils();
		List<BoardFileVO> fileList = fileUtils.parseFileInfo(seq, request, mhsr);
		
		if(CollectionUtils.isEmpty(fileList) == false) {
			boardService.insertBoardFileList(fileList);
		}
		
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println("글 삭제 처리");
		
		boardService.deleteFileList(vo.getSeq());
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping(value="/getBoard.do")
	public String getBoard(BoardVO vo, Model model, Criteria cri) {
		System.out.println("글 상세 조회 처리");
		
		System.out.println(cri.getPageNum());
		System.out.println(cri.getAmount());
		//Model 객체는 RequestServlet 데이터 보관소에 저장
		//RequestServlet 데이터 보관소에 저장하는 것과 동일하게 동작
		//request.setAttribute("board", boardDAO.getBoard(vo)) == model.addAttribute("board", boardDAO.getBoard(vo))
		model.addAttribute("board", boardService.getBoard(vo));
		model.addAttribute("criteria", cri);
		model.addAttribute("fileList", boardService.getBoardFileList(vo.getSeq()));
		return "getBoard.jsp";
	}
	
	@RequestMapping(value="/getBoardList.do")
	//@RequestParam : Command 객체인 VO에 매핑값이 없는 사용자 입력정보는 직접 받아서 처리
	//				  value = 화면으로부터 전달된 파라미터 이름(jsp의 input의 name속성 값)
	//				  required = 생략 가능 여부
	public String getBoardList(BoardVO vo, Model model, Criteria cri) {
		System.out.println("글 목록 검색 처리");
		System.out.println(vo.getSearchKeyword());
		//Null check
		//로그인 화면에서 로그인성공 시 getBoardList.do 호출 할 때 searchKeyword, searchCondition 값의 null 방지 
		if(vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if(vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		
		int total = boardService.selectBoardCount(vo);
		
		model.addAttribute("boardList", boardService.getBoardList(vo, cri));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		return "getBoardList.jsp";
	}
	
	@RequestMapping(value="/deleteFile.do")
	@ResponseBody
	public void deleteFile(BoardFileVO vo) {
		boardService.deleteFile(vo);
	}
	
	@RequestMapping(value="fileDown.do")
	@ResponseBody
	public ResponseEntity<Resource> fileDown(@RequestParam("fileName") String fileName,
						HttpServletRequest request) throws Exception {
		//업로드 파일 경로
		String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";
		
		//파일경로, 파일명으로 리소스 객체 생성
		Resource resource = new FileSystemResource(path + fileName);
		
		//파일 명
		String resourceName = resource.getFilename();
		
		//Http헤더에 옵션을 추가하기 위해서 헤더 변수 선언
		HttpHeaders headers = new HttpHeaders();
		
		try {
			//헤더에 파일명으로 첨부파일 추가
			headers.add("Content-Disposition", "attachment; filename=" + new String(resourceName.getBytes("UTF-8"),
						"ISO-8859-1"));
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
