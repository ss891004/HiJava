## 官网
+ http://www.layui.com
+ 模块化前端UI框架

## 示例
+ 下载, 官网下载压缩包,解压其中的layui文件,全部拷贝到项目中去
```
 ├─css //css目录
  │  │─modules //模块css目录（一般如果模块相对较大，我们会单独提取，比如下面三个：）
  │  │  ├─laydate
  │  │  ├─layer
  │  │  └─layim
  │  └─layui.css //核心样式文件
  ├─font  //字体图标目录
  ├─images //图片资源目录（目前只有layim和编辑器用到的GIF表情）
  │─lay //模块核心目录
  │  └─modules //各模块组件
  │─layui.js //基础核心库
  └─layui.all.js //包含layui.js和所有模块的合并文件
```
+ 引用
```
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
</head>
```

+ 布局
+ 字体图标
+ 按钮
+ 表单
+ 数据表格
+ 导航
+ 动画
+ 内置模块
    + 弹出层 layer
    + 日期与时间选择 laydate
    + 即时通讯 layim
    + 分页 laypage
    + 模板引擎 laytpl
    + 数据表格 table
    + 表单 form
    + 文件上传 upload
    + 穿梭框 transfer
    + 树形组件 tree
    + 颜色选择器 colorpicker
    + 常用元素操作 element
    + 滑块 slider
    + 评分 rate
    + 轮播 carousel
    + 流加载 flow
    + 工具集 util
    + 代码修饰器 code
```
关于模块的核心，就是 layui.js 的两个底层方法：一个用于定义模块的 layui.define()，一个加载模块的 layui.use()。
```

## 官方示例
+ https://www.layui.com/demo/


## 什么是跨域
+ 跨域指的是浏览器不能执行其他网站的脚本.它是由浏览器的同源策略造成的,是浏览器对javascript施加的安全限制.目前所有的浏览器都实行同源策略,所谓的同源指的是
    + 协议相同
    + 域名相同
    + 端口相同
