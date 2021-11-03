<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- spring:message="메시지 키값" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.user.login.title"/></title>
</head>
<body>
	<center>
		<h1><spring:message code="message.user.login.title"/></h1>
		<a href="login.do?lang=en">
			<spring:message code="message.user.login.language.en"/>
		</a>&nbsp;&nbsp;
		<a href="login.do?lang=ko">
			<spring:message code="message.user.login.language.ko"/>
		</a>
		<hr>
		<!-- login_proc.jsp : 컨트롤러에 해당 jsp 파일. 로그인 기능처리 -->
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.id"/></td>
					<td><input type="text" name="id" value="${user.id }"></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.password"/></td>
					<td><input type="password" name="password" value="${user.password }"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="<spring:message code="message.user.login.loginBtn"/>"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>