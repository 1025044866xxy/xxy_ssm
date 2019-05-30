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

<div class="row" style="width: 1210px; margin: 0 auto;">
    <div class="col-md-12">
        <ol class="breadcrumb">
            <li><a href="<%=basePath%>backtohome">首页</a></li>
        </ol>
    </div>
    <%--${productList.size()}--%>
    <c:forEach items="${productList}" var="item">
    <div class="col-md-2" style="height:250px">
        <a href="<%=basePath%>good?id=${item.id}"> <img src="${item.goodsModel}"
                                         width="170" height="170" style="display: inline-block;">
        </a>
        <p>
            <a href="<%=basePath%>good?id=${item.id}" style='color: green'>${item.goodsName}</a>
        </p>
        <p>
            <font color="#FF0000">商城价：¥${item.goodsPrice}</font>
        </p>
    </div>
    </c:forEach>
</div>

</body>
</html>

