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
<table>
<c:forEach items="${list }" var="dto"> 
<tr><td>회원이름</td><td>회원아이디</td><td>연락처</td></tr>
	<tr><td>${dto.memName }</td><td>${dto.memId }</td><td>${dto.memPhone }</td></tr>
	<tr><td>구매번호</td><td>상품명</td><td>구매일</td></tr>
	<tr><td>${dto.purchaseNum }</td><td>${dto.prodName }</td><td>${dto.purchaseDate }</td></tr>
	<tr><td>수량</td><td colspan="2">총금액</td></tr>
	<tr><td>${dto.purchaseQty }</td><td colspan="2">${dto.purchasePrice }</td></tr>
	<tr><td>구매자</td><td>받는 주소</td><td>구매자 연락처</td></tr>
	<tr><td>${dto.receiverName }</td><td>${dto.purchaseAddr }</td><td>${dto.receiverPhone }</td></tr>
	<tr><td colspan="3">&nbsp;</td></tr>
</c:forEach>
</table>
</body>
</html>