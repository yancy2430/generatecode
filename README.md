##根据MySQL生成 dao等代码的maven插件
* 添加插件源
```xml
<pluginRepositories>
        <pluginRepository>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
            <id>releases</id>
            <name>tdeado</name>
            <url>http://nexus.tdeado.com/repository/maven-releases/</url>
        </pluginRepository>
</pluginRepositories>
```
* 添加到插件
```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>com.tdeado</groupId>
                <artifactId>generate-code</artifactId>
                <version>2.5</version>
                <configuration>
                    <host>mysqlhost</host>
                    <port>mysqlprot</port>
                    <user>mysqlroot</user>
                    <pass>mysqlpass</pass>
                    <dbname>mysqldb</dbname>
                    <prefix>tableprefix</prefix>
                </configuration>
            </plugin>
        </plugins>
</build>
```