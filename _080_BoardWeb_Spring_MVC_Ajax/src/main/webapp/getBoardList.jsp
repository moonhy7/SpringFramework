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
</head>
<body>
	<center>
		<h1><spring:message code="message.board.list.mainTitle"/></h1>
		<h3>${userName }<spring:message code="message.board.list.welcomeMSG"/><a href="logout.do">Log-out</a></h3>
		
		<!-- 검색 시작 -->
		<form id="form_Search">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<c:forEach var="condition" items="${conditionMap }">
								<option value="${condition.value }">${condition.key }</option>
							</c:forEach>
						</select>
						<input name="searchKeyword" type="text">
						<input id="btn_Search" type="submit" value="<spring:message code="message.board.list.search.condition.btn"/>">
					</td>
				</tr>
			</table>			
		</form>
		<!-- 검색 종료 -->
		
		<table border="1" cellpadding="1" cellspacing="0" width="700" id= "tblresult">
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
					<td><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
					<td>${board.writer }</td>
					<td><fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/></td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<a href="insertBoard.jsp">
			<spring:message code="message.board.list.link.insertBoard"/>
		</a>
	</center>
	<script>
		$(document).ready(function() {
			$("#btn_Search").click(function() {
				$.ajax({
					url : 'getBoardListSearch.do',
					type : 'POST',
					data : $("#form_Search").serialize(),
					success : function(obg) {
						console.log(obj);
						var data = JSON.parse(obj);
						console.log(data.boardList); //배열로 가져옴
						var htmlString = "";
						htmlString += "<tr>";
						htmlString += '		<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq"/></th>		';
						htmlString += '		<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>		';
						htmlString += '		<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>		';
						htmlString += '		<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>	';
						htmlString += '		<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>		';
						htmlString += "</tr>"
						for(var i = 0; i < data.boardList.length; i++) {
							htmlString += "<tr>";
							htmlString += "		<td>" + data.boardList[i].seq + "</td>"; 
							htmlString += "		<td>";
							htmlString += "			<a href='getBoard.do?seq=" + data.boardList[i].seq + "'>" + data.boardList[i].title + "</a>"; 
							htmlString += "		</td>"; 
							htmlString += "		<td>" + data.boardList[i].writer + "</td>"; 
							htmlString += "		<td>" + data.boardList[i].regDate + "</td>"; 
							htmlString += "		<td>" + data.boardList[i].cnt + "</td>"; 
							htmlString += "</tr>";	
						}
						
						$("#tblresult").html(htmlString);
					},
					error : function(e) {
						console.log(e);
					}
				});
			});
		});
	</script>
</body>
</html>