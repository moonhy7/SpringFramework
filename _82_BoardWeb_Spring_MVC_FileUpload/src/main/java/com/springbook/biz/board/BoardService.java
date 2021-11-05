package com.springbook.biz.board;

import java.util.List;

public interface BoardService {
	//CRUD 기능의 메소드 구현
	//글 등록
	void insertBoard(BoardVO vo);
	
	//글 수정
	void updateBoard(BoardVO vo);
	
	//글 삭제
	void deleteBoard(BoardVO vo);
	
	//글 상세 조회
	BoardVO getBoard(BoardVO vo);
	
	//글 목록 조회
	List<BoardVO> getBoardList(BoardVO vo, Criteria cri);
	
	int selectBoardCount(BoardVO vo);
	
	//글 등록 전 등록 될 일련번호 획득
	int getBoardSeq();
	
	//디비에 파일 리스트 등록
	void insertBoardFileList(List<BoardFileVO> fileList);
	
	//파일목록 리턴
	List<BoardFileVO> getBoardFileList(int seq);
	
	//파일 삭제
	void deleteFile(BoardFileVO vo);
	
	//게시글 삭제시 해당 게시글의 첨부파일 삭제
	void deleteFileList(int seq);
	
}
