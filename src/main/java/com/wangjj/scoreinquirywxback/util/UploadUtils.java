package com.wangjj.scoreinquirywxback.util;

import com.wangjj.scoreinquirywxback.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName : UploadUtils
 * @Author : wangJJ
 * @Date : 2020/1/25 18:20
 * @Description : 上传文件的工具类
 */
@Slf4j
public class UploadUtils {

	public static final String path = "/upload/";

	/**
	 * 上传文件
	 * @param multipartFile 文件
	 * @return
	 */
	public static File upload(MultipartFile multipartFile) {
		log.info("开始上传文件：");
		log.info("名称：{}",multipartFile.getOriginalFilename());
		log.info("类型：{}", multipartFile.getContentType());
		File f  = new File(path + multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(f);
		} catch (IOException e) {
			log.error("文件上传异常");
			e.printStackTrace();
			throw new GlobalException("文件上传异常");
		}
		log.info("上传完成");
		return f;
	}
}
