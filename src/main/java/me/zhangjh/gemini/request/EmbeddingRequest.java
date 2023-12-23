package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import me.zhangjh.gemini.pojo.Content;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:35 2023/12/22
 * @Description
 */
@Data
public class EmbeddingRequest extends BaseRequest{

    private Content content;

    @JSONField(serialize = false)
    private String urlPath = "/models/embedding-001:embedContent";

}
