# Wridpluins
 扩展Mybatits Plugins
 解决主从数据不一致 脏读的问题    修改数据时  修改成功则 将表名作为key放入redis  1秒（可配置）   
 当读取数据时 主动切换到从库. 解析查询sql中的表名作为key 查询redis    
 如果redis 中无该key 则继续读从库，  
 如果redis 中有key 说明该表1秒内有修改过数据,切换db到主库，读主库
 
 
CREATE TABLE `t_aaa_users` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`e_mail` VARCHAR(80) NOT NULL COMMENT '邮箱/登陆用户名',
	`name` VARCHAR(32) NOT NULL COMMENT '姓名',
	`password` VARCHAR(100) NOT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL COMMENT '描述',
	`status` INT(2) NOT NULL DEFAULT '1' COMMENT '是否启用0停用 1启用 2禁用',
	`phone_no` VARCHAR(15) NULL DEFAULT NULL,
	`miss_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	`allow_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ,
	`miss_number` INT(11) NOT NULL DEFAULT '0' COMMENT ,
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`create_by` VARCHAR(32) NULL DEFAULT NULL,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_by` VARCHAR(32) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `e_mail` (`e_mail`)
)
COMMENT='用户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
