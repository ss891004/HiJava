


约定配置
Maven 提倡使用一个共同的标准目录结构，Maven 使用约定优于配置的原则，大家尽可能的遵守这样的目录结构，如下所示：

目录	目的
${basedir}	存放pom.xml和所有的子目录
${basedir}/src/main/java	项目的java源代码
${basedir}/src/main/resources	项目的资源，比如说property文件，springmvc.xml
${basedir}/src/test/java	项目的测试类，比如说Junit代码
${basedir}/src/test/resources	测试用的资源
${basedir}/src/main/webapp/WEB-INF	web应用文件目录，web项目的信息，比如存放web.xml、本地图片、jsp视图页面
${basedir}/target	打包输出目录
${basedir}/target/classes	编译输出目录
${basedir}/target/test-classes	测试编译输出目录


### maven依赖范围（scope）
依赖范围与classpath的关系如下：

依赖范围	编译源码	编译测试代码	运行测试	运行项目	示例
compile	Y	Y	Y	Y	spring-web
test	-	Y	Y	-	junit
provide	Y	Y	Y	-	servlet-api
runtime	-	Y	Y	Y	jdbc驱动
system	Y	Y	Y	-	本地的jar包


### 依赖的传递
+ 路径最近原则
+ 路径最近原则




### Maven 仓库
在 Maven 中，任何一个依赖、插件或者项目构件的输出，都可以称之为构件。

在 Maven 中，仓库是一个位置，这个位置是用来存放各种第三方构件的，所有maven项目可以共享这个仓库中的构件。

Maven 仓库能帮助我们管理构件（主要是jar包），它就是放置所有jar文件（jar、war、zip、pom等等）的地方。

+ 远程仓库又分为：中央仓库、私服、其他公共远程仓库
+ 本地仓库


### 私服
+ https://my.sonatype.com/
+ Nexus 仓库分类
  + 代理仓库
    + 代理仓库主要是让使用者通过代理仓库来间接访问外部的第三方远程仓库的，如通过代理仓库访问maven中央仓库、阿里的maven仓库等等。代理仓库会从被代理的仓库中下载构件，缓存在代理仓库中以供maven用户使用。
  + 宿主仓库
  + 仓库组
    + maven用户可以从代理仓库和宿主仓库中下载构件至本地仓库，为了方便从多个代理仓库和宿主仓库下载构件，maven提供了仓库组，仓库组中可以有多个代理仓库和宿主仓库，而maven用户只用访问一个仓库组就可以间接的访问这个组内所有的仓库，仓库组中多个仓库是有顺序的，当maven用户从仓库组下载构件时，仓库组会按顺序依次在组内的仓库中查找组件，查找到了立即返回给本地仓库，所以一般情况我们会将速度快的放在前面。

#### 从私服下载构件

#### 本地构件发布到私服


### 生命周期

### 插件
