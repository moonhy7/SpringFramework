package com.springbook.biz.board.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOJPA {
	//@PersistenceContext : 스프링 컨테이너가 관리하는 EntityManager 객체를 의존성 주입할 때 사용
	@PersistenceContext
	private EntityManager em;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("====> JPA로 insertBoard() 기능 처리");
		em.persist(vo);
	}	
	
	public void updateBoard(BoardVO vo) {
		System.out.println("====> JPA로 updateBoard() 기능 처리");
		em.merge(vo);
	}	
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("====> JPA로 deleteBoard() 기능 처리");
		em.remove(em.find(BoardVO.class, vo.getSeq()));
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("====> JPA로 getBoard() 기능 처리");
		return (BoardVO)em.find(BoardVO.class, vo.getSeq());
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("====> JPA로 getBoardList() 기능 처리");
		return em.createQuery("from BoardVO b order by b.seq desc").getResultList();
	}
}
