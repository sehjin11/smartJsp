<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="goodsJoin.gd" method="post" name="frm" enctype="multipart/form-data">
<table border=1 align="center">
	<tr><th>상품번호</th>
	<td><select name="ctgr">
			<option value="wear">의류</option>	
			<option value="cosmetic">화장품</option>	
			<option value="food">식품</option>	
			<option value="car">자동차용품</option>	
		</select>
		<input type="text" name="goodsNum" value="${goodsNum }" />
		</td></tr>
	<tr><th>상품명</th>
		<td><input type="text" name="prodName"/></td></tr>
	<tr><th>가격</th>
		<td><input type="text" name="prodPrice"/></td></tr>
	<tr><th>용량</th>
		<td><input type="text" name="prodCapacity"/></td></tr>
	<tr><th>공급처</th>
		<td><input type="text" name="prodSupplyer"/></td></tr>
	<tr><th>배송비</th>
		<td><input type="number" name="prodDelFee" min="0" step="1" /></td></tr>
	<tr><th>추천여부</th>
		<td><input type="radio" name="recommend" value="Y"/>예
			<input type="radio" name="recommend" value="N"/>아니오
		</td></tr>
	<tr><th>내용</th>
		<td><textarea rows="6" cols="50" name="prodDetail"></textarea></td></tr>
	<tr><th>파일</th>
		<td><input type="file" name="prodImage1" /><br />
			<input type="file" name="prodImage2" /><br />
			<input type="file" name="prodImage3" />
		</td></tr>
	<tr><th colspan="2">
		<input type="submit" value="상품등록"/>
		<input type="reset" value="초기화" />
		<input type="button" value="뒤로" onclick="javascript:history.back();"/>
		</th></tr>
</table>
</form>
</body>
</html>