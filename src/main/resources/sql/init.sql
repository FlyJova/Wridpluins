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

INSERT INTO `t_aaa_users` (`e_mail`, `name`, `password`, `description`, `status`, `phone_no`, `miss_time`, `allow_time`, `miss_number`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES ('superMan1', '超人', '111111111111', '超人的描述', 1, '11111111111', '2018-08-13 15:25:54', '2018-08-13 15:25:54', 0, '2018-07-04 13:22:48', NULL, '2018-08-03 14:09:25', 'superMan');
