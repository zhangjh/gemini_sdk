package me.zhangjh.gemini.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:18 2023/12/22
 * @Description
 */
@Data
public class Part {

    private String text;

    @JSONField(name = "inline_data")
    private InlineData inlineData;
}
