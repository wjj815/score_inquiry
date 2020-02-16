package com.wangjj.scoreinquirywxback.util;

/**
 * @ClassName : Transformable
 * @Author : wangJJ
 * @Date : 2020/2/16 12:03
 * @Description : TODO
 */
@FunctionalInterface
public interface Transformable<T1,T2> {

	T2 transform(T1 u);
}