INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (1, 'welcome', 'layui-icon layui-icon-home', '首页', 1, '/welcome', null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (2, 'scoreInquiry', 'layui-icon layui-icon-log', '成绩统计查询', 2, null, null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (3, 'exam', 'layui-icon layui-icon-list', '考试列表', 1, '/exam', 2);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (4, 'studentManager', 'layui-icon layui-icon-face-smile', '学生信息管理', 3, null, null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (5, 'student', 'layui-icon layui-icon-list', '学生列表', 1, '/student', 4);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (6, 'baseInfoManager', 'layui-icon layui-icon-template-1', '基础信息管理', 4, null, null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (7, 'course', 'layui-icon layui-icon-table', '课程列表', 1, '/course', 6);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (8, 'clazz', 'layui-icon layui-icon-list', '班级列表', 2, '/clazz', 6);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (9, 'grade', 'layui-icon layui-icon-list', '年级列表', 3, '/grade', 6);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (10, 'teacherInfoManager', 'layui-icon layui-icon-group', '教师信息管理', 5, null, null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (11, 'teacher', 'layui-icon layui-icon-list', '教师列表', 1, '/teacher', 10);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (12, 'userInfoManager', 'layui-icon layui-icon-user', '用户信息管理', 6, null, null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (13, 'userList', 'layui-icon layui-icon-list', '用户列表', 1, '/userList', 12);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (14, 'settings', 'layui-icon layui-icon-set', '系统设置', 7, '/settings', null);
INSERT IGNORE INTO  micro_campus.t_menu (id, menu_code, menu_icon, menu_name, menu_order, menu_url, parent_menu_id) VALUES (15, 'enterScore', 'layui-icon layui-icon-survey', '成绩登记', 8, '/enterScore', null);