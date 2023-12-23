package me.zhangjh.gemini.common;

import lombok.Getter;

/**
 * @author njhxzhangjihong@126.com
 * @date 22:47 2023/12/23
 * @Description
 */
@Getter
public enum MimeTypeEnum {
    PNG("image/png"),
    JPEG("image/jpeg"),
    WEBP("image/webp"),
    HEIC("image/heic"),
    HEIF("image/heif"),
    ;

    private String code;

    MimeTypeEnum(String code) {
        this.code = code;
    }

    public static MimeTypeEnum getByCode(String code) {
        for (MimeTypeEnum typeEnum : MimeTypeEnum.values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        throw new RuntimeException("Unsupported mimeType");
    }
}
