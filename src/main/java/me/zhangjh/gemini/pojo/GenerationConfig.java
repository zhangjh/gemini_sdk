package me.zhangjh.gemini.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 22:15 2023/12/23
 * @Description
 */
@Data
public class GenerationConfig {

    private List<String> stopSequences;

    private double temperature = 1.0;

    private int maxOutputTokens = 800;

    private double topP = 0.8;

    private double topK = 10;
}
