package com.wangjj.scoreinquirywxback.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : MenuDTO
 * @Author : wangJJ
 * @Date : 2020/2/19 21:24
 * @Description : 菜单传输对象
 */

@Data
public class MenuDTO {

		/*菜单id*/
		private Long id;
		/*菜单名字*/
		private String menuName;
		/*菜单编码*/
		private String menuCode;
		/*菜单url*/
		private String menuUrl;
		/*菜单图标*/
		private String menuIcon;
		/*菜单顺序*/
		private Integer menuOrder;
		/*一个子菜单只能对应一个父菜单*/
		List<MenuDTO> childMenu;

		private Long roleId;
}
