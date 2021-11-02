package com.springbook.biz.board;

//sql -> util 로 변환
//이유 : xml로 변화하려면 기본 생성자가 필요한데 java.sql.Date는 기본 생성자가 없다.
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@XmlAccessorType : 해당 객체를 Xml로 변환 가능하다는 선언
//XmlAccessType.FIELD : 해당 객체의 필드변수로 XML 변환
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardVO {
	//@XmlAttribute : 해당 필드변수가 속성값이 됨
	@XmlAttribute
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	//@XmlTransient : xml 변환시 특정 값 제외 == JSON 변환에서 @JsonIgnore와 같은 개념
	@XmlTransient
	private String SearchCondition;
	@XmlTransient
	private String SearchKeyword;
	@XmlTransient
	private MultipartFile uploadFile;
	
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
	//@JsonIgnore : Json형식의 데이터 만들 때 특정값 제외
	@JsonIgnore
	public String getSearchCondition() {
		return SearchCondition;
	}
	
	public void setSearchCondition(String searchCondition) {
		SearchCondition = searchCondition;
	}
	
	@JsonIgnore
	public String getSearchKeyword() {
		return SearchKeyword;
	}
	
	public void setSearchKeyword(String searchKeyword) {
		SearchKeyword = searchKeyword;
	}
	@JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + "]";
	}	
}
