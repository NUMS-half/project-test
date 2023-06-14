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
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('111111111', null, 't227601', null, null, '2023-06-12 20:25:16', null, '2023-06-12 20:25:16');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('123456789', '8ceeee2995f3459ba1955f85245dc7a5', 't58421', 'project_service_ add_test', 'admin', '2023-06-12 20:25:16', 'admin', '2023-06-12 20:25:16');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('12fbd95c6a7a4c27951c7658f673b135', '8ceeee2995f3459ba1955f85245dc7a5', '卫禹轩', '刘慧娟', 'admin', '2023-06-12 20:23:55', 'admin', '2023-06-12 20:23:55');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('1775449254544479b93311dcf6077ec8', '8ceeee2995f3459ba1955f85245dc7a5', '修改后的第三个测试项目名称', '第三个测试项目_for_update', 'admin', '2023-06-10 16:17:15', 'admin', '2023-06-12 20:25:16');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('283dcf241cf245aea824dc10bbb3d680', '8ceeee2995f3459ba1955f85245dc7a5', '修改后的第一个项目名称', '修改后的第一个项目内容', 'admin', '2020-09-23 20:27:42', 'admin', '2023-06-09 16:40:27');
INSERT INTO questionnaire.project_info (id, user_id, project_name, project_content, created_by, creation_date, last_updated_by, last_updated_date) VALUES ('4cd6ccb65c894eafaa70b12330f8c2f8', '8ceeee2995f3459ba1955f85245dc7a5', '第一个项目', '第一个项目的方法', 'admin', '2020-09-27 16:23:20', 'admin', '2020-09-27 16:23:20');
