package com.springbook.biz.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Board
 *
 */
//@Entity : 객체를 Entity 클래스로 선언. 테이블과 1:1 매핑됨
@Entity
//@Table : Entity 클래스가 어떤 테이블과 매핑될지 설정
//		기본적으로 엔티티 클래스 명으로 된 테이블이 매핑이 되는데 Table 어노테이션을 사용하면 다른 이름의 테이블과 매핑 가능
//		UniqueConstraints : 유니크 인덱스 지정, 여러 개 컬럼을 묶어서 유니크 인덱스로 지정할 때 사용
//		ex) @UniqueConstraints(columnNames={"SEQ", "WRITER"}) 1, 홍길동 -> 1, 관리자(O)
//															  1, 홍길동 -> 1, 홍길동(X)
@Table(name="BOARD")
public class Board {
	//@Id : 테이블의 기본 키 값 설정
	@Id
	
	/*
	 * //oracle은 seq가 자동 생성되지 않으므로 아래 두 줄로 대체
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Board_Seq")
	 * //@SequenceGenerator : 시퀀스 생성기. 지정한 sequence 이름으로 시퀀스 생성
	 * @SequenceGenerator(name = "Board_Seq", sequenceName = "Board_Seq")
	 */
	
	//@GeneratedValue : 기본 키값 자동 생성하여 할당, 데이터베이스마다 방식이 다름.
	//strategy : 기본 키 값 자동 생성 방식 설정.
	//GenerationType : TABLE -> PK값 생성을 위한 별도의 테이블을 이용하여 PK 생성. 별도의 테이블 따로 만들어줘야 됨.
	//				   SEQUENCE -> 데이터베이스에 저장되어 있는 시퀀스를 이용하여 키 생성(우리가 설정한 생성 전략임)
	//				   IDENTITY -> AUTO_INCREMENT나 IDENTITY를 이용하여 키 생성
	// 				   AUTO -> 데이터베이스에 맞게 자동 설정되어 키 생성
	//generator : Sequence 이름 지정
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int seq;
	private String title;
	private String writer;
	private String content;
	//@Temporal : 날짜 타입을 매핑할 때 사용하는 어노테이션
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();
	private int cnt;
	@Transient	// 엔티티 클래스 내의 특정 변수를 영속 필드에서 제외할 때 사용한다.
	private String searchCondition;
	@Transient
	private String searchKeyword;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + "]";
	}

}
