package com.wangjj.scoreinquirywxback.vo.response;

import com.wangjj.scoreinquirywxback.constant.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @ClassName : APIResultBean
 * @Author : wangJJ
 * @Date : 2019/12/25 17:16
 * @Description : 封装api返回结果
 */
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "api返回参数")
public class APIResultBean<T> {

    /**
     * 编码
     */
    @ApiModelProperty(value = "响应码(0表示成功,1表示失败)", example = "0")
    private String resultCode;
    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志", example = "true")
    private Boolean success;
    /**
     * 返回消息
     */
    @ApiModelProperty(value = "返回消息说明", example = "操作成功")
    private String resultMsg;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private T data;

    public static APIResultBean.APIResultBeanBuilder ok() {
        return builder().success(true).resultCode("0");
    }

    public static APIResultBean.APIResultBeanBuilder ok(Object data) {
        return ok().data(data);
    }
    public static APIResultBean.APIResultBeanBuilder ok(String resultMsg) {
        return ok(resultMsg,null);
    }

    public static APIResultBean.APIResultBeanBuilder ok(String resultMsg,Object data ) {
        return ok(data).resultMsg(resultMsg);
    }

    public static APIResultBean.APIResultBeanBuilder error() {
        return builder().success(false);
    }
    public static APIResultBean.APIResultBeanBuilder error(String resultMsg) {
        return error().resultMsg(resultMsg).resultCode(ResultCode.ERROR);
    }

    public static APIResultBean.APIResultBeanBuilder error(String resultCode,String resultMsg) {
        return error().resultMsg(resultMsg).resultCode(resultCode);
    }




    public APIResultBean(String resultCode, String resultMsg, T data){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
    public APIResultBean(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = null;
    }

}
