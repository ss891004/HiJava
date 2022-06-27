# HiSwagger2
# HiJava
```
echo "# HiJava" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/ss891004/HiJava.git
git push -u origin main
```

```text
原因：
pom.xml文件被设置在maven忽略文件清单中

解决方法：
file -->setting–>maven–>Ignored Files 将清单中对应项目的pom.xml文件取消选中即可；
如果取消选中之后，在idea的工作区还是显示pom.xml文件中间有一条横线，那么此时需要重新加载项目或重启idea即可恢复正常，如下图所示：

```

### Spring
```text
01、Spring是什么
02、控制反转（IoC）与依赖注入（DI）
03、Spring容器基本使用及原理
04、xml中bean定义详解
05、容器创建bean实例有多少种？
06、bean作用域scope详解
07、依赖注入之手动注入
08、依赖注入之自动注入（autowire）详解
09、depend-on干什么的？
10、primary可以解决什么问题？
11、bean中的autowire-candidate属性又是干什么的？
12、lazy-init：bean延迟初始化
13、使用继承简化bean配置(abstract & parent)
14、lookup-method和replaced-method比较陌生，怎么玩的？
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
16、深入理解java注解（预备知识）
17、@Configration、@Bean注解详解
18、@ComponentScan、@ComponentScans详解
19、@Import 注解详解
20、@Conditional通过条件来控制bean的注册
21、注解实现依赖注入（@Autowired、@Resource、@Primary、@Qulifier）
22、@Scope、@DependsOn、@ImportResource、@Lazy
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
23、Bean生命周期详解
24、父子容器
25、@PropertySource、@Value注解及动态刷新实现
26、国际化详解
27、事件详解
28、循环bean详解
29、BeanFactory扩展（BeanFactoryPostProcessor、BeanDefinitionRegistryPostProcessor）
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
15、代理详解（java动态代理&CGLIB代理)
30、jdk动态代理和cglib代理
31、Aop概念详解
32、AOP核心源码、原理详解
33、ProxyFactoryBean创建AOP代理
34、@Aspect中@Pointcut 12种用法
35、@Aspect中5中通知详解
36、@EnableAspectJAutoProxy、@Aspect中通知顺序详解
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
37、@EnableAsync & @Async 实现方法异步调用
38、@Scheduled & @EnableScheduling定时器详解
39、强大的Spel表达式
40、缓存使用（@EnableCaching、@Cacheable、@CachePut、@CacheEvict、@Caching、@CacheConfig）
41、@EnableCaching集成redis缓存
42、JdbcTemplate实现增删改查如此简单？
43、Spring中编程式事务怎么用的？
44、Spring声明事务怎么用的？
45、详解Spring事务中7种传播行为
46、Spring如何管理多数据源事务？
47、Spring事务源码解析
48、@Transaction 事务源码解析
49、实战篇：手把手带你实现事务消息！
50、Spring事务拦截器顺序如何控制？
51、Spring事务失效常见的几种情况
52、Spring实现数据库读写分离
53、Spring集成MyBatis
54、集成junit
55、Spring上下文生命周期
56、面试官：循环依赖不用三级缓存可以么？
```