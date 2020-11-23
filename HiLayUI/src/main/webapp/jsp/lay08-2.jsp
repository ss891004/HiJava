<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>时间框和文件上传</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
</head>
<body>
<form class="layui-form layui-form-pane" action="" method="post">
    <!-- layui-form-item 一个输入项-->
    <div class="layui-form-item">
        <label class="layui-form-label">生日</label>
        <!-- layui-input-block 输入框会占满除文字外的整行 -->
        <div class="layui-input-block">
            <input readonly id="birth" type="text" name="birth" placeholder="请选择生日日期" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="button" class="layui-btn" id="test1">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
        </div>
    </div>
</form>
<script>
    layui.use(["laydate","form","upload","layer"],function(){
        var laydate = layui.laydate;
        var upload = layui.upload;
        var layer  = layui.layer;
        //执行一个laydate实例
        laydate.render({
            elem: '#birth', //指定元素
            format:'yyyy/MM/dd',
            value:'2012/12/12' //默认值
            // value:new Date() //默认值
        });


        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/data.jsp' //上传接口
            //,accept:'images' // file代表所有文件，默认是images代表图片
            ,size:100 // 文件最大100kb
            ,done: function(res){
                //上传完毕回调
                layer.msg("ok");
            }
            ,error: function(){
                //请求异常回调
                layer.msg("error");
            }
        });
    });
</script>

</body>
</html>
