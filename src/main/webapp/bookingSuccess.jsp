<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Success</title>
<link rel="icon" type="image/x-icon" href="image/weExposeFavicon.ico">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
ul{
list-style-type: none;
}
.testLabel{
	margin:auto 0px;
}
td label table{
margin: 20px 20px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="margin:20px 28%" align="center">

<h2>訂位成功</h2>
<!--  cinema theater movie product seatList -->
<table>
<tr><td><label class="testLabel">${cinema.cinemaName }&nbsp;&nbsp;&nbsp;&nbsp;</label><td><label class="testLabel">${theater.theaterName }&nbsp;&nbsp;&nbsp;&nbsp;</label><td><label class="testLabel">${movie.movieName }&nbsp;&nbsp;&nbsp;&nbsp;</label>
<td><form action="/WeExpose/CallOfOrder" method="get"><input type="text" name="orderId" value="${orderId }" style="display:none"><button type="submit" class="btn btn-outline-secondary">取消交易</button></form>
<c:forEach items="${seatList}" var="seat">
	<tr align="right"><td><span>第${seat.seatRow }排</span><td><span>第${seat.seatColumn }列</span><td><span>小計${product.productPricing }</span>
</c:forEach>
</table>
<a href="#addedProductList"  data-toggle="collapse"><div id="toggleCollapseBtn" style="margin:15px auto;border:5px solid grey;padding :25px auto;border-radius:7px;color:#337ab7;background-color:#DCDCDC"><label style="font-size:20px">可加購商品</label></div></a>
<ul id="addedProductList"  class="collapse list-group" >
<c:forEach items="${addableProducts }" var="p">
<li class="list-group-item input-group" style="padding: auto 0px">
<label style="text-align:left;width:15%;margin:auto 0px" class="testLabel ">${p.productName }</label>
<label style="text-align:left;width:15%;margin:auto 0px" class="testLabel ">${p.productPricing }元</label>
<label style="text-align:center;width:15%" class="testLabel ">數量</label>
<input type="number" id="p_${p.productId }_n" class="input-group-addon" disabled value="0" style="width:20%;display:inline" >
<span class="input-group-addon" onclick="addProduct(${p.productId});" style="display:inline;text-align:left;width:10%;margin:auto 0px"><a class="glyphicon glyphicon-plus"></a></span>
<span class="input-group-addon" onclick="minusProduct(${p.productId})" style="display:inline;text-align:left;width:10%;margin:auto 0px"><a class="glyphicon glyphicon-minus"></a></span>
</li>
</c:forEach>
</ul>
<form action="/WeExpose/AddProduct" method="get">
<input type="text" style="display:none" name="addedProductsStr" id="addedProductStr" value="">
<input type="text" style="display:none" name="orderId" value="${orderId}">
<div class="btn-group">
<a href="/WeExpose/DisplayMovieDesp"><button class="btn btn-outline-primary">回首頁</button></a>
<a href="/WeExpose/MemberInfo"><button class="btn btn-outline-primary">會員專區</button></a>
<a ><button type="submit"class="btn btn-outline-primary" id="submitAddBtn">加購</button></a>
</div>
</form>
</body>
<script>
let addedProduct = [
		<c:forEach items="${addableProducts}" var="p" varStatus="s">
		[${p.productId} , 0 ]<c:if test="true">,</c:if>
		</c:forEach>
]
function addProduct(pId){
	// find quantity in addedProduct[]
	let idx=0;
	for( idx=0; idx< addedProduct.length ; idx++){
		if(addedProduct[idx][0] == pId){
			break;
		}
	}
	if(idx>= addedProduct.length){
		return;
	}
	addedProduct[idx][1]+= 1;
	document.getElementById('p_'+pId+'_n').value= addedProduct[idx][1];
}
function minusProduct(pId){
	let idx=0;
	for( idx=0; idx< addedProduct.length ; idx++){
		if(addedProduct[idx][0] == pId){
			break;
		}
	}
	if(idx>= addedProduct.length){
		return;
	}
	addedProduct[idx][1]-= (addedProduct[idx][1]>0)? 1:0;
	document.getElementById('p_'+pId+'_n').value= addedProduct[idx][1];
}
let collapseTog = 0;
let togDiv = document.getElementById('toggleCollapseBtn');
togDiv.addEventListener('click',()=>{
	if( collapseTog==0){		
	togDiv.style.backgroundColor= "#DCDCDC";
	togDiv.style.color="white";
	collapseTog=1;
	}
	else{
		togDiv.style.backgroundColor= "#DCDCDC";
		togDiv.style.color="#337ab7";
		collapseTog=0;
	}
});
var s = document.getElementById('addedProductStr');
document.getElementById('submitAddBtn').addEventListener('click',()=>{
	
	let cnt = 0;
	let res = '';
	for( let set of addedProduct ){
		for( let i=0; i< set[1] ; i++){
			res+= ((cnt++>0)?',': '') + set[0];
		}
	}
	console.log( res);
	s.value= res;
})
</script>
</html>