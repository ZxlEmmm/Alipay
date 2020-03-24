<%--
  Created by IntelliJ IDEA.
  User: zxl
  Date: 2020/3/16
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>确定订单</title>
</head>
<body>
<form action="/alipay" method="post">
    订单ID：${orderDetail.orders.orderId}<br>
    商品ID：${orderDetail.pro.productId}<br>
    商品名称：${orderDetail.pro.productName}<br>
    商品数量：${orderDetail.detail.productNum}<br>
    订单总额：${orderDetail.orders.orderAmmount}<br>
    <input type="hidden" value="${orderDetail.orders.orderId}" name="orderId">
    <input type="submit" value="去付款">
</form>


</body>
</html>
