package me.zhangjh.gemini.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:35 2023/12/23
 * @Description
 */
@Data
public class ImagePart extends Part {

    @JSONField(name = "inline_data")
    private InlineData inlineData;
}
