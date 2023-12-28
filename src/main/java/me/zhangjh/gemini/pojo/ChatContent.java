package me.zhangjh.gemini.pojo;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:00 2023/12/23
 * @Description
 */
@Data
public class ChatContent {

    private String role;

    private List<TextPart> parts;

    /**
     * non multiTurnChat chat doesn't need role
     * */
    public static ChatContent buildBySingleText(String text) {
        ChatContent content = new ChatContent();
        content.setParts(Collections.singletonList(new TextPart(text)));
        return content;
    }

    public static ChatContent buildBySingleText(String text, String role) {
        ChatContent content = new ChatContent();
        content.setRole(role);
        content.setParts(Collections.singletonList(new TextPart(text)));
        return content;
    }
}
