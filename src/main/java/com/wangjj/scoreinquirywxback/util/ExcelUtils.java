package com.wangjj.scoreinquirywxback.util;

import com.wangjj.scoreinquirywxback.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName : ExcelUtils
 * @Author : wangJJ
 * @Date : 2020/2/6 17:44
 * @Description : TODO
 */
@Slf4j
public class ExcelUtils {

	public static void check( MultipartFile multipartFile) {
		log.info("导入文件名:{}",multipartFile.getOriginalFilename());
		if(StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
			throw new GlobalException("上传文件异常");
		}

		if(!(multipartFile.getOriginalFilename().endsWith(".xls")||
				multipartFile.getOriginalFilename().endsWith(".xlsx"))){
			throw new GlobalException("导入文件格式错误！");
		}
	}
}
