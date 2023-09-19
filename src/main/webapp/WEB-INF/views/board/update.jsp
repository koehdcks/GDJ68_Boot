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
	         	<div class="row">
						<form action="update" method="post">
							<input type="hidden" name=boardNo value=${boardVO.boardNo}>
							<div class="mb-3">
								<label for="boardTitle" class="form-label">Title
									</label> <input type="text" class="form-control"
									id="boardTitle" name="boardTitle" value="${boardVO.boardTitle}">
							</div>
							<div class="mb-3">
								<label for="boardWriter" class="form-label">Writer
									</label> <input type="text" class="form-control"
									id="boardWriter" name="boardWriter" value="${boardVO.boardWriter}" readonly>
							</div>
							<div class="mb-3">	
								<label for="boardContents" class="form-label">Contents</label>
								<textarea class="form-control" id="boardContents" name="boardContents"
									rows="3">${boardVO.boardContents}</textarea>
							</div>
							<button type="submit">수정</button>
						</form>
					</div>
	         </div>
         
         </div>
         <c:import url="/WEB-INF/views/layout/btnbar.jsp"></c:import>
      </div>
   </div>
   
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</body>
</html>