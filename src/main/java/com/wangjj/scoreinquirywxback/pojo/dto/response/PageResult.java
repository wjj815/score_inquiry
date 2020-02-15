package com.wangjj.scoreinquirywxback.pojo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @ClassName : PageResult
 * @Author : wangJJ
 * @Date : 2020/2/9 23:24
 * @Description : 分页结果类
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
public class PageResult<T> {


	private int totalElements;
	private int totalPages;
	private boolean last;
	private int number;
	private int size;
	private int numberOfElements;
	private boolean first;
	private boolean empty;
	private List<T> content;

}
