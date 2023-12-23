package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import me.zhangjh.gemini.common.ApiVersionEnum;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:38 2023/12/22
 * @Description
 */
@Data
public class BaseRequest {

    @JSONField(serialize = false)
    private String version = ApiVersionEnum.V1.getCode();
}
