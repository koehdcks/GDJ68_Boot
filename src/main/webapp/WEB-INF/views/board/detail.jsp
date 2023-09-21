<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	         	<h1>detail Page</h1>
	         	<h1>글번호:${boardVO.boardNo}</h1>
	         	<h1>글제목:${boardVO.boardTitle}</h1>
	         	<h1>작성자:${boardVO.boardWriter}</h1>
	         	<h1>내용:${boardVO.boardContents}</h1>
	       		<div class="row">
	       			<c:forEach items="${boardVO.list}" var="f">
	       				<img alt="" src="/files/${board}/${f.fileName}">
	       				<a href="./fileDown?fileNo=${f.fileNo}">${f.oriName}</a>
	       			</c:forEach>
	       		</div>
	         </div>
	         <a href="./update?boardNo=${boardVO.boardNo}">수정</a>
	         <a href="./delete?boardNo=${boardVO.boardNo }">삭제</a>
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>