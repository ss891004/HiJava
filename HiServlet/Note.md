## C/S 和 B/S

## Tomcat服务器
+ tomcat 目录结构
+ web.xml , server.xml , context.xml

## IDEA创建web项目

### 新建一个web项目
+ 项目结构
```
webapp
-WEB-INF
--classes
--lib
--web.xml
```
+ 部署在tomcat 中

![1](./img/1.png )
+ 注意context path 和 部署目录
```
Tomcat Server：tomcat的路径
Deployment：webapp所在的路径
Contex Path：上下文路径。会自己识别出来，一般我们不改这个。
Server Port：默认是8080，可以改成其它
VM options: 可选的。没有参数就不填
```

## HTTP协议

## Servlet概念

### 示例
+ 包
+ 编写
+ 部署
+ 配置

### Servlet接口
```
在ServletAPI中最重要的是Servlet接口，所有Servlet都会直接或间接的与该接口发生联系，或是直接实现该接口，或间接继承自实现了该接口的类。
该接口包括以下五个方法：

init(ServletConfig config)
ServletConfig getServletConfig()
service(ServletRequest req,ServletResponse res)
String getServletInfo()
destroy()

处理方式：

（1）第一次访问Servlet时，服务器会创建Servlet对象，并调用init方法，再调用service方法
（2）第二次再访问时，Servlet对象已经存在，不再创建,也不再初始化，执行service方法
（3）当服务器停止，会释放Servlet，调用destroy方法。
```

### GenericServlet抽象类
```
GenericServlet 使编写 servlet 变得更容易。它提供生命周期方法 init 和 destroy 的简单实现，要编写一般的 servlet，只需重写抽象 service 方法即可。 
```

### HttpServlet类
```
是继承GenericServlet的基础上进一步的扩展。
提供将要被子类化以创建适用于 Web 站点的 HTTP servlet 的抽象类。HttpServlet 的子类至少必须重写一个方法，该方法通常是以下这些方法之一： 
doGet，如果 servlet 支持 HTTP GET 请求 
doPost，用于 HTTP POST 请求 
doPut，用于 HTTP PUT 请求 
doDelete，用于 HTTP DELETE 请求 
init 和 destroy，用于管理 servlet 的生命周期内保存的 资源 
getServletInfo，servlet 使用它提供有关其自身的信息 
```

### 两种配置方式
+ web.xml
+ 注解


## Servlet应用
+ request对象
    + 登录注册功能
+ response对象

+ JSP页面乱码问题
``` 
<%@ page language="java" pageEncoding="gb2312"%>
<%@ page contentType="text/html;charset=iso8859-1"%>
<html>
<head>
<title>JSP的中文处理</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">
</head>
<body>
<%out.print("JSP的中文处理");%>
</body>
</html>

第一处<%@ page language="java" pageEncoding="gb2312"%>的编码格式为jsp文件的存储格式。IDE会根据这个编码格式保存文件。并编译jsp文件，包括里面的汉字。
第二处编码为解码格式。因为存为gb2312的文件被解码为iso8859-1，这样如有中文肯定出乱码。也就是必须一致。而第二处所在的这一行，可以没有。缺省也是使用iso8859-1的编码格式。所以如果没有这一行的话，也会出现乱码。必须一致才可以。
第三处编码为控制浏览器的解码方式。如果前面的解码都一致并且无误的话，这个编码格式用不用设置都可以。有的网页出现乱码，就是因为浏览器不能确定使用哪种编码格式。因为页面有时候会嵌入页面，导致浏览器混淆了编码格式出现了乱码。
```
+ jsp中form表单传值


+ jsp页面编译流程


## 转发和重定向
### 转发
+ req.getRequestDispatcher("/login.jsp").forward(req,resp);
+ 转发的作用在服务器端,将请求发送给服务器上的其他资源,地址栏不发生变化,以共同完成一次请求的参数.
+ 转发表示一次请求,在服务器内部跳转,可以共享同一次request作用域中的数据.
    + 存数据: request.setAttribute(key, value)
    + 取数据: request.getAttribute(key)
    
### 重定向
+  resp.sendRedirect("/login.jsp");
+ 重定向作用在客户端,客户端讲请求发送给服务器后,服务器响应给客户端一个新的请求地址,客户端重新发送请求.
+ 重定向时,地址栏改变,代表客户端重新发送的请求,  属于两次请求.
    + 传递参数: 通过在URI中拼接传递 ("/login?username=xx&password=yy")
    + 获取参数:  req.getParameter("username");
+ 重定向是,两次跳转之间的request范围的信息丢失,重定向可以执行任意资源.

## Servlet生命周期
+ 实例化 -> 初始化 -> 处理请求响应 -> 销毁

## 线程安全问题

## ServletContext 


## 状态管理
### Cookie
### Session

## 过滤器


## Java Web 项目打包

+ 单模块的maven项目
![a](./img/2.png)

+ 编译
![b](./img/3.png)
  
+ 查看生成war
![c](./img/4.png)
