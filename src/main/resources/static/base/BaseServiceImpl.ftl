package ${packageName}.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.dao.${className}Mapper;
import ${basePackageName}.entity.${name}.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class Base${className}ServiceImpl implements ${className}Service {
    @Autowired
    protected ${className}Mapper mapper;
    @Override
    public boolean add${className}(${className} obj) {
        if (mapper.insert${className}(obj)>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean edit${className}(${className} obj) {
        if (mapper.update${className}(obj)>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public ${className} get${className}(${className} obj) {
        return mapper.query${className}Limit1(obj);
    }

    @Override
    public PageInfo<${className}> get${className}List(int page, int size, ${className} obj) {
        PageHelper.startPage(page, size);
        List<${className}> list = mapper.query${className}(obj);
        PageInfo<${className}> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public PageInfo<${className}> get${className}List(int page, int size,String orderby, ${className} obj) {
        PageHelper.startPage(page, size,orderby);
        List<${className}> list = mapper.query${className}(obj);
        PageInfo<${className}> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public boolean del${className}(int id) {
        if (mapper.del${className}(id)>0){
            return true;
        }
        return false;
    }
}

