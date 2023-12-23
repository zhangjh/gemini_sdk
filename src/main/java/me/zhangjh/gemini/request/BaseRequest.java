package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import me.zhangjh.gemini.common.ApiVersionEnum;
import me.zhangjh.gemini.pojo.GenerationConfig;
import me.zhangjh.gemini.pojo.SafetySetting;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:38 2023/12/22
 * @Description
 */
@Data
public class BaseRequest {

    @JSONField(serialize = false)
    private String version = ApiVersionEnum.V1.getCode();

    /**
     * you can set this like this:
     * "safetySettings": [
     *             {
     *                 "category": "HARM_CATEGORY_DANGEROUS_CONTENT",
     *                 "threshold": "BLOCK_ONLY_HIGH"
     *             }
     *         ],
     *  category： @link me.zhangjh.gemini.common.SafetyCategoryEnum
     *  threshold: @link me.zhangjh.gemini.common.SafetyThreshHoldEnum
     * */
    private List<SafetySetting> safetySettings;

    private GenerationConfig generationConfig;
}
