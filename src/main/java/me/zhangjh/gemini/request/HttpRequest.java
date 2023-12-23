package me.zhangjh.gemini.request;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:18 2023/12/22
 * @Description
 */
@Data
public class HttpRequest {

    @NonNull
    private String url;

    private String version;

    private String method = HttpMethod.POST.name();

    private Map<String, String> bizHeaderMap;

    /**
     * post data body, json format
     * */
    private String reqData;
}
