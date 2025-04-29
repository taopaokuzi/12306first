drop table if exists `member`;
create table `member` (
  `id` bigint not null comment 'id',
  `mobile` varchar(11) comment '手机号',
  primary key (`id`),
  unique key `mobile_unique` (`mobile`)
) engine=innodb default charset=utf8mb4 comment='会员';
INSERT INTO `member` (`id`, `mobile`)
VALUES (1, '12345678901');
CREATE TABLE passenger (
                           id BIGINT PRIMARY KEY,
                           name VARCHAR(255)
);
