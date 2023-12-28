package me.zhangjh.gemini.common;

import lombok.Getter;

/**
 * @author njhxzhangjihong@126.com
 * @date 22:18 2023/12/23
 * @Description
 */
@Getter
public enum SafetyCategoryEnum {
    HARM_CATEGORY_UNSPECIFIED("HARM_CATEGORY_UNSPECIFIED", "未指定类别"),
    HARM_CATEGORY_DEROGATORY("HARM_CATEGORY_DEROGATORY", "针对身份和/或受保护特征发表的负面或有害评论"),
    HARM_CATEGORY_TOXICITY("HARM_CATEGORY_TOXICITY", "粗俗、不雅或亵渎性的内容"),
    HARM_CATEGORY_VIOLENCE("HARM_CATEGORY_VIOLENCE", "描述针对个人或群体的暴力行为的场景，或对血腥内容的一般描述"),
    HARM_CATEGORY_SEXUAL("HARM_CATEGORY_SEXUAL", "包含对性行为或其他淫秽内容的引用"),
    HARM_CATEGORY_MEDICAL("HARM_CATEGORY_MEDICAL", "宣传未经证实的医疗建议"),
    HARM_CATEGORY_DANGEROUS("HARM_CATEGORY_DANGEROUS", "宣扬、助长或鼓励有害行为的危险内容"),
    HARM_CATEGORY_HARASSMENT("HARM_CATEGORY_HARASSMENT", "骚扰内容"),
    HARM_CATEGORY_HATE_SPEECH("HARM_CATEGORY_HATE_SPEECH", "仇恨言论和内容"),
    HARM_CATEGORY_SEXUALLY_EXPLICIT("HARM_CATEGORY_SEXUALLY_EXPLICIT", "露骨色情内容"),
    HARM_CATEGORY_DANGEROUS_CONTENT("HARM_CATEGORY_DANGEROUS_CONTENT", "危险内容")
    ;

    private final String code;

    private final String desc;

    SafetyCategoryEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
