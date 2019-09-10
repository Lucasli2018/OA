
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('1', null, '系统管理', null);
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('2', '/role_list', '岗位管理', '1');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('3', '/department_list', '部门管理', '1');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('4', '/user_list', '用户管理', '1');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('5', '/role_list', '岗位列表', '2');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('6', '/role_delete', '岗位删除', '2');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('7', '/role_add', '岗位添加', '2');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('8', '/role_edit', '岗位修改', '2');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('9', '/department_list', '部门列表', '3');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('10', '/department_delete', '部门删除', '3');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('11', '/department_add', '部门添加', '3');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('12', '/department_edit', '部门修改', '3');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('13', '/user_list', '用户列表', '4');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('14', '/user_delete', '用户删除', '4');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('15', '/user_add', '用户添加', '4');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('16', '/user_edit', '用户修改', '4');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('17', '/user_initPassword', '用户初始化密码', '4');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('18', null, '网上交流', null);
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('19', '/forumManage_list', '论坛管理', '18');
INSERT INTO `itcast_privilege`(id,url,name,parentId) VALUES ('20', '/forum_list', '论坛', '18');

--INSERT INTO `itcast_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', null, null, null, null, null);
