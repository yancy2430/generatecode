# ${name}模块接口文档
#用户添加
### 简要描述：
- 用户添加接口

#### 接口版本：
|版本号|制定人|制定日期|修订日期|
|:----    |:---|:----- |-----   |
|1.0.0 |杨哲  |2019-06-09 |  xxxx-xx-xx |
#### 请求URL:
- /${name}/${className}/register

#### 请求方式：
- POST

#### 请求头：

|参数名|是否必须|类型|说明|
|:----    |:---|:----- |-----   |
|Content-Type |是  |string |请求类型： application/json   |

#### 请求参数:
|参数名|类型|说明|
|:----    |:---|:----- |-----   |
<#list attrs as attr>
<#if attr.explain!="0">
|${attr.propertiesName} |${attr.javaTypeName} |${attr.explain}   |
</#if>
</#list>

#### 返回示例:
**正确时返回:**

```
{
    "code": 0,
    "msg": "success",
    "data": ${className}
}
```
**${className}:**

|字段名|类型|说明|
|:----    |:----- |-----   |
<#list attrs as attr>
|${attr.propertiesName} |${attr.javaTypeName} |${attr.remarks}   |
</#list>

**错误时返回:**
```
{
"code": 500,
"msg": "error",
"data": null
}
```
#### 返回参数说明:

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |结果代码，0：成功；500：程序异常  |
|msg |string   |结果说明，success：成功；其他：失败原因  |
|data |object   |结果说明，结果对象：成功；null：失败  |

#### 备注:

- 更多返回错误代码请看首页的错误代码描述