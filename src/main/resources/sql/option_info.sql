create table option_info
(
    id          varchar(50) not null comment '选项ID'
        primary key,
    question_id varchar(50) not null comment '所属问题ID',
    choose_term text        null comment '选项内容',
    fraction    text        null comment '度量分数',
    constraint option_info_question_info_id_fk
        foreign key (question_id) references question_info (id)
)
    comment '选项信息';

INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('026ee957f8654b06a429a979ba344bee', 'fdfb02e547eb419eb26025b35f50484e', '不是', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('03397aa9c317408e88d8b173ebf8235a', '8f102054b681499fb79419d1d5164083', '444', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('0479c20e1b4c462bb2323812c8a28e36', '3304b2a4500f4885812d45e0bea83756', '亲密接触', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('06f0748a42774d259d0b0fd75950d281', '4ae18b87602e4af4a6110b85f61cb6d3', '1', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('0d9b1112a7814d37a15e9f2b9a3398e8', '275defe71a744129b6d568bbba9efc1d', '温柔', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('10d442f407fa46718b19c55d85490b6a', '3304b2a4500f4885812d45e0bea83756', '发送消息', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('15ad39cf24024319a6e162db65471c6a', '347145b3d9e14a4190e8157348b087f8', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('1950510bde654b32b24224c433639e67', 'fdfb02e547eb419eb26025b35f50484e', '是', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('1d8753fdd1c0474f93847f10c168cf3d', 'dcb6a355dc884d488922068e52aceac9', '1', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('1de6d7577e204424b3da5227780e751f', 'd62671e7e0af4fbeb5bc78f566f2de76', '充满互惠和感激', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('1fa1413b077f455c99732547c3154405', '3acb12d9c5b342c8905e6d4a5bb15f7d', '共同成长', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('203a8674d9a9441f92cedaf06be94c34', 'd62671e7e0af4fbeb5bc78f566f2de76', '沟通良好且频繁', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('20ab767445cf472db1ff01ec41becbab', 'dcb6a355dc884d488922068e52aceac9', '3', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('21b6d5040bdd43f9bdcbf5fe89e439c9', '98be7647964d44bcbb5e0343832b00b1', '女', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('25d75eab506c472791e490fdd2e5c2da', 'e33e813c62dc4318982b6a508ac0fe41', '难', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('270e2c00e98047169d5a42ab04036340', 'd62671e7e0af4fbeb5bc78f566f2de76', '年龄和性别相似', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('2f680cec906842509d80cb14baf7f1fd', 'd62671e7e0af4fbeb5bc78f566f2de76', '相互支持和理解', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('317fbada98ba4d83b2f2b0052886a9e3', '142e7461fa4a4720b7407b9031b8795d', '3-6', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('384db932afe84a03934d6e42ea7f82a6', '9b0bdea4fed249a6bcb1ab62ada68048', '偶尔', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('3ae63e0388564405a3d7677273c09dfe', 'ceae34273ccb425181a0da1fe5967311', '>3', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('3b0403ba0ad44ae3a025b4efe56c4c18', '59bb97f6379c475794fe86c3cc7d8b71', '男', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('3b9ed66265c94adb953059d58e6c8e19', '59bb97f6379c475794fe86c3cc7d8b71', '女', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('3df479508644464ba60e920977bbf3c4', '142e7461fa4a4720b7407b9031b8795d', '0', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('3e3f2f5694c04955bd090a9f831a2137', '142e7461fa4a4720b7407b9031b8795d', '1-3', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('44aef660794c4f2f90f249ca3252eccd', '3304b2a4500f4885812d45e0bea83756', '打电话', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('46520698d7e64854b19d683826ebf892', 'd7087fa60fb946329808fa964a64623a', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('470f14a11f0e4945a1a2ba528883ca0d', '984353f9b2e4492389176f8b89c3363e', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('4b826f054942416f99625b037adb04aa', '4999cd7e273c48a9bbaa60e924b05a78', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('4dd7a89ef9f746d2b5dd91d1f4e2418b', '9b0bdea4fed249a6bcb1ab62ada68048', '从不', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('4fbfa9244e774c02abac877ff7ee6b98', '9995c2b319324904a60dad4fc371a937', '11-20', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('520c73b74b80468a87f01f194cc3abfc', 'd62671e7e0af4fbeb5bc78f566f2de76', '美满的亲密关系', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('5516e82292054992a16231cfd1d670e4', '3acb12d9c5b342c8905e6d4a5bb15f7d', '快乐', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('58ad004f16b94263bdf9f064aabe1ebb', '9b0bdea4fed249a6bcb1ab62ada68048', '经常', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('58fefad763414b65a8f5af8d00ace47e', '3a742f153cb04ffbbeecb47f8f6d6fb1', '1234', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('5ab4d484af0d42ae9d6f045588f26134', 'ceae34273ccb425181a0da1fe5967311', '0', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('5e9e3c1e0d934f449e478db3591a35fe', 'e33e813c62dc4318982b6a508ac0fe41', '难上加难', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('614a236e3f7147d8838959c74cfc8905', 'd62671e7e0af4fbeb5bc78f566f2de76', '家庭背景和人生经验顺畅', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('6819eee0af9f462289dc2c317067df1c', '9dc2a0cd2a434f0692aa0005fdfd43da', 'ccc', '3');
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('6dc0541761cf4bc4b5fb388f6b5eeca6', '3408df18524a4c0387375b3937578bb9', '2', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('6e137f1b1d5e4bea9ab61f1a98b9324a', '142e7461fa4a4720b7407b9031b8795d', '更多', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('746c69d9227e4876b56760e551d6a661', '2bea19c23ea84ef99ea7901e3ff300a1', '唐嫣', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('7845cea4c0e74ff9817dd511ff20473c', '275defe71a744129b6d568bbba9efc1d', '开朗', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('78eb67ce2dc049018edf1ce7567f1017', 'fdfb02e547eb419eb26025b35f50484e', '不确定', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('7e04f4d3eacd45eb93ad74e41121fe02', 'ceae34273ccb425181a0da1fe5967311', '1-3', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('81032e2a1b914e5687e28483853b6a16', 'd8a2f5443ee34ebe87ccb99a1d763020', '共同成长', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('833a21d942244ed68831a8b79f3e403e', '8e159ea78de844d7a4876a5c3783a7e8', '普信', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('8b44bc4751a24c40a3fea0980b8b68fa', '2bea19c23ea84ef99ea7901e3ff300a1', '杨幂', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('8c22616ba0e64f1083848a63d6c7734a', 'd8a2f5443ee34ebe87ccb99a1d763020', '支持与理解', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('8d7aa3ff84994d0dabfcc4428cd1a0c9', '8e159ea78de844d7a4876a5c3783a7e8', '温柔', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('8e1af8b4e94b43738090096afbd7487c', 'a089700b7687458f9f1b928da6ddb616', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('8eea27a20ef54ae186a9d81e9ca0b0d3', '8f102054b681499fb79419d1d5164083', '222', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('8f3ebff7f8004aadb1c2626f4b653731', '2bea19c23ea84ef99ea7901e3ff300a1', '刘诗诗', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('93976d075624417ebd839004899bc1c8', '3304b2a4500f4885812d45e0bea83756', '礼物', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('9913375f65bd450fad102f33e99a3963', '3408df18524a4c0387375b3937578bb9', '1', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('9ec117560dfc42e5849b9b329d09486e', 'd5205778ed5249fea79642ec0a485f68', '123', '1');
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('a5d5927731784e6d8b964ffb39604f16', '4ae18b87602e4af4a6110b85f61cb6d3', '2', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('a718786cbdd04de19160487cbfdf2165', 'd8a2f5443ee34ebe87ccb99a1d763020', '快乐', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('a84a6f64ea4146208a591d427c784cfe', 'd5205778ed5249fea79642ec0a485f68', '123', '2');
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('a940df279a934ba3a67ce5c4f33126df', '98be7647964d44bcbb5e0343832b00b1', '男', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('ac40e122b2db4cc9aaf9504d93e96ef6', '3408df18524a4c0387375b3937578bb9', '3', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('ade5703689ae4014952496ff09c3327e', 'd8a2f5443ee34ebe87ccb99a1d763020', '自由', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('b0a8b39b27bd4d6d8234aea5e1df48b2', '275defe71a744129b6d568bbba9efc1d', '体贴', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('b3d0f32ab103491789c6d311b32d9577', '8f102054b681499fb79419d1d5164083', '333', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('b5d464b98b614427b179635a381e0e3c', '9995c2b319324904a60dad4fc371a937', '1-10', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('b7182a772da84e698a76a12c1c4c8433', '3acb12d9c5b342c8905e6d4a5bb15f7d', '安全感', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('be664619e36e43978ce49bb290deffa6', '8f102054b681499fb79419d1d5164083', '111', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('be69cea6936c443ea45939ede06b1f9e', 'dcb6a355dc884d488922068e52aceac9', '2', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('c0a17a39c7b0429da29576d58c0b3ca1', '3a742f153cb04ffbbeecb47f8f6d6fb1', '123', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('c34ed59b723f4fe994ff84915cb0814f', 'd8a2f5443ee34ebe87ccb99a1d763020', '相互信任', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('c51e820b3a8946db808687d1724c8df2', '8e159ea78de844d7a4876a5c3783a7e8', '自信', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('c8e6fe385f6849b8b95c884e4b20f2c0', '98be7647964d44bcbb5e0343832b00b1', '保密', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('ce19218ea49849c18e6fb786c3048d44', '8fc2689ac58f41be9499a7127739b6e5', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('d06cbd63ab554b818061d571a32d514f', 'fd25548ed7db43a08f9e58985bf9be98', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('d1f2a5e4dd1e4f95a58291f6a9966c16', '9dc2a0cd2a434f0692aa0005fdfd43da', 'aaa', '1');
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('d5efd115eb3f4e0888792f242e420e90', '8e159ea78de844d7a4876a5c3783a7e8', '开朗', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('d6bfc3c497ac49e4bc5b5c29cfe7d703', '3a742f153cb04ffbbeecb47f8f6d6fb1', '12345', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('de91976327274168b1998d3b4ddd391f', '9995c2b319324904a60dad4fc371a937', '21-30', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('dfafd9cad8c34b7682688ce796f41415', 'e33e813c62dc4318982b6a508ac0fe41', '不难', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('e54bfe09342b4b548678f1bbe4a9fffb', '275defe71a744129b6d568bbba9efc1d', '自信', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('e57a8a2f2cde4390934003792fef9b1d', 'd6de77f94c444afdadb58ceaf02811df', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('f0c41c5d3fcb45b2afd73dafcf0faa48', '4ae18b87602e4af4a6110b85f61cb6d3', '3', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('f1519784ce4f40aca75d0488f713e8f0', 'dcb6a355dc884d488922068e52aceac9', '123', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('f5145514fe0749f7a369e5e331d9130e', '9dc2a0cd2a434f0692aa0005fdfd43da', 'bbb', '2');
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('f5a21c514ef9468eb5faacd49829afef', 'd8a2f5443ee34ebe87ccb99a1d763020', '安全感', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('fe4b1bf99a4d40db803b278bd37e43e9', '2b6fe6dfdc284fc290a3a97065c64cbe', null, null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('fe988bacdf6f46b8bb08dca0ba1f63aa', '2bea19c23ea84ef99ea7901e3ff300a1', '高圆圆', null);
INSERT INTO questionnaire.option_info (id, question_id, choose_term, fraction) VALUES ('ffc66d1cd7cd4356a3c7dfb392ed87c0', '142e7461fa4a4720b7407b9031b8795d', '6-9', null);
