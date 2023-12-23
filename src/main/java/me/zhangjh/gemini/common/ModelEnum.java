package me.zhangjh.gemini.common;

import lombok.Getter;

/**
 * @author njhxzhangjihong@126.com
 * @date 19:56 2023/12/22
 * @Description
 */
@Getter
public enum ModelEnum {
    GEMINI_PRO("models/gemini-pro", "gemini专业版"),
    GEMINI_PRO_VISION("models/gemini-pro-vision", "gemini多模态版本"),
    EMBEDDING_001("models/embedding-001", "嵌入"),
    AQA("models/aqa", "空气质量评估")
    ;

    private final String code;

    private final String name;

    ModelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
