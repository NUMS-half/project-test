create table user_info
(
    id               varchar(50) not null comment '用户表主键'
        primary key,
    username         varchar(10) not null comment '用户名',
    password         varchar(10) null comment '密码',
    start_time       datetime    null comment '开始时间',
    stop_time        datetime    null comment '截止时间（时间戳）',
    status           varchar(2)  null comment '是否启用（1启用，0不启用）',
    created_by       char(32)    null comment '创建人',
    creation_date    datetime    null comment '创建时间',
    last_updated_by  char(32)    null comment '最后修改人',
    last_update_date datetime    null comment '最后修改时间',
    constraint user_info_pk
        unique (username)
)
    charset = utf8mb3
    row_format = COMPACT;

INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('06f2da79c1404c7ea880916d77a0d915', 'eeeeee', '111111', '2023-06-01 15:06:54', '2023-06-13 13:06:10', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('09bd14a0ea2e41fd93f4866f1335b5e7', 'admin222', '123456', '2023-06-01 15:06:30', '2023-06-13 15:06:22', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('111111111', 't240361', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('123456789', 't4651', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('12e774ac19514ebfa1d958aaf61d0245', 'dddddd', '123456', '2023-06-01 15:06:35', '2024-06-01 15:06:36', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('348c3e7c07094f34b1486622434d7b5f', 'gggggg', '123456', '2023-06-01 15:06:23', '2023-06-30 15:06:16', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('8ceeee2995f3459ba1955f85245dc7a5', 'admin', '123456', '2023-06-14 06:06:38', '2023-06-21 09:06:38', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('a3777b80bb6f4f4583629f67da26c921', 'bbbbbb', '123456', '2023-06-01 15:06:53', '2023-09-15 15:09:47', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('a70b935f9df04886a10467eacd8a1fad', 'ffffff', '123456', '2023-06-01 15:06:12', '2023-06-30 15:06:06', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('d6938b1c6b884165a681ca1873b04062', 'aaaaaa', '123456', '2023-06-12 10:06:32', '2023-07-05 10:07:20', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('e051b86740dc4c49978240a558433938', 'forupdate', 'update2', '2023-06-01 15:06:53', '2023-06-01 15:06:53', '0', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('e23a37bb68164e30a340f9b5403fa4a4', 't43931', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('ebf33dab2a82445fb572d74f65f0c668', 'hhhhhh', '123456', '2023-06-07 12:06:43', '2023-06-07 12:06:45', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('f44a4545aee74a5291b2ba58a22b2903', 'cccccc', '123456', '2023-06-01 15:06:53', '2023-09-21 10:09:46', '1', null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('f7edae0626d34abbbe1a27158fabcc5b', 't295031', 'test_pswd', null, null, null, null, null, null, null);
