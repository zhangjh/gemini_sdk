package me.zhangjh.gemini.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 22:12 2023/12/23
 * @Description
 */
@Data
@AllArgsConstructor
public class SafetySetting {

    private String category;

    private String threshold;
}
