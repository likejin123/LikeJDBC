# JDBC快速入门

# 文档目录
```java
.
|-- java
|   `-- com
|       `-- likejin
|           `-- jdbc
|               |-- entity
|               |   `-- Book.java   '封装数据库库返回数据的实体类'
|               |-- getConnAndCRUD
|               |   |-- JDBCUtil.java   '通用的查询操作'
|               |   |-- MyConnection.java   '三种连接方式（驱动器，驱动管理器，数据库连接池）'
|               |   `-- TestCRUD.java   '测试sql注入问题'
|               `-- socket
|                   |-- Client.java      '利用socket模拟mysql客户端'
|                   `-- Server.java      '利用socket模拟mysql服务器端'
`-- resources
    `-- application.properties    '数据库连接的驱动，url，用户名，密码'
```
1.用了socket连接来模拟mysql客户端和mysql数据库 来校验信息 发送sql

2.对sql注入问题进行了测试(prepareStatement,Statement)

3.三种不同方式获取数据库连接。
通用查询方法利用preparestatement
