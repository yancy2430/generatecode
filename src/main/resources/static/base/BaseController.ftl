package ${packageName}.web.base;

import com.github.pagehelper.PageInfo;
import ${basePackageName}.entity.${name}.${className};
import ${packageName}.service.${className}Service;
import com.tdeado.utils.bean.Result;
import com.tdeado.utils.reflect.ParamUtil;
import com.tdeado.utils.string.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class Base${className}Controller{

    @Autowired
    protected ${className}Service service;

    @RequestMapping(value = "add",name="添加", method = {RequestMethod.POST})
    protected Result add(@RequestBody ${className} obj) {
        return Result.ok(service.add${className}(obj));
    }

    @RequestMapping(value = "edit",name="编辑", method = {RequestMethod.POST})
    protected Result edit(@RequestBody ${className} obj) {

        return Result.ok(service.edit${className}(obj));
    }
    @RequestMapping(value = "list",name="查询列表", method = {RequestMethod.POST})
    protected Result list(@RequestBody Map<String, String> obj) {

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
        return Result.ok(list);
    }

    @RequestMapping(value = "item",name="查看内容", method = {RequestMethod.POST})
    protected Result item(@RequestBody ${className} obj) {
        return Result.ok(service.get${className}(obj));
    }

    @RequestMapping(value = "dels",name="批量删除", method = {RequestMethod.POST})
    protected Result dels(@RequestBody int[] obj) {
        int b = 0;
        for (int i : obj) {
            if (service.del${className}(i)){
                b++;
            }
        }
        return Result.ok("成功"+b+"条,失败"+(obj.length-b));
    }
}
