package me.zhangjh.gemini.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:10 2023/12/22
 * @Description
 */
@Data
public class Embedding {
    private List<Double> values;
}
