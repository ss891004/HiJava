<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>动画</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
</head>
<body>
<!-- 整个div会在页面显示时，以特定动画显示出来 -->
<div class="layui-anim layui-anim-up" style="height: 100px">aa</div>
<!-- 额外添加样式类：layui-anim-loop 使得动画循环运行 -->
<div class="layui-anim layui-anim-rotate layui-anim-loop"
     style="text-align:center;line-height: 100px;margin-left:50px;height: 100px;width:100px">bb</div>
</body>
</html>
