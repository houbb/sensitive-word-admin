# 项目介绍

## 平台简介

[sensitive-word-admin](https://github.com/houbb/sensitive-word-admin) 是和 [sensitive-word](https://github.com/houbb/sensitive-word) 配套使用的控台。

[![Build Status](https://travis-ci.com/houbb/sensitive-word-admin.svg?branch=master)](https://travis-ci.com/houbb/sensitive-word-admin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sensitive-word-admin/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sensitive-word-admin)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/sensitive-word-admin/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/sensitive-word-admin)

* 前端采用Vue、Element UI。
* 后端采用Spring Boot、Spring Security、Redis & Jwt。
* 权限认证使用Jwt，支持多终端认证系统。
* 支持加载动态权限菜单，多方式轻松权限控制。
* 高效率开发，使用代码生成器可以一键生成前后端代码。

## 拓展阅读

> [sensitive-word-admin 敏感词控台 v1.2.0 版本开源](https://mp.weixin.qq.com/s/7wSy0PuJLTudEo9gTY5s5w)

> [sensitive-word 基于 DFA 算法实现的高性能敏感词工具介绍](https://mp.weixin.qq.com/s/OKLCWlOTv_PSi9MIfDpoMw)

## 特性

- 基本的 CRUD

- 敏感词修改实时生效

> [变更日志](https://github.com/houbb/sensitive-word-admin/blob/master/CHANGELOG.md)

# 本地启动

## 依赖

redis

mysql

node

## 后端

2）执行 mysql 脚本

登录 mysql

清空库（谨慎操作，别清空了以前的数据）

```
use test;
drop database sensitive_word;
```

创建库

```
create database sensitive_word;
use sensitive_word;
```

执行 sql 下的 `init.sql`+ `sensitive_word.sql`

3）修改配置

`application-druid.yml` 调整一下数据库链接+账户密码

```yaml
spring:
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource
        driverClassName: com.mysql.cj.jdbc.Driver
        druid:
            # 主库数据源
            master:
                url: jdbc:mysql://localhost:3306/sensitive_word?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
                username: root
                password: PASSWORD
```


运行 SpringBootApplication#main()

## 前端

```
cd admin-ui
npm install --registry=https://registry.npm.taobao.org
npm run dev
```

## 登录

打开浏览器，输入：http://localhost:80 （默认账户 admin/admin123）

## 敏感词基本管理

你可以在敏感词管理菜单，进行管理。

![敏感词菜单](sensitive-word-manager-menu.png)

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


## 接口验证

### token

![接口验证](sensitive-word-admin-api-test.png)

我们登录之后，可以获取到对应的 token。

比如我们打开【系统工具】-【系统接口】，任意找一个接口，看一下对应的 Authorization

比如这里就是 

```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImxvZ2luX3VzZXJfa2V5IjoiYzBjMmY0MWQtMjliMS00YzlkLWI2ZmMtNDhlZTk4MGIwZjJjIn0.67qpqIndfAd4J769ny1jqq8kEnATE9u5DbjJoMpbiMCSvAg2WaBV0b4nBjo5UxKVqcqbYgmnIN4gUi3AP2p6uw
```

### swagger 认证

然后点击绿色认证按钮认证一下，输入上面的 token。

就可以想基本的 swagger 一样验证接口。

### curl 请求

你可以本地直接 curl

```
curl -X GET "http://localhost:80/dev-api/api/sensitiveWord/tags?text=%E4%BA%8C%E5%8D%81%E5%85%AB%E7%94%BB%E7%94%9F" -H "accept: */*" -H "Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImxvZ2luX3VzZXJfa2V5IjoiYzBjMmY0MWQtMjliMS00YzlkLWI2ZmMtNDhlZTk4MGIwZjJjIn0.67qpqIndfAd4J769ny1jqq8kEnATE9u5DbjJoMpbiMCSvAg2WaBV0b4nBjo5UxKVqcqbYgmnIN4gUi3AP2p6uw"
```

结果：

```
{
  "respCode": "0000",
  "respMessage": "成功",
  "result": [
    "伟人",
    "画家"
  ]
}
```



# 如何自定义标签

## 自定义单词 TAG

我们在配置中指定：

```java
@Configuration
public class SensitiveWordConfig {

    @Autowired
    private MyDdWordAllow myDdWordAllow;

    @Autowired
    private MyDdWordDeny myDdWordDeny;

    /**
     * 自定义单词标签
     *
     * @since v1.4.0
     */
    @Autowired
    private MyDdWordTags myDdWordTags;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        return SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.defaults(), myDdWordAllow))
                .wordDeny(WordDenys.chains(WordDenys.defaults(), myDdWordDeny))
                .wordTag(myDdWordTags)
                // 各种其他配置
                .init();
    }

}
```

## MyDdWordTags 自定义实现

MyDdWordTags 是一个实现的例子：

核心分为两步：

1）根据【标签单词映射表】获取单词对应的标签编码(tag_code) 列表

2）根据【标签表】中的 tag_code 去查询对应的 标签描述(tag_label) 列表

所以需要分别配置二者，然后进行关联。

```sql
SELECT DISTINCT
mapping.word AS word,
tag.tag_label AS tagLabel
FROM word_tag_mapping mapping
INNER JOIN tag tag ON mapping.tag_code = tag.tag_code
WHERE tag.status = 'Y'
```

# road-map

- [ ] 单个词的修改实时生效
- [ ] word/tags 的修改，增量的变化，而不是全量
- [ ] word-tag 的处理，改为枚举映射，而不是手动输入
- [ ] 限定接入系统 appKey appSecret
- [ ] 系统的调用记录
- [ ] 数据大盘
- [ ] word 操作时对应的日志表，有必要存在吗？

# 技术鸣谢

- 感谢 ruoyi-vue 的无私奉献

# 开源矩阵

[sensitive-word 敏感词](https://github.com/houbb/sensitive-word)

[pinyin 汉字转拼音](https://github.com/houbb/pinyin)

[pinyin2hanzi 拼音转汉字](https://github.com/houbb/pinyin2hanzi)

[segment 高性能中文分词](https://github.com/houbb/segment)

[opencc4j 中文繁简体转换](https://github.com/houbb/opencc4j)

[nlp-hanzi-similar 汉字相似度](https://github.com/houbb/nlp-hanzi-similar)

[word-checker 拼写检测](https://github.com/houbb/word-checker)

