create table user_info
(
    id               varchar(50) not null comment '用户表主键'
        primary key,
    username         varchar(10) null comment '用户名',
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

INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('013e80f8f22346518637bb6a84536dd9', 't22701', 'test_pswd', '2023-06-12 10:06:32', '2023-07-05 10:07:20', '0', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('06f2da79c1404c7ea880916d77a0d915', 'eeeeee', '111111', '2023-06-01 15:06:54', '2023-06-13 13:06:10', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('09bd14a0ea2e41fd93f4866f1335b5e7', 'admin222', '123456', '2023-06-01 15:06:30', '2023-06-13 15:06:22', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('12e774ac19514ebfa1d958aaf61d0245', 'dddddd', '123456', '2023-06-01 15:06:35', '2024-06-01 15:06:36', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('348c3e7c07094f34b1486622434d7b5f', 'gggggg', '123456', '2023-06-01 15:06:23', '2023-06-30 15:06:16', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('3f830074801b494c90b7e1f22efa7c91', 't1381', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('5135ab238c05411883320d1e729a1622', 't291881', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('80515ccc65e5413a9682c55b12658fbb', 't280361', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('8ceeee2995f3459ba1955f85245dc7a5', 'admin', '123456', '2023-06-14 06:06:38', '2023-06-21 09:06:38', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('9ab8e7c571d34416a7b4a4569b5f5fb3', 't88231', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('a3777b80bb6f4f4583629f67da26c921', 'bbbbbb', '123456', '2023-06-01 15:06:53', '2023-09-15 15:09:47', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('a70b935f9df04886a10467eacd8a1fad', 'ffffff', '123456', '2023-06-01 15:06:12', '2023-06-30 15:06:06', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('b9de64dbbda34174bbe01dfc704db704', 't250951', 'test_pswd', '2023-06-12 10:06:32', '2023-07-05 10:07:20', '0', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('c7082d64c195469f82fc65b033c51b4b', 't10141', 'test_pswd', null, null, null, null, null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('d6938b1c6b884165a681ca1873b04062', 'aaaaaa', '123456', '2023-06-12 10:06:32', '2023-07-05 10:07:20', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('e051b86740dc4c49978240a558433938', 'forupdate', 'update2', '2023-06-01 15:06:53', '2023-06-01 15:06:53', '0', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('ebf33dab2a82445fb572d74f65f0c668', 'hhhhhh', '123456', '2023-06-07 12:06:43', '2023-06-07 12:06:45', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('f44a4545aee74a5291b2ba58a22b2903', 'cccccc', '123456', '2023-06-01 15:06:53', '2023-09-21 10:09:46', '1', 'admin', null, null, null);
INSERT INTO questionnaire.user_info (id, username, password, start_time, stop_time, status, created_by, creation_date, last_updated_by, last_update_date) VALUES ('f770688a25214d65aed326d4640cb828', 't292581', 'test_pswd', null, null, null, null, null, null, null);
