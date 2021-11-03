package com.springbook.biz.board;

//sql -> util 로 변환
//이유 : xml로 변화하려면 기본 생성자가 필요한데 java.sql.Date는 기본 생성자가 없다.
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "BOARD")
public class BoardVO {
	@Id
	@GeneratedValue
	private int seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.DATE)
	private Date regDate;
	private int cnt;
	@Transient
	private String SearchCondition;
	@Transient
	private String SearchKeyword;
	@Transient
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

	public String getSearchCondition() {
		return SearchCondition;
	}
	
	public void setSearchCondition(String searchCondition) {
		SearchCondition = searchCondition;
	}
	
	public String getSearchKeyword() {
		return SearchKeyword;
	}
	
	public void setSearchKeyword(String searchKeyword) {
		SearchKeyword = searchKeyword;
	}

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
