package ${packageName}.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ${packageName}.service.${className}Service;
import java.util.Map;
@RestController
@RequestMapping("${className}")
public class ${className}Controller{

    @Autowired
    protected ${className}Service service;
    /**
    * 接口编写
    **/
}
