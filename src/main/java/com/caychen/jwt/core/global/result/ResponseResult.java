package com.caychen.jwt.core.global.result;

import com.caychen.jwt.core.error.GlobalCode;
import com.caychen.jwt.core.serializer.ResponseSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Data
@NoArgsConstructor
@JsonSerialize(using = ResponseSerializer.class)
@ApiModel(description = "RestApi返回统一数据结构体")
public class ResponseResult<T> {

    @ApiModelProperty(value = "业务码：000000表示操作成功，其他返回请参考业务码列表", name = "code", dataType = "String")
    private String code;

    @ApiModelProperty(value = "返回消息")
    private String description;

    private T data;

    public ResponseResult(T data) {
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        return result(data, GlobalCode.SUCCESS);
    }

    public static ResponseResult success() {
        return success(null);
    }

    public static ResponseResult error() {
        return ResponseResult.error(GlobalCode.SYSTEM_INTERNAL_ERROR);
    }

    public static ResponseResult error(GlobalCode code) {
        ResponseResult response = new ResponseResult(null);
        response.code = code.getCode();
        response.description = code.getDesc();
        return response;
    }

    public static ResponseResult error(GlobalCode code, String extraMessage) {
        ResponseResult response = new ResponseResult(null);
        response.code = code.getCode();
        response.description = code.getDesc();

        if(StringUtils.isNotBlank(extraMessage)){
            response.description += ": " + extraMessage;
        }
        return response;
    }

    public static <T> ResponseResult<T> result(T data, GlobalCode code) {
        ResponseResult<T> response = new ResponseResult<>();
        response.setCode(code.getCode());
        response.setData(data);
        response.setDescription(code.getDesc());
        return response;
    }

    public static <T> ResponseResult<T> result(T data, String code, String msg) {
        ResponseResult<T> response = new ResponseResult<>();
        response.setCode(code);
        response.setData(data);
        response.setDescription(msg);
        return response;
    }
}
