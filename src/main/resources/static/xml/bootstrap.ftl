spring.application.name=${name}
# dev根据具体情况来修改
spring.cloud.config.profile=dev
spring.cloud.config.label=master
eureka.client.service-url.defaultZone=http://192.168.2.199:8761/eureka/
spring.cloud.config.discovery.enabled=true
spring.cloud.config.discovery.service-id=config
spring.main.allow-bean-definition-overriding=true
#以ip形式注册
eureka.instance.prefer-ip-address=true
#是否注册到服务发现
eureka.client.register-with-eureka=true
