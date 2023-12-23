package me.zhangjh.gemini.common;

import lombok.Getter;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:03 2023/12/22
 * @Description
 */
@Getter
public enum SafetyEnum {

    BLOCK_NONE("BLOCK_NONE", "始终显示（无论是否存在不安全内容的概率）"),
    BLOCK_ONLY_HIGH("BLOCK_ONLY_HIGH", "在存在高风险的不安全内容时屏蔽"),
    BLOCK_MEDIUM_AND_ABOVE("BLOCK_MEDIUM_AND_ABOVE", "出现中等或高概率的不安全内容时屏蔽"),
    BLOCK_LOW_AND_ABOVE("BLOCK_MEDIUM_AND_ABOVE", "在存在不安全内容的几率较低、中等或较高时屏蔽"),
    HARM_BLOCK_THRESHOLD_UNSPECIFIED("HARM_BLOCK_THRESHOLD_UNSPECIFIED", "未指定阈值，使用默认阈值进行屏蔽"),
    ;

    private final String code;
    private final String desc;

    SafetyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
