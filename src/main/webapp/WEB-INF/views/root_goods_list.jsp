<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.xxy.model.Goods" %>
<%@ page import="java.util.List" %>
<%
    /*获取工程路径*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<%=basePath%>to_add">添加</a>
${name}
${id}
<div style="text-align: center;">
<table border="1" style="margin: auto" width='60%'>
    <tr>
        <td>id</td>
        <td>商品名：</td>
        <td>库存：</td>
        <td>价格：</td>
        <td>模型</td>
    </tr>
    ${productList.size()}a
    <c:forEach items="${productList}"  var="item">
        <tr>
            <td>${item.id}</td>
            <td><a href="<%=basePath%>to_update_goodsname?id=${item.id}">${item.goodsName}</a></td>
            <td><a href="<%=basePath%>to_update_goodsnum?id=${item.id}">${item.goodsNum}</a></td>
            <td><a href="<%=basePath%>to_update_goodsprice?id=${item.id}">${item.goodsPrice}</a></td>
            <td><a href="<%=basePath%>to_update_goodsmodel?id=${item.id}"><img src="${item.goodsModel}"
                                                                               width="100" height="100" style="display: inline-block;"></a></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
