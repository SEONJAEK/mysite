<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
						
					<!-- 찐 -->
					<c:set var="count" value="${fn:length(boardList)}"/>
					<c:forEach var="board" items="${boardList }" varStatus="status">
					<tr>
						<td>${count-status.index}</td>
						<td style = "text-align:left;padding-left:${(board.depth-1)*20 }px"><c:if test="${board.depth > 1 }"><img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" /></c:if><a href="${pageContext.servletContext.contextPath }/board?a=view&no=${board.no}">${board.title }</a></td>
						<td>${board.userName }</td>
						<td>${board.hit }</td>
						<td>${board.regDate }</td>
						<c:if test="${board.userNo eq authUser.no }">
							<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${board.no}"  class="del" 
							style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
						</c:if>
					</tr>
					</c:forEach>	
					
		
				</table>
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test='${page.currentpage==1 }'>
								<c:forEach var="pagenum" begin="1" end="${page.listcnt}">
									<li><a href="${pageContext.servletContext.contextPath}/board?page=${pagenum}&kwd=${param.kwd}">${pagenum}</a></li>														
								</c:forEach>
								<li><a href="${pageContext.servletContext.contextPath}/board?page=${page.currentpage+page.nextpage}&kwd=${param.kwd}">▶</a></li>
								
							</c:when>
							
							<c:when test='${page.currentpage==page.listcnt}'>
							<li><a href="${pageContext.servletContext.contextPath}/board?page=${page.currentpage+page.prepage}&kwd=${param.kwd}">◀</a></li>
							
								<c:forEach var="pagenum" begin="1" end="${page.listcnt}">
									<li><a href="${pageContext.servletContext.contextPath}/board?page=${pagenum}&kwd=${param.kwd}">${pagenum}</a></li>														
								</c:forEach>
							</c:when>
							
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath}/board?page=${page.currentpage+page.prepage}&kwd=${param.kwd}">◀</a></li>
								<c:forEach var="pagenum" begin="1" end="${page.listcnt}">
									<li><a href="${pageContext.servletContext.contextPath}/board?page=${pagenum}&kwd=${param.kwd}">${pagenum}</a></li>					
								</c:forEach>
								<li><a href="${pageContext.servletContext.contextPath}/board?page=${page.currentpage+page.nextpage}&kwd=${param.kwd}">▶</a></li>
								
							</c:otherwise>
						</c:choose>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
