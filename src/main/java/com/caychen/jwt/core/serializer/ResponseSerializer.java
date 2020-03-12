package com.caychen.jwt.core.serializer;

import com.caychen.jwt.core.global.result.PageResult;
import com.caychen.jwt.core.global.result.ResponseResult;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
public class ResponseSerializer extends JsonSerializer<ResponseResult> {

    @Override
    public void serialize(ResponseResult value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("code", value.getCode());
        gen.writeStringField("description", value.getDescription());
        Object data = value.getData();
        if (data instanceof PageResult) {
            PageResult pageResult = (PageResult) data;
            gen.writeObjectFieldStart("data");
            gen.writeObjectField("pageIndex", pageResult.getPageIndex());
            gen.writeObjectField("totalCount", pageResult.getTotalCount());
            gen.writeObjectField("pageSize", pageResult.getPageSize());
            gen.writeObjectField("pageData", pageResult.getPageData());
            gen.writeEndObject();
        } else {
            if (data != null) {
                gen.writeFieldName("data");
                serializers.defaultSerializeValue(data, gen);
            }
        }
        gen.writeEndObject();
    }
}
