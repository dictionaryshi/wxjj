CREATE TABLE `user_passport` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `passport` varchar(50) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '姓名',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_passport` (`passport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号';

CREATE TABLE `sku_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '品类id',
  `category_name` varchar(256) NOT NULL DEFAULT '' COMMENT '品类名称',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类';

CREATE TABLE `goods_sku` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `sku_name` varchar(256) NOT NULL DEFAULT '' COMMENT '商品名称',
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '品类id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_sku_name` (`sku_name`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';

CREATE TABLE `stock_base_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '名称',
  `address` varchar(512) NOT NULL DEFAULT '' COMMENT '地址',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库基本信息';

CREATE TABLE `sku_stock` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stock_base_info_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '仓库id',
  `sku_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品id',
  `stock` bigint(20) NOT NULL DEFAULT '0' COMMENT '库存',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_stock_base_info_id_sku_id` (`stock_base_info_id`,`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品库存';

CREATE TABLE `sku_stock_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stock_base_info_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '仓库id',
  `sku_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品id',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作类型',
  `stock_offset` bigint(20) NOT NULL DEFAULT '0' COMMENT '库存操作值',
  `stock_before` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作前库存',
  `stock_after` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作后库存',
  `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单id',
  `operator` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作人',
  `remark` varchar(256) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_stock_base_info_id_sku_id` (`stock_base_info_id`,`sku_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存操作明细';

CREATE TABLE `sku_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单id',
  `stock_base_info_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '仓库id',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单类型',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `confirm_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单提交时间',
  `operator` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作人',
  `price` bigint(20) NOT NULL DEFAULT '0' COMMENT '金额',
  `customer_name` varchar(256) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `customer_phone` varchar(64) NOT NULL DEFAULT '' COMMENT '客户电话',
  `customer_address` varchar(512) NOT NULL DEFAULT '' COMMENT '客户地址',
  `remark` varchar(256) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_id` (`order_id`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品订单';

CREATE TABLE `order_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单id',
  `sku_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品id',
  `number` bigint(20) NOT NULL DEFAULT '0' COMMENT '数量',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_id_sku_id` (`order_id`,`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单条目';

CREATE TABLE `job_lock` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `lock_name` varchar(32) NOT NULL DEFAULT '' COMMENT '锁名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_lock_name` (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='job锁';
INSERT INTO `job_lock` ( `lock_name`) VALUES ( 'schedule_lock');

CREATE TABLE `job_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `app_name` varchar(64) NOT NULL DEFAULT '' COMMENT 'app标识',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '0:自动注册, 1:手动录入',
  `address_list` longtext COMMENT '地址列表',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_app_name_name` (`app_name`,`name`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='执行器';

CREATE TABLE `job_registry` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `app_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'app标识',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_app_name_address` (`app_name`,`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='app注册';

CREATE TABLE `job_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '执行器id',
  `job_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'job id',
  `executor_address` varchar(255) NOT NULL DEFAULT '' COMMENT '执行器地址',
  `executor_app` varchar(255) NOT NULL DEFAULT '' COMMENT '执行器app',
  `executor_handler` varchar(255) NOT NULL DEFAULT '' COMMENT '执行器名称',
  `executor_param` varchar(512) NOT NULL DEFAULT '' COMMENT '执行器参数',
  `executor_sharding_param` varchar(32) NOT NULL DEFAULT '' COMMENT '执行器任务分片参数',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '调度时间',
  `trigger_code` int(11) NOT NULL DEFAULT '-1' COMMENT '调度结果',
  `trigger_msg` longtext COMMENT '调度日志',
  `handle_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '执行时间',
  `handle_code` int(11) NOT NULL DEFAULT '-1' COMMENT '执行状态',
  `handle_msg` longtext COMMENT '执行日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '告警状态',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_group_id` (`job_group_id`),
  KEY `idx_job_id` (`job_id`),
  KEY `idx_trigger_time` (`trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='job log';

CREATE TABLE `job_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `job_group_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '执行器id',
  `job_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '任务描述',
  `alarm_email` varchar(255) NOT NULL DEFAULT '' COMMENT '告警邮件',
  `schedule_type` varchar(32) NOT NULL DEFAULT '' COMMENT '调度类型',
  `schedule_config` varchar(255) NOT NULL DEFAULT '' COMMENT '调度配置',
  `executor_route_strategy` varchar(32) NOT NULL DEFAULT '' COMMENT '路由策略',
  `executor_app` varchar(255) NOT NULL DEFAULT '' COMMENT '执行器app',
  `executor_handler` varchar(255) NOT NULL DEFAULT '' COMMENT '执行器名称',
  `executor_param` varchar(512) NOT NULL DEFAULT '' COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(32) NOT NULL DEFAULT '' COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务超时',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `child_job_id` varchar(512) NOT NULL DEFAULT '' COMMENT '子任务id',
  `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态',
  `trigger_last_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '任务最后一次执行时间',
  `trigger_next_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '任务下次调度时间',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_group_id` (`job_group_id`),
  KEY `idx_job_desc` (`job_desc`),
  KEY `idx_executor_handler` (`executor_handler`),
  KEY `idx_trigger_next_time` (`trigger_next_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='job信息';

CREATE TABLE `mq_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `topic` varchar(255) NOT NULL DEFAULT '' COMMENT '主题',
  `mq_group` varchar(255) NOT NULL DEFAULT '' COMMENT '分组',
  `data` longtext NOT NULL COMMENT '数据',
  `status` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态',
  `retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `sharding_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '分片id',
  `timeout` int(11) NOT NULL DEFAULT '0' COMMENT '超时',
  `effect_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '生效时间',
  `log` longtext NOT NULL COMMENT '日志',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_topic_group_status_effect_time` (`topic`,`mq_group`,`status`,`effect_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息';

CREATE TABLE `mole_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `next_exe_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下一次执行时间',
  `exe_interval_sec` int(11) NOT NULL DEFAULT '0' COMMENT '执行间隔',
  `exe_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行次数',
  `max_exe_count` int(11) NOT NULL DEFAULT '0' COMMENT '最大执行次数',
  `exe_status` int(11) NOT NULL DEFAULT '0' COMMENT '任务状态',
  `error_message` longtext COMMENT '错误信息',
  `shared_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '分库id',
  `param_type_json` varchar(1024) NOT NULL DEFAULT '' COMMENT '参数类型',
  `params_json` longtext COMMENT '参数',
  `target_class_name` varchar(512) NOT NULL DEFAULT '' COMMENT '类名',
  `method_name` varchar(512) NOT NULL DEFAULT '' COMMENT '方法名',
  `bean_name` varchar(512) NOT NULL DEFAULT '' COMMENT 'spring bean名',
  `env` varchar(32) NOT NULL COMMENT '环境',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分布式事务表';

commit;

