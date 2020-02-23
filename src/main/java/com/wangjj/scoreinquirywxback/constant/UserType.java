package com.wangjj.scoreinquirywxback.constant;

/**
 * @ClassName : UserType
 * @Author : wangJJ
 * @Date : 2020/2/22 23:11
 * @Description : 用户类型（管理员，教师，家长）
 */
public enum UserType {

	MANAGER(1),TEACHER(2),PARENT(3);

	private int type;

	UserType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
