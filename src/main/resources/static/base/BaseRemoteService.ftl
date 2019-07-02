package ${basePackageName}.remotecall.${name};


import ${basePackageName}.entity.${name}.${className};
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

public interface ${className}RemoteFeginService {
    @RequestMapping(value = "${className}/add", method = {RequestMethod.POST})
    boolean add${className}(@RequestBody ${className} obj);
    @RequestMapping(value = "${className}/edit", method = {RequestMethod.POST})
    boolean edit${className}(@RequestBody ${className} obj);
    @RequestMapping(value = "${className}/list", method = {RequestMethod.POST})
    PageInfo<${className}> get${className}List(int page, int size,@RequestBody ${className} obj);
    @RequestMapping(value = "${className}/list", method = {RequestMethod.POST})
    PageInfo<${className}> get${className}List(int page, int size,String orderby,@RequestBody ${className} obj);
    ${className} get${className}(${className} obj);
    @RequestMapping(value = "${className}/del${className}", method = {RequestMethod.POST})
    boolean del${className}(int id);

}
