package com.wangjj.scoreinquirywxback.controller;

import com.wangjj.scoreinquirywxback.pojo.dto.ParentDTO;
import com.wangjj.scoreinquirywxback.pojo.dto.request.PageParameter;
import com.wangjj.scoreinquirywxback.pojo.dto.response.APIResultBean;
import com.wangjj.scoreinquirywxback.pojo.dto.response.PageResult;
import com.wangjj.scoreinquirywxback.service.ParentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName : ParentController
 * @Author : wangJJ
 * @Date : 2020/2/20 14:24
 * @Description : 关于家长请求处理器
 */
@Api(description = "家长类")
@RestController
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	private ParentService parentService;

	@PostMapping
	@ApiOperation("保存家长信息")
	public APIResultBean saveParent(@RequestBody ParentDTO parentDTO) {
		parentService.saveParent(parentDTO);
		return APIResultBean.ok("操作成功！").build();
	}

	@GetMapping("/page")
	@ApiOperation(value = "分页查询家长信息")
	public APIResultBean findParentPage(PageParameter pageParameter,ParentDTO parentDTO) {
		PageResult<ParentDTO> parentPage = parentService.findParentPage(parentDTO, PageRequest.of(pageParameter.getPage() - 1, pageParameter.getLimit()));
		return APIResultBean.ok(parentPage).build();
	}
	@GetMapping("/list")
	@ApiOperation(value = "分页查询家长信息")
	public APIResultBean findParentList(ParentDTO parentDTO) {
		List<ParentDTO> parentList = parentService.findParentList(parentDTO);
		return APIResultBean.ok(parentList).build();
	}

	@GetMapping("{parentId}")
	@ApiOperation(value = "根据id获得家长信息")
	public APIResultBean getParent(@PathVariable Long parentId){

		ParentDTO parent = parentService.findParentById(parentId);
		return APIResultBean.ok(parent).build();
	}

}
