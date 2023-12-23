package me.zhangjh.gemini.service;

import com.alibaba.fastjson2.JSONObject;
import me.zhangjh.gemini.client.GeminiService;
import me.zhangjh.gemini.pojo.Content;
import me.zhangjh.gemini.request.HttpRequest;
import me.zhangjh.gemini.request.MixRequest;
import me.zhangjh.gemini.request.MultiTurnRequest;
import me.zhangjh.gemini.request.TextRequest;
import me.zhangjh.gemini.response.MultiTurnResponse;
import me.zhangjh.gemini.response.StreamResponse;
import me.zhangjh.gemini.response.TextResponse;
import me.zhangjh.gemini.response.VisionResponse;
import me.zhangjh.gemini.util.HttpClientUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:08 2023/12/22
 * @Description
 */
@Component
public class GeminiServiceImpl implements GeminiService {

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${URL_BASE}")
    private String urlBase;

    @Override
    public TextResponse generateByText(TextRequest request) {
        List<Content> contents = request.getContents();
        Assert.isTrue(CollectionUtils.isNotEmpty(contents), "Empty contents");
        HttpRequest httpRequest = new HttpRequest(
                urlBase + "/" + request.getVersion() + request.getUrlPath() + "?key=" + apiKey);
        httpRequest.setReqData(JSONObject.toJSONString(request));
        String res = HttpClientUtil.sendSync(httpRequest);
        Assert.isTrue(StringUtils.isNotEmpty(res), "empty res returned");
        return JSONObject.parseObject(res, TextResponse.class);
    }

    @Override
    public VisionResponse generateByMix(MixRequest request) {
        List<Content> contents = request.getContents();
        Assert.isTrue(CollectionUtils.isNotEmpty(contents), "Empty contents");
        HttpRequest httpRequest = new HttpRequest(
                urlBase + "/" + request.getVersion() + request.getUrlPath() + "?key=" + apiKey);
        httpRequest.setReqData(JSONObject.toJSONString(request));
        String res = HttpClientUtil.sendSync(httpRequest);
        Assert.isTrue(StringUtils.isNotEmpty(res), "empty res returned");
        return JSONObject.parseObject(res, VisionResponse.class);
    }

    @Override
    public MultiTurnResponse multiTurnChat(MultiTurnRequest request) {
        return null;
    }

    @Override
    public StreamResponse steamChat(TextRequest request) {
        return null;
    }
}
