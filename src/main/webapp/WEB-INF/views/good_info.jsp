<%@ page isELIgnored="false" %>
<%@ page import="com.xxy.model.Goods" %>
<%@ page import="java.util.List" %>
<%
    /*获取工程路径*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="col-md-2" style="height:250px">
            <a href="<%=basePath%>good?id=${good.id}"> <img src="${good.goodsModel}"
                                                               width="170" height="170" style="display: inline-block;">
            </a>
            <p>
                <a href="<%=basePath%>good?id=${good.id}" style='color: green'>${good.goodsName }</a>
            </p>
            <p>
                <font color="#FF0000">商城价：¥${good.goodsPrice}</font>
            </p>
            <p>
                <font color="#FF0000">剩余数量：¥${good.goodsNum}</font>
            </p>
            <p>
                <a href="<%=basePath%>buygood?id=${good.id}">购买</a>
            </p>
        </div>
</div>

</body>
</html>

