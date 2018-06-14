<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/13 0013
  Time: 下午 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登录表单演示</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
</head>

<body>
<h2>登录</h2>

<form action="<%=request.getContextPath()%>/test/login" method="post">
    <div class="imgcontainer">
        <img src="<%=request.getContextPath()%>/img/img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label><b>用户名</b></label>
        <input type="text" placeholder="请填写用户名" name="userName" required>

        <label><b>密码</b></label>
        <input type="password" placeholder="请输入密码" name="passWord" required>

        <button type="submit">登录</button>
        <input type="checkbox" checked="checked"> Remember me
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">忘记<a href="#">密码？</a></span>
    </div>
</form>
</body>
</html>
