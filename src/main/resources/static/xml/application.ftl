server.port=8989

spring.datasource.url=jdbc:mysql://192.168.2.199:3306/bingo_user?useUnicode=true&characterEncoding=UTF-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=bw5223591
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

mybatis.type-aliases-package=${packageName}.entity
mybatis.mapper-locations=classpath:mapper/**/**.xml
mybatis.configuration.use-generated-keys=true
mybatis.configuration.use-column-label=true
mybatis.configuration.map-underscore-to-camel-case=false


