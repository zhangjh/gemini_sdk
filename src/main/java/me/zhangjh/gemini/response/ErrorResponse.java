package me.zhangjh.gemini.response;

import lombok.Data;
import me.zhangjh.gemini.pojo.Error;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:24 2023/12/28
 * @Description
 */
@Data
public class ErrorResponse {

    private Error error;

}
