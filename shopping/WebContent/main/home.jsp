<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 로그인 안 되었을 때 -->
<c:if test="${empty authInfo }">
<form action="login.sj" method="post" name="frm">
<table border = 1>
	<tr><td colspan="3">아이디 저장 | 자동로그인</td></tr>
	<tr><td>아이디</td>
		<td><input type="text" name="userId" /><span>${userFail }</span></td>
		<td rowspan="2">
			<input width="50" type="image" src="images/img123.JPG" alt="login" />
		</td></tr>
	<tr><td>비밀번호</td>
		<td><input type="password" name="userPw"/><span>${pwFail }</span></td></tr>
	<tr><td colspan="3">
		<a href="#">아이디</a>/<a href="#">비밀번호 찾기</a> | 
		<a href="memAgree.mem">회원가입</a>
	</td></tr>
</table>
</form>
</c:if>
<!-- 로그인  되었을 때 -->
	<!-- 일반 회원 -->
<c:if test="${authInfo.grade==1 }">
	<a href="goodsCartList.gd">장바구니</a>
	<a href="orderConfirm.gd">주문확인</a>
	<a href="myPage.mem">mypage</a>
	<a href="logout.sj">로그아웃</a>
</c:if>
	<!-- 직원 -->

	<!-- 관리자 -->
<c:if test="${authInfo.grade!=1 }">


<c:if test="${!empty authInfo }">
	<a href="empMyPage.em">mypage</a>
	<a href="goodsList.gd">상품 등록</a>
	<a href="empList.em">직원 리스트</a>
	<a href="memList.mem">회원 리스트</a>
	<a href="logout.sj">로그아웃</a>
</c:if>
</c:if>
<hr />
<!-- 상품리스트 -->
<script>
	function goodsBuy(prodNum){
		if(${authInfo == null}){
			alert("로그인이 필요합니다."); return false;
		}else{
			location.href='prodInfo.gd?prodNum='+prodNum;
		}
	}
</script>
	<table align="center">
		<tr>
		<c:forEach items="${list }" var="dto" varStatus="cnt">
			<td><a href="javascript:goodsBuy('${dto.prodNum }')">
			<img width="300" height="200" src="goods/upload/${dto.prodImage.split(',')[0] }"><br />
			이름 : ${dto.prodName }<br />
			가격 : <fmt:formatNumber value="${dto.prodPrice }" type="currency" /></a></td>	
			<c:if test="${cnt.count % 3 == 0 }">
			</tr>
			<tr>
			</c:if>		
		</c:forEach>
		</tr>
	</table>
</body>
</html>