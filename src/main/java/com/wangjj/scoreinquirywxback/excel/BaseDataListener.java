package com.wangjj.scoreinquirywxback.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : BaseDataListener
 * @Author : wangJJ
 * @Date : 2020/2/6 17:50
 * @Description : TODO
 */
@Slf4j
@NoArgsConstructor
public abstract class BaseDataListener<T> extends AnalysisEventListener<T> {


	/*每隔100条存储数据库，实际使用可以3000条然后清理list，方便回收*/
	private static final int BATCH_COUNT = 100;
	List<T> list = new ArrayList<>();

	protected JpaRepository<T,Long> jpaRepository;
	private StringBuffer stringBuffer;
	private int count = 0;
	private int failedCount = 0;


	public BaseDataListener(JpaRepository<T,Long> jpaRepository) {
		this.jpaRepository = jpaRepository;
		stringBuffer = new StringBuffer();
	}


	@Override
	public void invoke(T data, AnalysisContext context) {
		count++;
		log.info("解析到一条数据：{}", JSON.toJSONString(data));
		wrapperData(data);
		list.add(data);
		if(list.size() >= BATCH_COUNT) {
			saveData();
			//存储完成清理list
			list.clear();
		}
	}

	protected abstract void wrapperData(T data);


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

	public void otherSave(List<T> list){

	};
	/**
	 * 加上存储数据库
	 */
	@Transactional
	public void saveData() {
		log.info("{} 条数据，开始存储数据库！", list.size());
		jpaRepository.saveAll(list);
		otherSave(list);
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

	public String getResult() {
		return stringBuffer.toString();
	}
}
