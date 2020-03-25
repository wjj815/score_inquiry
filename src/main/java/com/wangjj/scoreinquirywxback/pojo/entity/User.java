package com.wangjj.scoreinquirywxback.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName : User
 * @Author : wangJJ
 * @Date : 2020/1/2 16:51
 * @Description : 角色表
 */
@ToString(exclude = "role")
@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User  {

	@Id
	private Long id;
	/**登录账号*/
	private String account;
	/**登录密码*/
	private String password;
	/**用户姓名*/
	private String name;
	/** 头像 */
	private String avatar ;
	/**用户信息*/
//	private Long infoId;
	/** 电话 */
	private String phone ;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;
	/**角色*/
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

	private String wxOpenId;

}
