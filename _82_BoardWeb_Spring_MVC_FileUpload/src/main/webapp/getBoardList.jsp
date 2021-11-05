<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.board.list.mainTitle"/></title>
<script src="js/jquery-3.6.0.min.js"></script>
	<style>
		ul {
			list-style: none;
			width : 30%;
			display: inline-block;
		}
		
		li {
			float: left;
			margin-left : 5px;
		}
	</style>
</head>
<body>
	<center>
		<h1><spring:message code="message.board.list.mainTitle"/></h1>
		<h3>${userName }<spring:message code="message.board.list.welcomeMSG"/><a href="logout.do">Log-out</a></h3>
		
		<!-- 검색 시작 -->
		<form action="getBoardList.do" method="post" id="listForm">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<c:forEach var="condition" items="${conditionMap }">
								<option value="${condition.value }">${condition.key }</option>
							</c:forEach>
						</select>
						<input name="searchKeyword" type="text">
						<input type="submit" value="검색">
					</td>
				</tr>
			</table>			
		</form>
		<!-- 검색 종료 -->
		
		<table border="1" cellpadding="1" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq"/></th>
				<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>
				<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>
				<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
				<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
			</tr>
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.seq }</td>
					<td><a href="getBoard.do?seq=${board.seq }&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}">${board.title}</a></td>
					<td>${board.writer }</td>
					<td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/></td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<ul class="pagination">
				<c:if test="${pageMaker.prev }">
					<li class="pagination_button">
						<a href="${pageMaker.startPage - 1 }">Previous</a>
					</li>
				</c:if>
				
				<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
					<li class="pagination_button">
						<a href="${num }">${num }</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next }">
					<li class="pagination_button">
						<a href="${pageMaker.endPage + 1 }">Next</a>
					</li>
				</c:if>
			</ul>
		</div>
		<br>
		<a href="insertBoard.do">
			새 글 등록
		</a>
	</center>
</body>
</html>