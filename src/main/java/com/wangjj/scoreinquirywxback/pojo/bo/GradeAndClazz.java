package com.wangjj.scoreinquirywxback.pojo.bo;

import com.wangjj.scoreinquirywxback.pojo.entity.Clazz;
import com.wangjj.scoreinquirywxback.pojo.entity.Grade;
import lombok.Data;

/**
 * @ClassName : GradeAndClazz
 * @Author : wangJJ
 * @Date : 2020/2/20 17:23
 * @Description : TODO
 */
@Data
public class GradeAndClazz {

	private Grade grade;
	private Clazz clazz;
}
