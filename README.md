# ssm
ssm示例

## applicationContext.xml和dispatcher-servlet.xml区别

### 用途
applicationContext.xml文件通常用于加载spring系统级别的组件，比如bean的初始化。
dispatcher-servlet.xml文件通常用于加载controller层需要的类，比如拦截器,mvc标签加载的类。

### 加载位置不同
applicationContext.xml加载在标签中，作为FrameworkServlet的参数属性。
dispatcher-servlet.xml文件当做DispatcherServlet的参数属性进行加载。

applicationContext.xml是全局的，应用于多个servelet，配合listener一起使用。
```xml
<!-- 配置监听器 -->
<listener>        
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>
```
dispatcher-servlet.xml是SpringMVC的配置
