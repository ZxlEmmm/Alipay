<%--
  Created by IntelliJ IDEA.
  User: zxl
  Date: 2020/3/16
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<form action="/order" method="post">
    商品ID：${product.productId}<br>
    商品名称：${product.productName}<br>
    商品价格：${product.productPrice}<br>
    <input type="hidden" value="${product.productId}" name="proId">
    <input type="text" name="proNum"><br>
    <input type="submit" value="提交订单">
</form>

</body>
</html>
