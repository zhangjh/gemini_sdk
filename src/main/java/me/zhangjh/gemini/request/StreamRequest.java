package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:21 2023/12/23
 * @Description
 */
@ToString(callSuper = true)
@Getter
@Setter
public class StreamRequest<T> extends BaseRequest {

    /**
     * T can be content or ChatContent (for multiTurn)
     */
    private List<T> contents;

    @JSONField(serialize = false)
    private String urlPath = this.getModelName() + ":streamGenerateContent";
}
