<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<center>
		<h1>글 상세</h1>
		<a href="logout.do">Log-out</a>
		<form action="updateBoard.do" method="post" enctype="multipart/form-data">
			<input type="hidden" id="pageNum" name="pageNum" value=${criteria.pageNum }>
			<input type="hidden" id="amount"name="amount" value=${criteria.amount }>
			<input type="hidden" id="seq" name="seq" value="${board.seq }">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td align="left"><input name="title" type="text" value="${board.title }"></td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td align="left">${board.writer }</td>
				</tr>
				<tr>
					<td bgcolor="orange">내용</td>
					<td align="left">
						<textarea name="content" cols="40" rows="10">${board.content }</textarea>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">등록일</td>
					<td align="left">${board.regDate }</td>
				</tr>
				<tr>
					<td bgcolor="orange">조회수</td>
					<td align="left">${board.cnt }</td>
				</tr>
				<tr>
					<td bgcolor="orange">첨부파일 목록</td>
					<td>
						<c:forEach var="file" items="${fileList }">
							<a class="downlink" id="${file.fSeq }" href="${file.originalFileName }">${file.originalFileName }</a>
							<button type="button" onclick="deleteFile('${file.fSeq }');">삭제</button>
							<br>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70">업로드</td>
					<td align="left">
						<input type="file" name="uploadFiles" multiple="multiple">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 수정">
					</td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="insertBoard.jsp">글 등록</a>&nbsp;&nbsp;&nbsp;
		<a href="deleteBoard.do?seq=${board.seq }">글 삭제</a>&nbsp;&nbsp;&nbsp;
		<a href="getBoardList.do?pageNum=${criteria.pageNum }&amount=${criteria.amount}">글 목록</a>
	</center>
	<script>
		function deleteFile(fSeq) {
			var data = {"seq" : $("#seq").val(), "fSeq" : fSeq};
			$.ajax({
				url : "deleteFile.do",
				type : "POST",
				data : data,
				success : function() {
					location.reload();
				}
			});
		}
		
		$(document).ready(function() {
			$(".downlink").click(function(e){
				e.preventDefault();
				var fileName = $(this).attr("href");
				window.location = "fileDown.do?fileName=" + fileName; 
			});
		});
	</script>
</body>
</html>