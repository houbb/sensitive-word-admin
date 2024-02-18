create database sensitive_word;
use sensitive_word;

-- ------------------------------
-- ---------- MVP
-- 1. 用户管理
-- 2. 审批流程
-- 3. 操作日志
-- 4. 变更日志
-- 5. 定时生效
-- 6. 导入导出
-- 7. 敏感词规则（实时生效）
-- 9. 部署服务
-- 10. 数据大盘

-- 对外接口（GateWay）
-- 核心：提供对应的服务即可。
-- 外部应用的统一管理
-- 接口的权限控制
-- 接口的限次限流
-- ------------------------------

-- ------------ 核心表
-- tag 标签
-- word 单词
-- word_log 单词操作日志
-- 考虑后期支持敏感词级别 分类等

create table lc_enum_mapping
(
    id int unsigned auto_increment comment '自增主键' primary key,
    table_name varchar(32) not null comment '表名称',
    column_name varchar(64) not null comment '字段名称',
    `key` varchar(64) not null comment '字段编码',
    label varchar(64) not null comment '字段显示',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '枚举映射表' ENGINE=Innodb default charset=UTF8 auto_increment=1;
create unique index ix_lc_enum_mapping on lc_enum_mapping (table_name, column_name, `key`) comment '标识索引';

insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word', 'status', 'S', '正常');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word', 'status', 'F', '失效');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word', 'type', 'ALLOW', '允许');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word', 'type', 'DENY', '禁止');

insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'status', 'S', '正常');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'status', 'F', '失效');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'type', 'ALLOW', '允许');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'type', 'DENY', '禁止');

insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('tag', 'status', 'S', '正常');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('tag', 'status', 'F', '失效');


create table word
(
    id int unsigned auto_increment comment '应用自增主键' primary key,
    word varchar(128) not null comment '单词',
    type varchar(8) not null comment '类型',
    status char(1) not null default 'S' comment '状态',
    remark varchar(64) not null comment '配置描述' default '',
    operator_id varchar(64) not null default 'system' comment '操作员名称',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '敏感词表' ENGINE=Innodb default charset=UTF8 auto_increment=1;
create unique index uk_word on word (word) comment '唯一索引';

create table word_log
(
    id int unsigned auto_increment comment '应用自增主键' primary key,
    batch_id varchar(128) not null comment '批次号',
    word varchar(128) not null comment '单词',
    word_before varchar(128) null comment '变更前单词',
    word_after varchar(128) null comment '变更后单词',
    type varchar(8) not null comment '类型',
    status char(1) not null default 'S' comment '单词状态',
    remark varchar(64) not null comment '配置描述' default '',
    operator_type varchar(16) not null default '' comment '操作类别',
    operator_id varchar(64) not null default 'system' comment '操作员名称',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '敏感词操作日志表' ENGINE=Innodb default charset=UTF8 auto_increment=1;
create index ix_word on word_log (word) comment '单词普通索引';
create index ix_batch_id on word_log (batch_id) comment '批次号普通索引';
create index ix_update_time on word_log (update_time) comment '更新时间普通索引';

insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'operator_type', 'CREATE', '新增');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'operator_type', 'DELETE', '删除');
insert into lc_enum_mapping (table_name, column_name, `key`, label)  values ('word_log', 'operator_type', 'UPDATE', '更新');

create table tag
(
    id int unsigned auto_increment comment '应用自增主键' primary key,
    tag_code varchar(128) not null comment '标签编码',
    tag_label varchar(128) not null comment '标签描述',
    status char(1) not null default 'S' comment '状态',
    remark varchar(64) not null comment '配置描述' default '',
    operator_id varchar(64) not null default 'system' comment '操作员名称',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '标签表' ENGINE=Innodb default charset=UTF8 auto_increment=1;
create unique index uk_tag_code on tag (tag_code) comment '标签标识唯一索引';

create table word_tag_mapping
(
    id int unsigned auto_increment comment '应用自增主键' primary key,
    word varchar(128) not null comment '单词信息',
    tag_code varchar(128) not null comment '标签编码',
    operator_id varchar(64) not null default 'system' comment '操作员名称',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '标签单词映射表' ENGINE=Innodb default charset=UTF8 auto_increment=1;
create unique index uk_word_tag_mapping on word_tag_mapping (word, tag_code) comment '标签单词映射唯一索引';
