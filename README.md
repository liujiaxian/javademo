#javademo

### 关于环境配置
> 请自行配置jdk tomcat mysql等环境，并安装idea

### 项目搭建

#### 第一步 新建maven项目
![](https://www.pqpqpq.cn/uploads/richtext/2/20170428095030.png)

#### 起个项目名称

![](https://www.pqpqpq.cn/uploads/richtext/2/20170428095038.png)

#### 项目放置目录

![](https://www.pqpqpq.cn/uploads/richtext/2/20170428095046.png)

#### 添加springmvc支持

![](https://www.pqpqpq.cn/uploads/richtext/2/20170428095053.png)

### 配置文件的配置

#### pom.xml的配置
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>crud_demo</groupId>
  <artifactId>crud_demo</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>crud_demo Maven Webapp</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <spring.version>4.1.6.RELEASE</spring.version>
    <log4j.version>1.2.17</log4j.version>
    <aspectj.version>1.6.11</aspectj.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>com.ibeetl</groupId>
      <artifactId>beetl</artifactId>
      <version>2.4.1</version>
    </dependency>

    <!-- demn使用，不一定需要，可以换成其他json工具 -->
    <dependency>
      <groupId>com.ibeetl</groupId>
      <artifactId>btjson</artifactId>
      <version>0.94</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.0</version>
      <scope>provided</scope>
    </dependency>

    <!-- Java Servlet -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>

    <!-- Spring Context Jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring Test Jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring Core Jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring Beans Jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring Web Jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring WebMvc Jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring Expression -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- Spring Context Support -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.0.5</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.0.15</version>
    </dependency>

    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>${aspectj.version}</version>
    </dependency>

    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>${aspectj.version}</version>
    </dependency>

    <dependency>
      <groupId>com.google.zxing</groupId>
      <artifactId>core</artifactId>
      <version>3.2.1</version>
    </dependency>

    <!-- Log4j -->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>${log4j.version}</version>
    </dependency>
  </dependencies>

  <build>
    <finalName>crud_demo</finalName>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>1.6</source>
          <target>1.6</target>
          <encoding>UTF8</encoding>
        </configuration>
      </plugin>

      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat8-maven-plugin</artifactId>
        <version>2.1</version>
        <configuration>
          <path>/</path>
        </configuration>
      </plugin>

    </plugins>
  </build>
</project>

```

#### web.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="
         http://java.sun.com/xml/ns/javaee
         http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>Archetype Created Web Application</display-name>

  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <listener>
    <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
  </listener>

  <filter>
    <filter-name>Character Encoding Filter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <description>指定字符编码编码</description>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <description>是否强制要求响应包编码与请求包相同，false表示如果响应包设置了自己编码则不强制修改</description>
      <param-name>forceEncoding</param-name>
      <param-value>false</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>Character Encoding Filter</filter-name>
    <servlet-name>dispatcher-servlet</servlet-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <servlet>
    <servlet-name>dispatcher-servlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcher-servlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
</web-app>

```

#### dispatcher-servlet.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/mvc
	http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-3.2.xsd
	http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

    <context:component-scan base-package="controller" />

    <mvc:annotation-driven />

    <mvc:view-controller path="/" view-name="redirect:/index"/>

    <mvc:default-servlet-handler />

    <mvc:resources mapping="/static/**" location="/WEB-INF/static/"/>

    <bean id="beetlConfig" class="org.beetl.ext.spring.BeetlGroupUtilConfiguration" init-method="init" />

    <bean id="viewResolver" class="org.beetl.ext.spring.BeetlSpringViewResolver">
        <property name="contentType" value="text/html;charset=UTF-8" />
        <property name="order" value="0"></property>
        <property name="suffix" value=".html"></property>
        <property name="config" ref="beetlConfig"></property>
    </bean>

    <bean id="jspViewResolver"
          class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    </bean>

    <bean id="handlerExceptionResolver" class="util" />
</beans>
```

#### applicationContext.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
	http://www.springframework.org/schema/context 
	http://www.springframework.org/schema/context/spring-context-3.2.xsd
	http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
	http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
	http://www.springframework.org/schema/tx 
	http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">


    <context:component-scan base-package="controller" />
    <context:annotation-config />
    <aop:aspectj-autoproxy />

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          destroy-method="close">
        <property name="driverClassName">
            <value>com.mysql.jdbc.Driver</value>
        </property>
        <property name="url">
            <value>jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=UTF-8
            </value>
        </property>
        <property name="username">
            <value>root</value>
        </property>
        <property name="password">
            <value>123456</value>
        </property>
        <property name="initialSize">
            <value>2</value>
        </property>
        <property name="maxActive">
            <value>2</value>
        </property>
    </bean>

    <!-- transaction manager, use JtaTransactionManager for global tx -->
    <bean id="txManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>

    <!-- enable transaction demarcation with annotations -->
    <tx:annotation-driven transaction-manager="txManager" />

</beans>
```

#### 在资源文件夹添加三个文件
![](https://www.pqpqpq.cn/uploads/richtext/2/20170428100901.png)

##### beetl.properties配置
```
RESOURCE.root=/WEB-INF/views
IMPORT_PACKAGE=util.;
WEBAPP_EXT = util.GlobalExt
ERROR_HANDLER=org.beetl.core.ReThrowConsoleErrorHandler
```

##### jdbc.properties配置
```
connection.url=${connection.url}
connection.username=${connection.username}
connection.password=${connection.password}

#druid datasource
#\u53C2\u8003 https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE
druid.initialSize=10
druid.minIdle=10
druid.maxActive=50
druid.maxWait=60000
druid.timeBetweenEvictionRunsMillis=60000
druid.minEvictableIdleTimeMillis=300000
druid.validationQuery=SELECT 'x'
druid.testWhileIdle=true
druid.testOnBorrow=false
druid.testOnReturn=false
druid.poolPreparedStatements=true
druid.maxPoolPreparedStatementPerConnectionSize=20
druid.filters=wall,stat
```

##### log4j.properties配置
```
log4j.rootLogger=info,console,file

log4j.appender.console.Threshold=ALL
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd HH\:mm s,SSS}\:%5p [%t] (%F\:%L) - %m%n

# ----------------------------------------------------------
# file
# -----
log.path = E:/logs

log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.File=${log.path}/tbeetl.log
log4j.appender.file.Append=true
log4j.appender.file.DatePattern='_'yyyy-MM-dd
#log4j.appender.file.Threshold=${log.file.loglevel}
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.conversionPattern=%5p [%d{dd-MM-yyyy HH:mm:ss}] [%t] %C.%M(%F:%L) - %m %n%n


#log4j.additivity.file = false
#log4j.appender.file.Threshold=DEBUG
#log4j.appender.file=org.apache.log4j.RollingFileAppender
#log4j.appender.file.File=${catalina.base}/logs/HMIS_logs/hmis.log
#log4j.appender.file.FILE.layout.LocationInfo=true
#log4j.appender.file.layout=org.apache.log4j.PatternLayout
#log4j.appender.file.layout.ConversionPattern=%5p [%d{dd-MM-yyyy HH:mm:ss}] [%t] %C.%M(%F:%L) - %m %n%n
## Set the append to false, should not overwrite
#log4j.appender.file.Append=true
## Set the maximum file size before rollover
#log4j.appender.file.MaxFileSize=10MB
## Set the the backup index
#log4j.appender.file.MaxBackupIndex=200
```

> log4j配置需要注意，有个日志文件目录，需要对应你的计算机的盘符，没有会报错

### 项目结构
![](https://www.pqpqpq.cn/uploads/richtext/2/20170428101352.png)

> 红色框代表新增的文件或目录，其中src->main->java,需要做如下操作才能添加java的包或类文件

![](https://www.pqpqpq.cn/uploads/richtext/2/20170428101722.png)

设置为source root即可

### 控制器代码
```java
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
@Controller
@RequestMapping("/")
public class TestController {
    @RequestMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping(value = "2",method = RequestMethod.GET)
    public ModelAndView tindex(){
        int i = 12/0;
        return new ModelAndView("index");
    }
}
```

util 错误页面代码
```java
package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MVCExceptionHandler implements HandlerExceptionResolver {  
	
	public MVCExceptionHandler(){
		int a = 1 ;
	}

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
//        Map<String, Object> model = new HashMap<String, Object>();  
//        model.put("ex", ex);  
        ex.printStackTrace();
        ModelAndView view = new ModelAndView("/common/error");
        view.addObject("ex", ex);
        return view;
    }  
}  
```

### 模板代码
header代码
```html
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/bootstrap/css/bootstrap.min.css">
<script src="${ctxPath}/static/js/jquery-2.1.4.min.js"></script>
<script src="${ctxPath}/static/bootstrap/js/bootstrap.min.js"></script>
```

footer代码
```html
<footer></footer>
```

navbar代码
```html
<header></header>
```

layout代码
```html
<!DOCTYPE html>
<html>
<head>
<%include("/common/header.html"){} %>
</head>
<body>
<%include("/common/navbar.html"){} %>
${layoutContent}
<%include("/common/footer.html"){} %>
</body>
</html>
```

error代码 访问 /2
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出错了</title>
</head>
<body>
程序错误!
<p>
${ex.message}

</body>
</html>
```

index代码
```html
<%layout("/common/layout.html",{title:"首页"}){ %>
首页
<%}%>
```

> 访问根目录即可 看到效果，这是改造的一个小的demo

[原demo下载地址](http://obgwarx9r.bkt.clouddn.com/springmvc-beetl-demo-master.zip "原demo下载地址")
