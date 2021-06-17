<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="goodsModify.gd" method="post" name="frm">
<input type="hidden" name="prodNum" value="${dto.prodNum }">
<table border=1 align="center">
	<tr><th>상품번호</th>
	<td>${dto.ctgr }-${dto.prodNum }</td></tr>
	<tr><th>상품명</th>
		<td><input type="text" name="prodName" value="${dto.prodName }"/></td></tr>
	<tr><th>가격</th>
		<td><input type="text" name="prodPrice" value="${dto.prodPrice }"/></td></tr>
	<tr><th>용량</th>
		<td><input type="text" name="prodCapacity" value="${dto.prodCapacity }"/></td></tr>
	<tr><th>공급처</th>
		<td><input type="text" name="prodSupplyer" value="${dto.prodSupplyer }"/></td></tr>
	<tr><th>배송비</th>
		<td><input type="number" name="prodDelFee" min="0" step="1" value="${dto.prodDelFee }"/></td></tr>
	<tr><th>추천여부</th>
		<td><input type="radio" name="recommend" value="Y" <c:if test="${dto.recommend.trim() == 'Y' }">checked</c:if> />예
			
			<input type="radio" name="recommend" value="N" <c:if test="${dto.recommend.trim() == 'N' }">checked</c:if>/>아니오
		</td></tr>
	<tr><th>내용</th>
		<td><textarea rows="6" cols="50" name="prodDetail">${dto.prodDetail }</textarea></td></tr>
	<tr><th>파일</th>
		<td><input type="file" name="prodImage1" /><br />
			<input type="file" name="prodImage2" /><br />
			<input type="file" name="prodImage3" />
		</td></tr>
	<tr><th colspan="2">
		<input type="submit" value="수정"/>
		<input type="button" value="삭제" />
		<input type="button" value="취소" onclick="javascript:history.back();"/>
		</th></tr>
</table>
</form>
</body>
</html>