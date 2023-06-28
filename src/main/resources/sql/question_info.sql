create table question_info
(
    id                   varchar(50) not null comment '题目ID'
        primary key,
    questionnaire_id     varchar(50) not null comment '题目所属问卷的ID',
    question_description text        not null comment '题目描述',
    type                 int         not null comment '题目类型(枚举值)',
    left_title           text        null comment '矩阵题左标题，采用逗号分隔',
    must_answer          tinyint(1)  not null comment '是否为必答题',
    question_index       int         not null comment '题目序号',
    constraint question_info_questionnaire_info_id_fk
        foreign key (questionnaire_id) references questionnaire_info (id)
)
    comment '题目信息';

INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('142e7461fa4a4720b7407b9031b8795d', 'df1a941ee60d4748b6070678feedf156', '你谈过几次恋爱？', 1, null, 1, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('275defe71a744129b6d568bbba9efc1d', 'df1a941ee60d4748b6070678feedf156', '以下哪一种性格特点你最倾向于？', 1, null, 1, 4);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('2b6fe6dfdc284fc290a3a97065c64cbe', 'df1a941ee60d4748b6070678feedf156', '你对异地恋有何看法？', 3, null, 1, 8);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('2bea19c23ea84ef99ea7901e3ff300a1', '52a814095d08475bb863be1694b906b3', '你认识他们吗？', 2, null, 1, 3);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('3304b2a4500f4885812d45e0bea83756', 'df1a941ee60d4748b6070678feedf156', '以下哪个物品能让你在恋爱关系中感到最幸福？', 1, null, 1, 7);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('3408df18524a4c0387375b3937578bb9', '34ee0b5bda2c4cc98dd48707b7160ef2', '矩阵4', 4, '1,2,3,4,5', 1, 4);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('347145b3d9e14a4190e8157348b087f8', 'e4af5ea3c3234ec3af5832f26cfe2c5e', '', 0, null, 1, 4);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('3a742f153cb04ffbbeecb47f8f6d6fb1', '34ee0b5bda2c4cc98dd48707b7160ef2', '多选3', 2, null, 1, 3);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('3acb12d9c5b342c8905e6d4a5bb15f7d', '6b96c89a12c64d3588e014ffcb14c9cf', '你在恋爱关系中最希望什么？', 2, null, 1, 4);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('4999cd7e273c48a9bbaa60e924b05a78', '34ee0b5bda2c4cc98dd48707b7160ef2', '填空2', 3, null, 1, 2);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('4ae18b87602e4af4a6110b85f61cb6d3', '6ce237c2fa9d4ff598f740593d99ee0e', '123？', 2, null, 1, 2);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('59bb97f6379c475794fe86c3cc7d8b71', '6b96c89a12c64d3588e014ffcb14c9cf', '你的性别是？', 1, null, 1, 2);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('8e159ea78de844d7a4876a5c3783a7e8', '6b96c89a12c64d3588e014ffcb14c9cf', '以下哪一种性格特点你最倾向于？', 1, null, 1, 3);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('8f102054b681499fb79419d1d5164083', '34ee0b5bda2c4cc98dd48707b7160ef2', '单选1', 1, null, 1, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('8fc2689ac58f41be9499a7127739b6e5', 'e4af5ea3c3234ec3af5832f26cfe2c5e', '', 0, null, 1, 2);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('984353f9b2e4492389176f8b89c3363e', '52a814095d08475bb863be1694b906b3', '你认识的明星的名字？（请写出三位）', 3, null, 1, 2);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('98be7647964d44bcbb5e0343832b00b1', 'df1a941ee60d4748b6070678feedf156', '你的性别是？', 1, null, 1, 10);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('9995c2b319324904a60dad4fc371a937', '52a814095d08475bb863be1694b906b3', '你认识的娱乐圈明星大概有几位？', 1, null, 1, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('9b0bdea4fed249a6bcb1ab62ada68048', '6ce237c2fa9d4ff598f740593d99ee0e', '您对下面这些频道的观看频率是：', 4, 'CCTV1,CCTV2,CCTV3', 1, 4);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('9b4a9950c02746e8a117a0f5c76075b6', '5', '修改后的问题描述', 2, null, 0, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('9dc2a0cd2a434f0692aa0005fdfd43da', '34ee0b5bda2c4cc98dd48707b7160ef2', '量表5', 5, null, 1, 5);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('a089700b7687458f9f1b928da6ddb616', '6ce237c2fa9d4ff598f740593d99ee0e', '你好', 3, null, 0, 3);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('ceae34273ccb425181a0da1fe5967311', '6b96c89a12c64d3588e014ffcb14c9cf', '你谈过几次恋爱？', 1, null, 1, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('d5205778ed5249fea79642ec0a485f68', '6ce237c2fa9d4ff598f740593d99ee0e', '123', 5, null, 1, 5);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('d62671e7e0af4fbeb5bc78f566f2de76', 'df1a941ee60d4748b6070678feedf156', '对于一个健康的恋爱关系，以下哪些特征符合你的认知？', 2, null, 1, 6);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('d6de77f94c444afdadb58ceaf02811df', 'df1a941ee60d4748b6070678feedf156', '你目前的恋爱状况是什么？', 3, null, 1, 3);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('d7087fa60fb946329808fa964a64623a', 'e4af5ea3c3234ec3af5832f26cfe2c5e', '', 0, null, 1, 3);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('d8a2f5443ee34ebe87ccb99a1d763020', 'df1a941ee60d4748b6070678feedf156', '你在恋爱关系中最希望什么？', 2, null, 1, 5);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('dcb6a355dc884d488922068e52aceac9', '6ce237c2fa9d4ff598f740593d99ee0e', '123?', 1, null, 1, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('e33e813c62dc4318982b6a508ac0fe41', 'df1a941ee60d4748b6070678feedf156', '你认为在大学谈恋爱难吗？', 1, null, 1, 2);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('fd25548ed7db43a08f9e58985bf9be98', 'e4af5ea3c3234ec3af5832f26cfe2c5e', '', 0, null, 1, 1);
INSERT INTO questionnaire.question_info (id, questionnaire_id, question_description, type, left_title, must_answer, question_index) VALUES ('fdfb02e547eb419eb26025b35f50484e', 'df1a941ee60d4748b6070678feedf156', '你是否认为恋爱关系对于大学生来说是必须的？', 1, null, 1, 9);
