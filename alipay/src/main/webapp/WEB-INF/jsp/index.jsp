<%--
  Created by IntelliJ IDEA.
  User: zxl
  Date: 2020/3/16
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>所有商品</title>
    <script src="${pageContext.request.contextPath}/dist/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/dist/js/bootstrap.js"></script>
</head>
<body>
    <h2>商品列表</h2>
    <table class="table table-striped pro-list">
        <tr>
            <th>商品ID</th>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>操作</th>
        </tr>

    </table>


<script>
    product();
    function product() {
        console.log("运行了")
        $.ajax({
            type:"get",
            url:"/products",
            dataType:"json",
            success:function (jsonResult) {
                var result = typeof jsonResult=="string"?JSON.parse(jsonResult):jsonResult;
                if (result.status==1){
                    for (var i = 0; i < result.data.length; i++) {
                        var product = result.data[i];
                        var productHtml = "<tr>\n" +
                            "            <td>"+product.productId+"</td>\n" +
                            "            <td>"+product.productName+"</td>\n" +
                            "            <td>"+product.productPrice+"</td>\n" +
                            "            <td><a href='/addToCart/"+product.productId+"'>购买</a> </td>\n" +
                            "        </tr>";
                        $(".pro-list").append(productHtml);
                    }
                }
            }
        })
    }
</script>
</body>
</html>
