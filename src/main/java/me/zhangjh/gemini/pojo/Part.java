package me.zhangjh.gemini.pojo;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:18 2023/12/22
 * @Description
 */
@Data
public class Part {

    private String text;

    public Part() {
    }

    public Part(String text) {
        this.text = text;
    }
}
