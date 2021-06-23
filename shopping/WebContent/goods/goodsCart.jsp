<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script type="text/javascript">
	function checkQty(prodNum, prodPrice, cartQty){
		if(cartQty > 1){
			location.href="goodsCartQtyDown.gd?prodNum="+prodNum+"&prodPrice="+prodPrice;
		}else{
			alert("최소 수량이 1이어야합니다.");
			return false;
		}
	}
	function prodChk(){
		var prodTot = 0;
		var chk=document.getElementsByName("prodCk");
		var hddchk = document.getElementsByName("cartPrice");
		var cnt=0;
		for(var i = 0; i<chk.length; i++){
			if(chk[i].checked==true){
				prodTot+=Number(hddchk[i].value);
				cnt++;
			}
		}
		document.getElementById("totalPrice").innerHTML=prodTot;
		document.getElementById("prodCnt").innerHTML=cnt;
	}
</script>
</head>
<body>
카트페이지입니다.
<form action="goodsBuy.gd" method="post">
<c:set var="price" value="0" /><!-- 자바 변수 생성 -->
<c:set var="cnt" value="0" />
<c:forEach items="${lists }" var="dto">
<table border=1 width=600 align="center">
	<tr><td colspan=4 width=350><input type="checkbox" value="${dto.productDTO.prodNum}" name="prodCk" onchange="prodChk();" checked />${dto.productDTO.prodSupplyer }
	<input type="hidden" name="cartPrice" value="${dto.cartDTO.cartPrice+dto.productDTO.prodDelFee }" /></td><td>배송비</td><td>합계</td>
	<td rowspan="2" align="center"><input type="button" value="삭제" onclick="javascript:location.href='cartProdDel.gd?prodNum=${dto.productDTO.prodNum}';" /></td></tr>
	<tr><td><img src="goods/upload/${dto.productDTO.prodImage.split(',')[0] }" width=50 /></td>
	<td>${dto.productDTO.prodName }</td>
	<td><a href="javascript:checkQty('${dto.productDTO.prodNum}','${dto.productDTO.prodPrice}','${dto.cartDTO.cartQty }')">-</a> 
	&nbsp;&nbsp;${dto.cartDTO.cartQty }&nbsp;&nbsp; 
	<a href="goodsCartAdd.gd?prodNum=${dto.productDTO.prodNum}&qty=1&prodPrice=${dto.productDTO.prodPrice}">+</a></td>
	<td>${dto.cartDTO.cartPrice }</td>
	<td>${dto.productDTO.prodDelFee }</td>
	<td>${dto.cartDTO.cartPrice+dto.productDTO.prodDelFee }</td></tr>
</table>
<c:set var="price" value="${dto.cartDTO.cartPrice+dto.productDTO.prodDelFee+price}" />
<c:set var="cnt" value="${cnt=cnt+1 }" />
</c:forEach>
<span id="prodCnt">${cnt }</span>개 품목 선택
총 합계 : <span id="totalPrice">${price }</span>원
<input type="submit" value="구매하기" />&nbsp;&nbsp;|&nbsp;&nbsp;<a href="main.sj">&nbsp;계속 쇼핑</a>
</form>
</body>
</html>