package me.zhangjh.gemini.common;

import lombok.Getter;

/**
 * @author njhxzhangjihong@126.com
 * @date 19:53 2023/12/22
 * @Description
 */
@Getter
public enum ApiVersionEnum {

    /**
     * @link {https://ai.google.dev/docs/api_versions?hl=zh-cn}
     * */

    V1("v1"),
    V1_BETA("v1beta"),
    ;

    private final String code;

    ApiVersionEnum(String code) {
        this.code = code;
    }
}
