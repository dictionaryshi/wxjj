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

