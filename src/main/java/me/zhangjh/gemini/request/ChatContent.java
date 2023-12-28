package me.zhangjh.gemini.request;

import lombok.Data;
import me.zhangjh.gemini.pojo.TextPart;

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
        content.setParts(List.of(new TextPart(text)));
        return content;
    }

    public static ChatContent buildBySingleText(String text, String role) {
        ChatContent content = new ChatContent();
        content.setRole(role);
        content.setParts(List.of(new TextPart(text)));
        return content;
    }
}
