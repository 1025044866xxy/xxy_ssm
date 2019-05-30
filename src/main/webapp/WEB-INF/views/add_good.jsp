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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=basePath%>add_goods" method="post" enctype="multipart/form-data">
    <input name="goodsName" type="text" value="">商品名称
    <input name="goodsNum" type="text" value="">商品库存
    <input name="goodsPrice" type="text" value="">商品单价
    <input name="file" type="file">选择文件
    <input type="submit" value="提交">
</form>
</body>
</html>
