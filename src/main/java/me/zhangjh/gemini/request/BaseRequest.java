package me.zhangjh.gemini.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import me.zhangjh.gemini.common.ApiVersionEnum;
import me.zhangjh.gemini.common.SafetyCategoryEnum;
import me.zhangjh.gemini.common.SafetyThreshHoldEnum;
import me.zhangjh.gemini.pojo.GenerationConfig;
import me.zhangjh.gemini.pojo.SafetySetting;

import java.util.ArrayList;
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

    public BaseRequest() {
        setDefaultSafetySettings();
        setDefaultGenerationConfig();
    }

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

    /**
     * you can set this like this:
     * "generationConfig": {
     *     "stopSequences": [
     *        "Title"
     *     ],
     *     "temperature": 1.0,
     *     "maxOutputTokens": 800,
     *     "topP": 0.8,
     *     "topK": 10
     * }
     * */
    private GenerationConfig generationConfig;

    /**
     * All category are block_none by default, you can set this yourself.
     * */
    private void setDefaultSafetySettings() {
        if (this.safetySettings == null || this.safetySettings.isEmpty()) {
            List<SafetySetting> safetySettings = new ArrayList<>();
            safetySettings.add(new SafetySetting(SafetyCategoryEnum.HARM_CATEGORY_SEXUALLY_EXPLICIT.getCode(),
                    SafetyThreshHoldEnum.BLOCK_NONE.getCode()));
            safetySettings.add(new SafetySetting(SafetyCategoryEnum.HARM_CATEGORY_HATE_SPEECH.getCode(),
                    SafetyThreshHoldEnum.BLOCK_NONE.getCode()));
            safetySettings.add(new SafetySetting(SafetyCategoryEnum.HARM_CATEGORY_HARASSMENT.getCode(),
                    SafetyThreshHoldEnum.BLOCK_NONE.getCode()));
            safetySettings.add(new SafetySetting(SafetyCategoryEnum.HARM_CATEGORY_DANGEROUS_CONTENT.getCode(),
                    SafetyThreshHoldEnum.BLOCK_NONE.getCode()));
            safetySettings.add(new SafetySetting(SafetyCategoryEnum.HARM_CATEGORY_UNSPECIFIED.getCode(),
                    SafetyThreshHoldEnum.BLOCK_NONE.getCode()));
            this.safetySettings = safetySettings;
        }
    }

    private void setDefaultGenerationConfig() {
        if (this.generationConfig == null) {
            this.generationConfig = new GenerationConfig();
        }
    }
}
