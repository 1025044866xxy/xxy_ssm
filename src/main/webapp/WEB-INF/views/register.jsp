<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    /*获取工程路径*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=basePath%>register" method="post">
    <div style="text-align: center;margin-top: 100px;">
        <h1>用户注册</h1>
        <input type="text" name="username" placeholder="username">
        <input type="password" name="password" placeholder="password">
        <input type="submit" value="注册">
    </div>
</form>
</body>
</html>
