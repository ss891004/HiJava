<!DOCTYPE html>
<!--次文件的保存格式是GBK-->
<%@ page contentType="text/html; charset=GBK"%>
<html lang="en">
<head>
    <title><%out.print("JSP的中文处理");%></title>
</head>
<body>
    <form action="/login" method="post">
        用户名:<input type="text" name="username" /><br/>
        密码:<input type="password" name="password"><br/>
        <input type="submit" value="登录" />
    </form>
</body>
</html>