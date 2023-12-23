package me.zhangjh.gemini.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:22 2023/12/22
 * @Description
 */
@Data
public class InlineData {

    /**
     * such as "image/jpeg"
     * */
    @JSONField(name = "mime_type")
    private String mimeType;

    /**
     * image data format as base64
     * */
    private String data;
}
