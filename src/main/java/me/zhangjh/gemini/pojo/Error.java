package me.zhangjh.gemini.pojo;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:25 2023/12/28
 * @Description
 */
@Data
public class Error {

    private Integer code;

    private String message;

    private String status;
}
