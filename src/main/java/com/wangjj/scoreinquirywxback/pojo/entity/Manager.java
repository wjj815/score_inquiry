package com.wangjj.scoreinquirywxback.pojo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName : Manager
 * @Author : wangJJ
 * @Date : 2019/12/25 16:11
 * @Description :  管理员
 */
@ToString
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_manager")
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/** 管理员姓名 */
	private String managerName ;
	/** 管理员电话 */
	private String mobilePhone ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;
	/** 微信 */
	private String weiXin;

}
