package com.wangjj.scoreinquirywxback.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName : UserDTO
 * @Author : wangJJ
 * @Date : 2020/2/21 10:41
 * @Description : TODO
 */
@Data
public class UserDTO {

	@Id
	@GeneratedValue
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
	private Long infoId;
	/** 创建人 */
	private String createdBy ;
	/** 创建时间 */
	private Date createdTime ;
	/** 更新人 */
	private String updatedBy ;
	/** 更新时间 */
	private Date updatedTime ;
	/**角色*/
	private Long roleId;
	/** 电话 */
	private String phone ;
}
