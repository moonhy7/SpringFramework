package com.springbook.biz.board;

public class BoardFileVO {
	private int seq;
	private int fSeq;
	private String originalFileName;
	private String filePath;
	private long fileSize;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getfSeq() {
		return fSeq;
	}
	public void setfSeq(int fSeq) {
		this.fSeq = fSeq;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Override
	public String toString() {
		return "BoardFileVO [seq=" + seq + ", fSeq=" + fSeq + ", originalFileName=" + originalFileName + ", filePath="
				+ filePath + ", fileSize=" + fileSize + "]";
	}
}
