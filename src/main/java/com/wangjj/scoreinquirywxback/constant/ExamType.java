package com.wangjj.scoreinquirywxback.constant;

/**
 * @ClassName : ExamType
 * @Author : wangJJ
 * @Date : 2020/2/14 18:58
 * @Description : TODO
 */
public enum ExamType {
	/**
	 * 年级考试类型
	 */
	GRADE_EXAM_TYPE(1),

	/**
	 * 班级考试类型
	 */
	CLAZZ_EXAM_TYPE(0);

	private int value;

	ExamType(int value) {
		this.value = value;
	}

	public int getValue(){
		return value;
	}
}
