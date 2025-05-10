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
drop table if exists word;
create table word
(
  id bigint(20) unsigned auto_increment comment '应用自增主键' primary key,
  word varchar(128) not null comment '单词',
  type varchar(8) not null comment '类型',
  status char(1) not null default 'Y' comment '状态',
  remark varchar(64) not null comment '配置描述' default '',
  create_by varchar(64) default '' comment '创建者',
  update_by varchar(64) default '' comment '创建者',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '敏感词表'  ;
create unique index uk_word on word (word) comment '唯一索引';

drop table if exists word_log;
create table word_log
(
  id bigint(20) unsigned auto_increment comment '应用自增主键' primary key,
  batch_id varchar(128) not null comment '批次号',
  word varchar(128) not null comment '单词',
  word_before varchar(128) null comment '变更前单词',
  word_after varchar(128) null comment '变更后单词',
  type varchar(8) not null comment '类型',
  status char(1) not null default 'Y' comment '单词状态',
  remark varchar(64) not null comment '配置描述' default '',
  operator_type varchar(16) not null default '' comment '操作类别',
  create_by varchar(64) default '' comment '创建者',
  update_by varchar(64) default '' comment '创建者',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '敏感词操作日志表'  ;
create index ix_word on word_log (word) comment '单词普通索引';
create index ix_batch_id on word_log (batch_id) comment '批次号普通索引';
create index ix_update_time on word_log (update_time) comment '更新时间普通索引';

drop table if exists tag;
create table tag
(
  id bigint(20) unsigned auto_increment comment '应用自增主键' primary key,
  tag_code varchar(64) not null comment '标签编码',
  tag_label varchar(128) not null comment '标签描述',
  status char(1) not null default 'Y' comment '状态',
  remark varchar(64) not null comment '配置描述' default '',
  create_by varchar(64) default '' comment '创建者',
  update_by varchar(64) default '' comment '创建者',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '标签表'  ;
create unique index uk_tag_code on tag (tag_code) comment '标签标识唯一索引';

drop table if exists word_tag_mapping;
create table word_tag_mapping
(
  id bigint(20) unsigned auto_increment comment '应用自增主键' primary key,
  word varchar(128) not null comment '单词信息',
  tag_code varchar(64) not null comment '标签编码',
  create_by varchar(64) default '' comment '创建者',
  update_by varchar(64) default '' comment '创建者',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间戳',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间戳'
) comment '标签单词映射表'  ;
create unique index uk_word_tag_mapping on word_tag_mapping (word, tag_code) comment '标签单词映射唯一索引';


-- 枚举
insert into sys_dict_type values(11, '敏感词状态', 'word_status', '0', 'admin', sysdate(), '', null, '敏感词状态列表');
insert into sys_dict_type values(12, '敏感词类型', 'word_type', '0', 'admin', sysdate(), '', null, '敏感词类型列表');
insert into sys_dict_type values(13, '标签状态', 'tag_status', '0', 'admin', sysdate(), '', null, '标签状态列表');
insert into sys_dict_type values(14, '敏感词日志操作', 'word_log_oper_type', '0', 'admin', sysdate(), '', null, '敏感词日志操作类型');

insert into sys_dict_data values(101, 1,  '正常', 'Y', 'word_status', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(102, 2,  '失效', 'N', 'word_status', '', 'danger',  'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(103, 1,  '允许', 'ALLOW', 'word_type', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(104, 2,  '禁止', 'DENY', 'word_type', '', 'danger',  'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(105, 1,  '正常', 'Y', 'tag_status', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(106, 2,  '失效', 'N', 'tag_status', '', 'danger',  'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(107, 1,  '新增', 'CREATE', 'word_log_oper_type', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(108, 2,  '删除', 'DELETE', 'word_log_oper_type', '', 'danger',  'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(109, 3,  '更新', 'UPDATE', 'word_log_oper_type', '', 'warning',  'N', '0', 'admin', sysdate(), '', null, '');

-- 菜单
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2000','敏感词管理','0','4','word',NULL,NULL,'','1','0','M','0','0',NULL,'language','admin','2025-05-10 12:54:28','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2001','操作日志','2000','4','log','word/log/index',NULL,'','1','0','C','0','0','word:log:list','log','admin','2025-05-10 13:05:01','admin','2025-05-10 13:08:56','操作日志菜单');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2002','操作日志查询','2001','1','#','',NULL,'','1','0','F','0','0','word:log:query','#','admin','2025-05-10 13:05:01','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2003','操作日志新增','2001','2','#','',NULL,'','1','0','F','0','0','word:log:add','#','admin','2025-05-10 13:05:01','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2004','操作日志修改','2001','3','#','',NULL,'','1','0','F','0','0','word:log:edit','#','admin','2025-05-10 13:05:01','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2005','操作日志删除','2001','4','#','',NULL,'','1','0','F','0','0','word:log:remove','#','admin','2025-05-10 13:05:02','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2006','操作日志导出','2001','5','#','',NULL,'','1','0','F','0','0','word:log:export','#','admin','2025-05-10 13:05:02','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2007','词标签映射','2000','3','mapping','word/mapping/index',NULL,'','1','0','C','0','0','word:mapping:list','color','admin','2025-05-10 13:05:19','admin','2025-05-10 13:09:21','标签单词映射菜单');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2008','标签单词映射查询','2007','1','#','',NULL,'','1','0','F','0','0','word:mapping:query','#','admin','2025-05-10 13:05:19','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2009','标签单词映射新增','2007','2','#','',NULL,'','1','0','F','0','0','word:mapping:add','#','admin','2025-05-10 13:05:19','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2010','标签单词映射修改','2007','3','#','',NULL,'','1','0','F','0','0','word:mapping:edit','#','admin','2025-05-10 13:05:19','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2011','标签单词映射删除','2007','4','#','',NULL,'','1','0','F','0','0','word:mapping:remove','#','admin','2025-05-10 13:05:19','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2012','标签单词映射导出','2007','5','#','',NULL,'','1','0','F','0','0','word:mapping:export','#','admin','2025-05-10 13:05:19','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2013','标签','2000','2','tag','word/tag/index',NULL,'','1','0','C','0','0','word:tag:list','tree-table','admin','2025-05-10 13:05:25','admin','2025-05-10 13:08:07','标签菜单');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2014','标签查询','2013','1','#','',NULL,'','1','0','F','0','0','word:tag:query','#','admin','2025-05-10 13:05:25','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2015','标签新增','2013','2','#','',NULL,'','1','0','F','0','0','word:tag:add','#','admin','2025-05-10 13:05:25','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2016','标签修改','2013','3','#','',NULL,'','1','0','F','0','0','word:tag:edit','#','admin','2025-05-10 13:05:25','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2017','标签删除','2013','4','#','',NULL,'','1','0','F','0','0','word:tag:remove','#','admin','2025-05-10 13:05:25','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2018','标签导出','2013','5','#','',NULL,'','1','0','F','0','0','word:tag:export','#','admin','2025-05-10 13:05:25','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2019','敏感词','2000','1','word','word/word/index',NULL,'','1','0','C','0','0','word:word:list','language','admin','2025-05-10 13:05:30','admin','2025-05-10 13:07:19','敏感词菜单');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2020','敏感词查询','2019','1','#','',NULL,'','1','0','F','0','0','word:word:query','#','admin','2025-05-10 13:05:30','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2021','敏感词新增','2019','2','#','',NULL,'','1','0','F','0','0','word:word:add','#','admin','2025-05-10 13:05:30','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2022','敏感词修改','2019','3','#','',NULL,'','1','0','F','0','0','word:word:edit','#','admin','2025-05-10 13:05:30','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2023','敏感词删除','2019','4','#','',NULL,'','1','0','F','0','0','word:word:remove','#','admin','2025-05-10 13:05:30','',NULL,'');
INSERT INTO `sensitive_word`.`sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`route_name`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES ('2024','敏感词导出','2019','5','#','',NULL,'','1','0','F','0','0','word:word:export','#','admin','2025-05-10 13:05:30','',NULL,'');


-- 测试数据
INSERT INTO `sensitive_word`.`word` (`id`,`word`,`type`,`status`,`remark`,`create_by`,`update_by`,`create_time`,`update_time`) VALUES ('2','二十八画生','DENY','Y','画家','','','2025-05-10 14:16:09','2025-05-10 14:16:08');

INSERT INTO `sensitive_word`.`tag` (`id`,`tag_code`,`tag_label`,`status`,`remark`,`create_by`,`update_by`,`create_time`,`update_time`) VALUES ('1','1','伟人','Y','伟人','','','2025-05-10 14:16:43','2025-05-10 14:16:43');
INSERT INTO `sensitive_word`.`tag` (`id`,`tag_code`,`tag_label`,`status`,`remark`,`create_by`,`update_by`,`create_time`,`update_time`) VALUES ('2','2','画家','Y','画家','','','2025-05-10 14:16:54','2025-05-10 14:16:53');

INSERT INTO `sensitive_word`.`word_tag_mapping` (`id`,`word`,`tag_code`,`create_by`,`update_by`,`create_time`,`update_time`) VALUES ('1','二十八画生','1','','','2025-05-10 14:17:15','2025-05-10 14:17:14');
INSERT INTO `sensitive_word`.`word_tag_mapping` (`id`,`word`,`tag_code`,`create_by`,`update_by`,`create_time`,`update_time`) VALUES ('2','二十八画生','2','','','2025-05-10 14:17:24','2025-05-10 14:17:23');


