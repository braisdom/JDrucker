## JDrucker
Do what you do best, and outsource the rest. Make the Java do business logic only, make the SQL do data query and update. Don't let SQL statements get mixed up in Java code.

### XSQL
The 'xsql' file is a grammatical structure. Let SQL statements, like Java code, has methods, comments, structures, and support dynamic splicing.

`Example In IntelliJ IDEA:`

![](https://raw.githubusercontent.com/braisdom/JDrucker/master/JDrucker/images/1.png)

`Maven:`
```xml
<dependency>
    <groupId>com.github.braisdom</groupId>
    <artifactId>JDrucker</artifactId>
    <version>1.2.0</version>
</dependency>
```

`Usage:`
```java
// Loading and caching all XSQL file at system beginning. The xsqlPath is resources path of application
JDrucker.loadXsqlFile(xsqlPath);
// Retrieving the xsql declaration from cache, and formatting the sql statment
XSQLDeclaration xsqlDeclaration = JDrucker.getXSQLDeclaration(xsqlFileName)
```