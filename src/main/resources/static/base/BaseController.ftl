package ${packageName}.web;

import com.github.pagehelper.PageInfo;
import ${basePackageName}.${name}.entity.${className};
import ${packageName}.service.${className}Service;
import com.tdeado.utils.reflect.ParamUtil;
import com.tdeado.utils.string.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tdeado.utils.bean.Result;

import java.util.Map;
@RestController
@RequestMapping("Api/${className}")
public class ${className}Controller{

    @Autowired
    protected ${className}Service service;
    /**
    * 添加
    * @param obj 查询对象
    */
    @RequestMapping(value = "add",name="添加", method = {RequestMethod.POST})
    public Result add(@RequestBody ${className} obj) {
        return Result.ok(service.add${className}(obj));
    }
    /**
    * 编辑
    * @param obj 查询对象
    */
    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public Result edit(@RequestBody ${className} obj) {
        return Result.ok(service.edit${className}(obj));
    }
    /**
    * 获取数据列表
    * @param page 页数
    * @param size 页数大小
    * @param obj 查询对象
    */
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public Result list(int page,int size,@RequestBody ${className} obj) {
        PageInfo<${className}> list = service.get${className}List(page,size, obj);
        return Result.ok(list);
    }
    /**
    * 查看内容
    * @param obj 查询对象
    */
    @RequestMapping(value = "item", method = {RequestMethod.POST})
    public Result item(@RequestBody ${className} obj) {
        return Result.ok(service.get${className}(obj));
    }

}
