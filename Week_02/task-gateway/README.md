
# 作业
#### 1、Netty网关设计
------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_02/Netty%E7%BD%91%E5%85%B3%E4%BD%9C%E4%B8%9A%E8%AE%BE%E8%AE%A1.jpg)

#### 2、作业地址
[https://github.com/hyblog/JAVA-000/tree/main/Week_02/task-gateway](https://github.com/hyblog/JAVA-000/tree/main/Week_02/task-gateway)

#### 3、TEST
```java
// 请求Gateway：http://127.0.0.1:8001/fristServlet.do
// Tomacat-1响应：
{
    "code":0,
    "msg":"ok",
    "result":{
        "data":"First Servlet!",
        "tomcatLocalAddr":"127.0.0.1:9001",
        "tomcatMethod":"GET",
        "tomcatParameters":{

        },
        "tomcatRemoteAddr":"127.0.0.1:52670",
        "tomcatUri":"/fristServlet.do"
    }
}
// Tomacat-2响应：
{
    "code":0,
    "msg":"ok",
    "result":{
        "data":"First Servlet!",
        "tomcatLocalAddr":"127.0.0.1:9002",
        "tomcatMethod":"GET",
        "tomcatParameters":{

        },
        "tomcatRemoteAddr":"127.0.0.1:52918",
        "tomcatUri":"/fristServlet.do"
    }
}
// 控制台输出
Tomcat：启动成功，端口：9002
Tomcat：启动成功，端口：9001
Gateway：启动成功，端口：8001
http://127.0.0.1:9001/fristServlet.do
http://127.0.0.1:9002/fristServlet.do
```

