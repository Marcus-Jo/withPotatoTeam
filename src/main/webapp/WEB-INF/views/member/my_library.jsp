<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#backIcon:hover {
	cursor: pointer;

}
.aTag:hover {
	text-decoration: underline 2px black;
	cursor: pointer;
	color: black;
	
}
</style>
</head>
<body>
<div class="row justify-content-center">
	<div class="col-12" style="background-color: white;">
		<h2 class="text-first fw-bold">대출 / 예약 현황</h2>
		<hr>
	<!-- 제일 윗줄 -->
<%-- 		<div class="row mb-4" style="border-bottom: 2px solid #0b70b9 ;">
			<div class="col-6 text-start">
				<h3 style="font-weight: bold;">대출 / 예약 현황</h3>
			</div>
			<div class="col-6 text-end" style="font-size: 14px;">
				<p>
					<a href="/common/main">
						홈 >
					</a>&nbsp;
						<c:forEach items="${menuList }" var="menu">
							<c:if test="${menuVO.menuCode eq menu.menuCode }">
								<a href="/${menu.menuUri }/myPage?menuCode=${menuVO.menuCode }&sideMenuCode=myPage">
									${menu.menuName } >
								</a>&nbsp;
							</c:if>
						</c:forEach>
						<c:forEach items="${sideMenuList }" var="sideMenu">
							<c:if test="${sideMenuVO.sideMenuCode eq sideMenu.sideMenuCode }">
								<a href="/${sideMenu.menuUri }/${sideMenu.sideMenuUri }?menuCode=${menuVO.menuCode }&sideMenuCode=${sideMenuVO.sideMenuCode }">
									${sideMenu.sideMenuName } >
								</a>
							</c:if>
						</c:forEach>
				<p>
			</div>
		</div> --%>
		<!-- 타이틀 끝 -->
		
		
		<!-- 대출내역 테이블 시작 -->
		<div class="row justify-content-center">
				<div class="col-6 text-start" style="padding-top: 10px;">대출 현황</div>
				<div class="col-6 text-end">
					<img id="backIcon" src="/resources/img/back_off.png" onclick="javascript:history.back();" width="30px;" title="뒤로가기">
				</div>
			<div class="col-12 mb-4">
				<table class="table table-hover text-center mt-2" style="border-top: 2px solid #0b70b9;">
					<colgroup>
						<col width="10%">
						<col width="30%">
						<col width="20%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th class="table-secondary">번호</th>
							<th class="table-secondary">대출한책</th>
							<th class="table-secondary">대출일</th>
							<th class="table-secondary">반납일</th>
							<th class="table-secondary">반납기한</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty rentalList}">
								<tr style="height: 100px;">
									<td colspan="5">대출 기록이 없습니다.</td>
								</tr>
							</c:when>
							
							<c:otherwise>
								<c:forEach items="${rentalList }" var="rental" varStatus="status">
									<tr>
										<td>${status.count }</td>
										<td>
											<a class="aTag" href="/search/bookDetail?menuCode=${menuVO.menuCode}&bookCode=${rental.bookVO.bookCode }">${rental.bookVO.title }</a>	
										</td>
										<td>${rental.rentalDate }</td>
										<c:choose>
											<c:when test="${rental.returnDate eq '년월일' and rental.bookVO.status == 2 }">
												<td>대출중</td>
												<td>
													${rental.limitDate }
												</td>
											</c:when>
											<c:when test="${rental.returnDate eq '년월일' and rental.bookVO.status == 4 }">
												<td>대출중</td>
												<td>
													${rental.limitDate }
												</td>
											</c:when>
											<c:when test="${rental.returnDate eq '년월일' and rental.bookVO.status == 3 }">
												<td>연체중 </td>
												<td>
													${rental.limitDate }
												</td>
											</c:when>
											<c:otherwise>
												<td>${rental.returnDate }</td>
												<td>
													반납완료
												</td>
											</c:otherwise>
										</c:choose>
									</tr>
									</c:forEach>
								</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 대출내역 테이블 끝 -->
		
		
		<!-- 페이징 처리 -->
		<div class="col-12 pageDiv">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:if test="${memberVO.prev }">
						<li class="page-item">
							<a class="page-link" href="/myPage/myLibrary?nowPage=${memberVO.startPage - 1 }&menuCode=${menuVO.menuCode}" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if> 
					<c:forEach var="pageNum" begin="${memberVO.startPage }" end="${memberVO.endPage }">
						<li class="page-item  <c:if test="${memberVO.nowPage eq pageNum }">active</c:if>">
							<a class="page-link" href="/myPage/myLibrary?nowPage=${pageNum }&menuCode=${menuVO.menuCode}">${pageNum }</a>
						</li>
					</c:forEach>
					<c:if test="${rentalVO.next }">
						<li class="page-item">
							<a class="page-link" href="/myPage/myLibrary?nowPage=${memberVO.endPage + 1 }&menuCode=${menuVO.menuCode}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
			</nav>
		</div>
		<!-- 페이징 끝 -->
		
		<!-- 예약 현황 -->
		<div class="row justify-content-center">
			<div class="col-12 mb-4">
				<div class="co-12">예약 현황</div>
				<table class="table table-hover text-center mt-2" style="border-top: 2px solid #0b70b9;">
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th class="table-secondary">번호</th>
							<th class="table-secondary">예약한책</th>
							<th class="table-secondary">도서상태</th>
							<th class="table-secondary">대여가능일시</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty reserveList}">
								<tr style="height: 100px;">
									<td colspan="4">예약 중인 책이 없습니다</td>
								</tr>
							</c:when>
							
							<c:otherwise>
								<c:forEach items="${reserveList }" var="rental" varStatus="status">
									<tr>
										<td>${status.count }</td>
										<td>
											<a class="aTag" href="/search/bookDetail?menuCode=${menuVO.menuCode}&bookCode=${rental.bookVO.bookCode }">${rental.bookVO.title }</a>	
										</td>
										<c:if test="${rental.bookVO.status eq 1}">
											<td>대출가능</td>
										</c:if>
										<c:if test="${rental.bookVO.status eq 2}">
											<td>대출중</td>
										</c:if>
										<c:if test="${rental.bookVO.status eq 3}">
											<td>연체중</td>
										</c:if>
										<c:if test="${rental.bookVO.status eq 4}">
											<td>예약중</td>
										</c:if>
										<c:if test="${rental.bookVO.status eq 5}">
											<td>대출대기중</td>
										</c:if>
										<c:choose>
											<c:when test="${rental.rentableStartDate eq '년월일'}">
												<td>대여일이 정해지지 않았습니다.</td>
											</c:when>
											<c:otherwise>
												<td>${rental.rentableStartDate }~${rental.rentableEndDate }</td>
											</c:otherwise>
										</c:choose>
									</tr>
									</c:forEach>
								</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>












		
	</div>
</div>



</body>
</html>