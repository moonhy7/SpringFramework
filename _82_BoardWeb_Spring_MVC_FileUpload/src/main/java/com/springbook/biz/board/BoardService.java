package com.springbook.biz.board;

import java.util.List;

public interface BoardService {
	//CRUD ����� �޼ҵ� ����
	//�� ���
	void insertBoard(BoardVO vo);
	
	//�� ����
	void updateBoard(BoardVO vo);
	
	//�� ����
	void deleteBoard(BoardVO vo);
	
	//�� �� ��ȸ
	BoardVO getBoard(BoardVO vo);
	
	//�� ��� ��ȸ
	List<BoardVO> getBoardList(BoardVO vo, Criteria cri);
	
	int selectBoardCount(BoardVO vo);
	
	//�� ��� �� ��� �� �Ϸù�ȣ ȹ��
	int getBoardSeq();
	
	//��� ���� ����Ʈ ���
	void insertBoardFileList(List<BoardFileVO> fileList);
	
	//���ϸ�� ����
	List<BoardFileVO> getBoardFileList(int seq);
	
	//���� ����
	void deleteFile(BoardFileVO vo);
	
	void deleteFileList(int seq);
	
}