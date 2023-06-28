create table project_info
(
    id                varchar(50)  not null comment '项目表主键'
        primary key,
    user_id           varchar(50)  null comment '用户id（没有用）',
    project_name      varchar(100) null comment '项目名称',
    project_content   text         null comment '项目说明',
    created_by        char(32)     null comment '创建人',
    creation_date     datetime     null comment '创建时间',
    last_updated_by   char(32)     null comment '最后修改人',
    last_updated_date datetime     null comment '最后修改时间'
)
    charset = utf8mb3
    row_format = COMPACT;

INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('0a5fb07eaf4645b89564dd1c406e546d', '8ceeee2995f3459ba1955f85245dc7a5', '编辑测试-项目名称wyx', '编辑测试-项目描述lhj', 'admin', '2023-06-09 12:04:13', 'admin', '2023-06-12 18:58:26');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('111111111', '8ceeee2995f3459ba1955f85245dc7a5', 't237491', 'test', 'admin', '2023-06-16 15:50:33', 'admin', '2023-06-16 15:50:33');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('1775449254544479b93311dcf6077ec8', '8ceeee2995f3459ba1955f85245dc7a5', '修改后的第三个测试项目名称', '第三个测试项目_for_update', 'admin', '2023-06-10 16:17:15', 'admin', '2023-06-26 01:24:50');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('212a3ee994d14838b11aa1642376954f', '8ceeee2995f3459ba1955f85245dc7a5', 't52181', 'project_service_ add_test', 'admin', '2023-06-21 17:06:12', 'admin', '2023-06-21 17:06:12');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('22531ecf92b64da4b8413fe0257672e0', 'a3777b80bb6f4f4583629f67da26c921', '不是admin', '123', 'bbbbbb', '2023-06-25 21:33:19', 'bbbbbb', '2023-06-25 21:33:19');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('283dcf241cf245aea824dc10bbb3d680', '8ceeee2995f3459ba1955f85245dc7a5', '修改后的第一个项目名称', '修改后的第一个项目内容', 'admin', '2020-09-23 20:27:42', 'admin', '2023-06-09 16:40:27');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('4cd6ccb65c894eafaa70b12330f8c2f8', '8ceeee2995f3459ba1955f85245dc7a5', '第一个项目', '第一个项目的方法', 'admin', '2020-09-27 16:23:20', 'admin', '2020-09-27 16:23:20');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('864407b29830489cb70069ff8c3088c8', '8ceeee2995f3459ba1955f85245dc7a5', 't95611', 'project_service_ add_test', 'admin', '2023-06-26 01:24:55', 'admin', '2023-06-26 01:24:55');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('b246065edb9b471f8c8495a339a90026', '8ceeee2995f3459ba1955f85245dc7a5', '123', '123', 'admin', '2023-06-23 23:47:20', 'admin', '2023-06-23 23:47:20');
