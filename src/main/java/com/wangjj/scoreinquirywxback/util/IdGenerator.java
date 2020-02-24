package com.wangjj.scoreinquirywxback.util;

/**
 * @ClassName : IdGenerator
 * @Author : wangJJ
 * @Date : 2020/1/30 21:29
 * @Description : id生成器
 */
public class IdGenerator {

	//count XXXX
	private static int count = 0;

	public static Long generateId(){
		String id = System.currentTimeMillis()/100 + "";
		id = id + getValue();
		return Long.parseLong(id);
	}

	private static synchronized String getValue(){
		count++;
		if ( count < 10){
			return "000" + count;
		}else if ( count < 100 ){
			return "00" + count;
		}else if ( count < 1000 ){
			return "0" + count;
		}else if ( count < 10000 ){
			return "" + count;
		}else {
			count = 0;
			return "0000";
		}
	}
}
