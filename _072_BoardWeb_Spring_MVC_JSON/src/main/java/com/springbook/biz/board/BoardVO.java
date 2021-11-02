package com.springbook.biz.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String SearchCondition;
	private String SearchKeyword;
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
