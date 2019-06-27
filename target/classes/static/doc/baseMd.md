# USER模块接口文档
#用户添加
### 简要描述：
- 用户添加接口

#### 接口版本：
|版本号|制定人|制定日期|修订日期|
|:----    |:---|:----- |-----   |
|1.0.0 |杨哲  |2019-06-09 |  xxxx-xx-xx |
#### 请求URL:
- http://192.168.2.199:8251/user/Member/register

#### 请求方式：
- POST

#### 请求头：

|参数名|是否必须|类型|说明|
|:----    |:---|:----- |-----   |
|Content-Type |是  |string |请求类型： application/json   |

#### 请求参数:
|参数名|是否必须|类型|说明|
|:----    |:---|:----- |-----   |
|username |是  |string |用户名   |
|password |是  |string | 密码    |
|email |否  |string | 电子邮箱    |
|mobile |是  |long | 手机号    |
|groupId |是  |int | 角色ID    |
|nickname |否  |string | 昵称    |
|avatar |否  |string | 头像链接    |
|sex |否  |int | 性别    |
#### 返回示例:
**正确时返回:**

```
{
    "code": 0,
    "msg": "success",
    "data": {
        "id": 1,
        "username": "admin",
        "password": 123456,
        "email": null,
        "mobile": 18152733661,
        "seeds": null,
        "groupId": 1,
        "nickname": null,
        "lastLoginIp": null,
        "lastLoginTime": null,
        "avatar": null,
        "loginToken": null,
        "sex": null
    },
    "state": null
}
```
**错误时返回:**
```
{
    "code": 500,
    "msg": "用户已存在！",
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