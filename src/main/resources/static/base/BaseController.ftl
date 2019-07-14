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

import java.util.Map;

public class ${className}Controller{

    @Autowired
    protected ${className}Service service;

    @RequestMapping(value = "add",name="添加", method = {RequestMethod.POST})
    public boolean add(@RequestBody ${className} obj) {
        return service.add${className}(obj);
    }

    @RequestMapping(value = "edit",name="编辑", method = {RequestMethod.POST})
    public boolean edit(@RequestBody ${className} obj) {

        return service.edit${className}(obj);
    }
    @RequestMapping(value = "list",name="查询列表", method = {RequestMethod.POST})
    public PageInfo<${className}> list(@RequestBody Map<String, String> obj) {

        String field = obj.get("field");
        String order = obj.get("order");
        String tree = obj.get("tree");
        String orderby = "";
        if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(order)) {
            orderby = StringUtil.camelToUnderline(field) +" "+ order;
        }
        int page = Integer.parseInt(obj.get("page"));
        int size = Integer.parseInt(obj.get("size"));
        obj.remove("page");
        obj.remove("size");
        obj.remove("field");
        obj.remove("order");
        obj.remove("tree");
        ${className}.QueryBuilder ben = ${className}.QueryBuilder.QueryBuild();
        ParamUtil<${className}.QueryBuilder> builderParamUtils  = new ParamUtil<>();
        PageInfo<${className}> list = service.get${className}List(page,size,orderby, builderParamUtils.init(obj,ben));
        return list;
    }

    @RequestMapping(value = "item",name="查看内容", method = {RequestMethod.POST})
    public ${className} item(@RequestBody ${className} obj) {
        return service.get${className}(obj);
    }

}
