package com.wangjj.scoreinquirywxback.constant;

/**
 * @ClassName : UserType
 * @Author : wangJJ
 * @Date : 2020/2/22 23:11
 * @Description : 用户类型（管理员，教师，家长 , 学生）
 */
public enum UserType {

	MANAGER(1L),TEACHER(2L),PARENT(3L),STUDENT(4L);

	private Long id;

	UserType(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
