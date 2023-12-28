package me.zhangjh.gemini.service;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.gemini.client.GeminiService;
import me.zhangjh.gemini.common.MimeTypeEnum;
import me.zhangjh.gemini.common.RoleEnum;
import me.zhangjh.gemini.pojo.*;
import me.zhangjh.gemini.request.*;
import me.zhangjh.gemini.response.TextResponse;
import me.zhangjh.gemini.response.VisionResponse;
import me.zhangjh.gemini.util.HttpClientUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:08 2023/12/22
 * @Description
 */
@Slf4j
@Component
public class GeminiServiceImpl implements GeminiService {

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${URL_BASE}")
    private String urlBase;

    @Override
    public TextResponse generateByText(TextRequest request) {
        List<ChatContent> contents = request.getContents();
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
        // mime check
        for (Content content : contents) {
            for (Part part : content.getParts()) {
                if(part instanceof ImagePart imagePart) {
                    MimeTypeEnum.getByCode(imagePart.getInlineData().getMimeType());
                }
            }
        }
        HttpRequest httpRequest = new HttpRequest(
                urlBase + "/" + request.getVersion() + request.getUrlPath() + "?key=" + apiKey);
        httpRequest.setReqData(JSONObject.toJSONString(request));
        String res = HttpClientUtil.sendSync(httpRequest);
        log.info("generateByMix: {}", res);
        Assert.isTrue(StringUtils.isNotEmpty(res), "empty res returned");
        return JSONObject.parseObject(res, VisionResponse.class);
    }

    @Override
    public TextResponse generateByText(String text) {
        Assert.isTrue(StringUtils.isNotEmpty(text), "text input empty");
        TextRequest textRequest = new TextRequest();
        List<ChatContent> contents = new ArrayList<>();
        ChatContent content = ChatContent.buildBySingleText(text);
        contents.add(content);
        textRequest.setContents(contents);
        return this.generateByText(textRequest);
    }

    @Override
    public VisionResponse generateByMix(String text, String image, String mimeType) {
        Assert.isTrue(StringUtils.isNotEmpty(text), "text input empty");
        Assert.isTrue(StringUtils.isNotEmpty(image), "image base64 content empty");
        Assert.isTrue(StringUtils.isNotEmpty(mimeType), "image mimeType empty");
        MimeTypeEnum.getByCode(mimeType);
        MixRequest mixRequest = new MixRequest();
        List<Content> contents = new ArrayList<>();
        Content content = new Content();
        List<Part> parts = new ArrayList<>();
        TextPart textPart = new TextPart(text);
        parts.add(textPart);
        ImagePart imagePart = new ImagePart();
        InlineData inlineData = new InlineData(mimeType, image);
        imagePart.setInlineData(inlineData);
        parts.add(imagePart);
        content.setParts(parts);
        contents.add(content);
        mixRequest.setContents(contents);
        return this.generateByMix(mixRequest);
    }

    @Override
    public TextResponse multiTurnChat(MultiTurnRequest request) {
        List<ChatContent> contents = request.getContents();
        Assert.isTrue(CollectionUtils.isNotEmpty(contents), "request empty");
        HttpRequest httpRequest = new HttpRequest(urlBase + "/" + request.getVersion()
                + request.getUrlPath() + "?key=" + apiKey);
        httpRequest.setReqData(JSONObject.toJSONString(request));
        String res = HttpClientUtil.sendSync(httpRequest);
        Assert.isTrue(StringUtils.isNotEmpty(res), "empty res returned");
        return JSONObject.parseObject(res, TextResponse.class);
    }

    @Override
    public String multiTurnChat(String question, List<ChatContent> context) {
        MultiTurnRequest multiTurnRequest = new MultiTurnRequest();
        List<ChatContent> contents = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(context)) {
            contents.addAll(context);
        }
        ChatContent chatContent = new ChatContent();
        chatContent.setRole(RoleEnum.user.name());
        chatContent.setParts(List.of(new TextPart(question)));
        contents.add(chatContent);
        multiTurnRequest.setContents(contents);
        TextResponse textResponse = this.multiTurnChat(multiTurnRequest);
        if (CollectionUtils.isEmpty(textResponse.getCandidates())) {
            return null;
        }
        List<String> res = new ArrayList<>();
        for (Candidate candidate : textResponse.getCandidates()) {
            for (Part part : candidate.getContent().getParts()) {
                res.add(part.getText());
            }
        }
        return String.join("", res);
    }

    @Override
    public void streamChat(StreamRequest<ChatContent> request, Function<String, Void> cb) {
        List<ChatContent> contents = request.getContents();
        Assert.isTrue(CollectionUtils.isNotEmpty(contents), "contents empty");
        HttpRequest httpRequest = new HttpRequest(urlBase + "/" + request.getVersion()
                + request.getUrlPath() + "?key=" + apiKey);
        httpRequest.setReqData(JSONObject.toJSONString(request));
        HttpClientUtil.sendStream(httpRequest, cb);
    }

    @Override
    public void streamChat(String question, List<ChatContent> context, Function<String, Void> cb) {
        Assert.isTrue(StringUtils.isNotEmpty(question), "question empty");
        StreamRequest<ChatContent> streamRequest = new StreamRequest<>();
        ChatContent chatContent = new ChatContent();
        List<ChatContent> contents = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(context)) {
            contents.addAll(context);
        }
        chatContent.setRole(RoleEnum.user.name());
        chatContent.setParts(List.of(new TextPart(question)));
        contents.add(chatContent);
        streamRequest.setContents(contents);
        this.streamChat(streamRequest, cb);
    }
}
