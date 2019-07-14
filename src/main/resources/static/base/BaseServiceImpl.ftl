package ${packageName}.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${packageName}.dao.${className}Mapper;
import ${basePackageName}.${name}.entity.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import java.util.List;

public abstract class Base${className}ServiceImpl implements ${className}Service {
    @Autowired
    protected ${className}Mapper mapper;
    @CacheEvict(value = {"${className}List"},allEntries = true,beforeInvocation = true)
    @Override
    public boolean add${className}(${className} obj) {
        if (mapper.insert${className}(obj)>0){
            return true;
        }else{
            return false;
        }
    }
    @Caching(evict = {@CacheEvict(value = {"${className}List"},allEntries = true,beforeInvocation = true),@CacheEvict(value = {"${className}"},key = "#obj.id",beforeInvocation = true)})
    @Override
    public boolean edit${className}(${className} obj) {
        if (mapper.update${className}(obj)>0){
            return true;
        }else{
            return false;
        }
    }
    @Cacheable(value = {"${className}"},key = "#obj.id")
    @Override
    public ${className} get${className}(${className} obj) {
        return mapper.query${className}Limit1(obj);
    }
    @Cacheable(value = {"${className}List"},key = "'list-'+#page+'-'+#size+'-'+#obj.hash()")
    @Override
    public PageInfo<${className}> get${className}List(int page, int size, ${className} obj) {
        PageHelper.startPage(page, size);
        List<${className}> list = mapper.query${className}(obj);
        PageInfo<${className}> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Cacheable(value = {"${className}List"},key = "'list-'+#page+'-'+#size+'-'+#orderby+'-'+#obj.hash()")
    @Override
    public PageInfo<${className}> get${className}List(int page, int size,String orderby, ${className} obj) {
        PageHelper.startPage(page, size,orderby);
        List<${className}> list = mapper.query${className}(obj);
        PageInfo<${className}> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Caching(evict = {@CacheEvict(value = {"${className}List"},allEntries = true,beforeInvocation = true),@CacheEvict(value = {"${className}"},key = "#id",beforeInvocation = true)})
    @Override
    public boolean del${className}(int id) {
        if (mapper.del${className}(id)>0){
            return true;
        }
        return false;
    }
}

