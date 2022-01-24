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
					
					<!-- 강사님 코드 		
					<tr>
						<td>3</td>
						<td style="text-align:left; padding-left:0px"><a href="">세 번째 글입니다.</a></td>
						<td>안대혁</td>
						<td>3</td>
						<td>2015-10-11 12:04:20</td>
						<td><a href=""  class="del" style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
					</tr>
					<tr>
						<td>2</td>
						<!--style="padding-left:${(vo.depth-1)*20 }px"  
						<td style="text-align:left; padding-left:20px">
							<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
							<a href="">두 번째 글입니다.</a>
						</td>
						<td>안대혁</td>
						<td>3</td>
						<td>2015-10-02 12:04:12</td>
						<td><a href=""  class="del" style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td style="text-align:left; padding-left:40px">
							<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
							<a href="">첫 번째 글입니다.</a>
						</td>
						<td>안대혁</td>
						<td>3</td>
						<td>2015-09-25 07:24:32</td>
						<td><a href=""  class="del" style='background-image: url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a></td>
					</tr>-->
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
