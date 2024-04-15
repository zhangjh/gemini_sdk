package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.zhangjh.gemini.pojo.Content;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:31 2023/12/22
 * @Description
 */
@ToString(callSuper = true)
@Getter
@Setter
public class MixRequest extends BaseRequest {

    private List<Content> contents;

    @JSONField(serialize = false)
    private String urlPath = this.getVisionModelName() + ":generateContent";
}
