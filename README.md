# sensitive-word-admin

[sensitive-word-admin](https://github.com/houbb/sensitive-word-admin) 是和 [sensitive-word](https://github.com/houbb/sensitive-word) 配套使用的控台。

[![Build Status](https://travis-ci.com/houbb/sensitive-word-admin.svg?branch=master)](https://travis-ci.com/houbb/sensitive-word-admin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sensitive-word-admin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sensitive-word-admin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/sensitive-word-admin/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/sensitive-word-admin)

## 特性

- 基本的 CRUD

> [变更日志](https://github.com/houbb/sensitive-word-admin/blob/master/CHANGELOG.md)

# 启动

## 数据库脚本

mysql 执行数据库脚本：

> [mysql-5.7.sql](https://github.com/houbb/sensitive-word-admin/blob/master/sensitive-word-admin-dal/src/main/resources/sql/mysql-5.7.sql)

测试版本为 mysql5.7，理论 8.0 也支持。

## 编译

```bash
mvn clean install -DskipTests=true
```

建议 jdk 1.8

## 启动

运行 [Application#main()](https://github.com/houbb/sensitive-word-admin/blob/master/sensitive-word-admin-web/src/main/java/com/github/houbb/sensitive/word/admin/Application.java)

启动成功后，访问：

> [http://localhost:8080/](http://localhost:8080/)

![登录首页](sensitive-word-admin-index.png)

## 操作

功能管理进行初步操作，其他待后续完善。

# API

ApiSensitiveWordController 中包含对应的 api 方法，后续可以添加验签等校验。

## 接口列表

| api | 入参 | 出参 | 说明 |
|:----|:----|:----|:------|
| /api/sensitiveWord/contains | string | boolean | 是否包含敏感词 |
| /api/sensitiveWord/findAll | string | `List<String>` | 获取所有的敏感词 |
| /api/sensitiveWord/findFist | string | string | 获取第一个的敏感词 |
| /api/sensitiveWord/replace | string | string | 获取替换后的结果 |
| /api/sensitiveWord/tags | string | `Set<String>` | 获取敏感词的标签列表 |

# Road-Map

- [ ] 登录/登出
- [ ] 页面操作的权限管理
- [ ] 调用方系统 token 注册管理
- [ ] 调用 API
- [ ] 敏感词的数据大盘
- [ ] 调用信息数据大盘
- [ ] 操作审计日志

# 开源矩阵

[sensitive-word 基于 DFA 算法实现的高性能敏感词工具](https://github.com/houbb/sensitive-word)

[sensitive 基于注解的 java 日志脱敏工具框架，更加优雅的日志打印](https://github.com/houbb/sensitive)