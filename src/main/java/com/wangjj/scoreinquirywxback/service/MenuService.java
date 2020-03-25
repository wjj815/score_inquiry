package com.wangjj.scoreinquirywxback.service;

import com.wangjj.scoreinquirywxback.dao.MenuRepository;
import com.wangjj.scoreinquirywxback.pojo.dto.MenuDTO;
import com.wangjj.scoreinquirywxback.pojo.entity.Menu;
import com.wangjj.scoreinquirywxback.util.ParameterUtils;
import com.wangjj.scoreinquirywxback.util.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName : MenuService
 * @Author : wangJJ
 * @Date : 2020/1/7 22:22
 * @Description : 菜单业务的实现类
 */
@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;


	public List<MenuDTO> findMenuList(MenuDTO menuDTO) {
		List<Menu> menus = menuRepository.findAll(getMenuSpecification(menuDTO));
		return PropertyUtils.convert(menus,this::getMenuDTO);
	}

	private MenuDTO getMenuDTO(Menu menu) {
		MenuDTO menuDTO = new MenuDTO();
		PropertyUtils.copyNoNullProperties(menu,menuDTO);
		menuDTO.setParentMenuId(Objects.nonNull(menu.getParentMenu()) ? menu.getParentMenu().getId() : null);
		return menuDTO;
	}

	public List<MenuDTO> buildMenuTree(MenuDTO menuDTO) {
		List<MenuDTO> menuList = findMenuList(menuDTO);
		boolean[] visited = new boolean[menuList.size()];
		List<MenuDTO> collect = menuList.stream().filter(e -> Objects.isNull(e.getParentMenuId())).collect(Collectors.toList());
		for (int i = 0; i < menuList.size(); i++) {
			dfs(menuList,i,visited);
		}
		//返回首节点
		return collect;
	}

	private void dfs(List<MenuDTO> menuList, int i, boolean[] visited) {

		if(i >= menuList.size()) {
			return;
		}
		MenuDTO menuDTO = menuList.get(i);
		visited[i] = true;
		List<MenuDTO> children = new ArrayList<>();
		//找当前菜单的所有子菜单
		for (int i1 = 0,len = menuList.size(); i1 < len; i1++) {
			//跳过已访问的节点和本节点
			if(visited[i1]) {
				continue;
			}
			//找到了一个子菜单
			if(Objects.equals(menuList.get(i1).getParentMenuId(),menuDTO.getId())) {
				//添加子菜单
				children.add(menuList.get(i1));
			}
		}
		menuDTO.setChildrenMenu(children);
		visited[i] = false;
	}

	private Specification<Menu> getMenuSpecification(MenuDTO menuDTO) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if(Objects.nonNull(menuDTO.getRoleId())) {
				Path<Object> objectPath = root.joinSet("roleList").get("id");

				predicates.add(criteriaBuilder.equal(objectPath,menuDTO.getRoleId()));
			}

			query.orderBy(criteriaBuilder.asc(root.get("menuOrder")));
			query.where(predicates.toArray(new Predicate[predicates.size()]));
			return null;
		};
	}
}
