package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.zhangjh.gemini.pojo.Content;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:35 2023/12/22
 * @Description
 */
@ToString(callSuper = true)
@Getter
@Setter
public class EmbeddingRequest extends BaseRequest{

    private Content content;

    @JSONField(serialize = false)
    private String urlPath = "/models/embedding-001:embedContent";

}
