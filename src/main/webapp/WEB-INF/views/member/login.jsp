<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

</head>
<body id="page-top">
	<div id="wrapper">
		<!-- sidebar -->
		<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>

				<div class="container-fluid">
					<!-- page 상세내용 -->
					<div>
						<h3>${param.message}</h3>
						<spring:message code="${param.message}" var="msg"></spring:message>
						<h3>${msg}</h3>
					</div>
					<form:form modelAttribute="memberVO" method="post">
						<div class="form-group">
							<form:label path="username">Username</form:label>
							<form:input id="username" path="username" cssClass="form-control"/>
							<form:errors path="username"></form:errors>
						</div>
						<div class="form-group">
							<form:label path="password">Password</form:label>
							<form:password id="password" path="password" cssClass="form-control"/>
							<form:errors path="password"></form:errors>
						</div>
						<div class="form-group">
							<label for="remember">RememberMe</label>
							<input type="checkbox" name="remember-me" id="remember" class="form-control">
						</div>
						
						
						<button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
					<a href="/oauth2/authorization/kakao">KakaoLogin</a>
				</div>

			</div>
			<c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
		</div>
	</div>

	<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
	<script type="text/javascript">
		let m = '${msg}';
	
		if(m!=''){
			alert(m);
		}
		
		history.replaceState({}, null, location.pathname); //겟방식 url 파라미터 감추기
	</script>
</body>
</html>