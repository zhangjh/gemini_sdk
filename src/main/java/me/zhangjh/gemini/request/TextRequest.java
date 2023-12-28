package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:18 2023/12/22
 * @Description
 */
@Data
public class TextRequest extends BaseRequest {

    private List<ChatContent> contents;

    @JSONField(serialize = false)
    private String urlPath = "/models/gemini-pro:generateContent";
}
