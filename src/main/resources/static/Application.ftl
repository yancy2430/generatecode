package ${packageName};

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "${basePackageName}")
@EnableEurekaClient
@Slf4j
@MapperScan({"${packageName}.dao","${packageName}.dao.base"})
public class ${AppName}Application {

    public static void main(String[] args) {
        SpringApplication.run(${AppName}Application.class, args);
    }

}

