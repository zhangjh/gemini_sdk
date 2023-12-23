package me.zhangjh.gemini.service;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.gemini.client.EmbeddingService;
import me.zhangjh.gemini.pojo.Content;
import me.zhangjh.gemini.request.EmbeddingRequest;
import me.zhangjh.gemini.request.HttpRequest;
import me.zhangjh.gemini.response.EmbeddingResponse;
import me.zhangjh.gemini.util.HttpClientUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:09 2023/12/22
 * @Description
 */
@Slf4j
@Component
public class EmbeddingServiceImpl implements EmbeddingService {

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${URL_BASE}")
    private String urlBase;

    @Override
    public EmbeddingResponse embedding(EmbeddingRequest request) {
        Content content = request.getContent();
        Assert.isTrue(content != null, "request content empty");
        HttpRequest httpRequest = new HttpRequest(urlBase + "/" + request.getVersion()
                + request.getUrlPath() + "?key=" + apiKey);
        httpRequest.setReqData(JSONObject.toJSONString(request));
        String res = HttpClientUtil.sendSync(httpRequest);
        Assert.isTrue(StringUtils.isNotEmpty(res), "empty res returned");
        return JSONObject.parseObject(res, EmbeddingResponse.class);
    }
}
