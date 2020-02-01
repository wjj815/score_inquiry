package com.wangjj.scoreinquirywxback.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.fastjson.JSON;
import com.wangjj.scoreinquirywxback.dao.StudentRepository;
import com.wangjj.scoreinquirywxback.entity.Student;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName : StudentDataListener
 * @Author : wangJJ
 * @Date : 2020/1/23 19:58
 * @Description : easyExcel的数据导入监听
 */
@Slf4j
public class StudentDataListener extends AnalysisEventListener<Student> {


	/*每隔20条存储数据库，实际使用可以3000条然后清理list，方便回收*/
	private static final int BATCH_COUNT = 20;
	List<Student> list = new ArrayList<>();

	private StudentRepository studentRepository;
	private StringBuffer stringBuffer;
	private int count = 0;
	private int failedCount = 0;

	public StudentDataListener(StudentRepository studentRepository) {
		this(new StringBuffer(),studentRepository);
	}

	public StudentDataListener(StringBuffer stringBuffer,StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		this.stringBuffer = stringBuffer;
	}

	@Override
	public void invoke(Student data, AnalysisContext context) {
		count++;
		log.info("解析到一条数据：{}", JSON.toJSONString(data));
		Calendar instance = Calendar.getInstance();
		instance.setTime(data.getBirthday());
//		controllerLog.info("{}当前年份, {} 生日年份， {}相差年份",LocalDate.now().getYear(), );
		data.setAge(LocalDate.now().getYear() - instance.get(Calendar.YEAR));

		list.add(data);
		if(list.size() >= BATCH_COUNT) {
			saveData();
			//存储完成清理list
			list.clear();
		}
	}
	/**
	 * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
	 *
	 * @param exception
	 * @param context
	 * @throws Exception
	 */
	@Override
	public void onException(Exception exception, AnalysisContext context) {
		log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
		failedCount++;
		// 如果是某一个单元格的转换异常 能获取到具体行号
		// 如果要获取头的信息 配合invokeHeadMap使用
		if (exception instanceof ExcelDataConvertException) {
			ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
			log.error("解析异常 {}", excelDataConvertException.getMessage());
		}
	}
	/**
	 * 加上存储数据库
	 */
	private void saveData() {
		log.info("{} 条数据，开始存储数据库！", list.size());
		studentRepository.saveAll(list);
		log.info("存储数据库成功！");
	}


	/**
	 * 所有数据解析完成了 都会用来调用
	 * @param context
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		saveData();
		stringBuffer.append(String.format("共 %d 条数据, %d 条成功, %d 条失败",count,count - failedCount,failedCount));
		log.info("所有数据解析完成！");
	}

}
