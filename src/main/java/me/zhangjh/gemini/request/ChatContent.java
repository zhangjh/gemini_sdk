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
}
