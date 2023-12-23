package me.zhangjh.gemini;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.gemini.client.EmbeddingService;
import me.zhangjh.gemini.client.GeminiService;
import me.zhangjh.gemini.common.RoleEnum;
import me.zhangjh.gemini.pojo.*;
import me.zhangjh.gemini.request.*;
import me.zhangjh.gemini.response.EmbeddingResponse;
import me.zhangjh.gemini.response.TextResponse;
import me.zhangjh.gemini.response.VisionResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:29 2023/12/22
 * @Description
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GeminiTest {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private EmbeddingService embeddingService;

    @Test
    public void textOnly() {
        TextRequest textRequest = new TextRequest();
        List<Content> contents = new ArrayList<>();
        Content content = new Content();
        List<Part> parts = new ArrayList<>();
        TextPart textPart = new TextPart("你好");
        parts.add(textPart);
        content.setParts(parts);
        contents.add(content);
        textRequest.setContents(contents);
        TextResponse textResponse = geminiService.generateByText(textRequest);
        log.info("textResponse: {}", JSONObject.toJSONString(textResponse));
    }

    @Test
    public void vision() throws Exception {
        MixRequest mixRequest = new MixRequest();
        List<Content> contents = new ArrayList<>();
        Content content = new Content();
        List<Part> parts = new ArrayList<>();
        TextPart textPart = new TextPart("这幅图说了什么？");
        parts.add(textPart);
        ImagePart imagePart = new ImagePart();
        // 获取该图片文件的base64编码值
        Path img = Paths.get("img.png");
        String base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(img));
        InlineData inlineData = new InlineData("image/jpeg", base64);
        imagePart.setInlineData(inlineData);
        parts.add(imagePart);
        content.setParts(parts);
        contents.add(content);
        mixRequest.setContents(contents);
        VisionResponse visionResponse = geminiService.generateByMix(mixRequest);
        System.out.println(JSONObject.toJSONString(visionResponse));
    }

    @Test
    public void multiTurnChat() {
        MultiTurnRequest multiTurnRequest = new MultiTurnRequest();
        List<ChatContent> contents = new ArrayList<>();
        ChatContent content = new ChatContent();
        content.setRole(RoleEnum.user.name());
        content.setParts(List.of(new TextPart("Write the first line of a story about a magic backpack.")));
        contents.add(content);
        ChatContent content1 = new ChatContent();
        content1.setRole(RoleEnum.model.name());
        content1.setParts(List.of(new TextPart("In the bustling city of Meadow brook, lived a young girl named Sophie. She was a bright and curious soul with an imaginative mind.")));
        contents.add(content1);
        ChatContent question = new ChatContent();
        question.setRole(RoleEnum.user.name());
        question.setParts(List.of(new TextPart("Can you set it in a quiet village in 1600s France?")));
        contents.add(question);
        multiTurnRequest.setContents(contents);
        TextResponse multiTurnResponse = geminiService.multiTurnChat(multiTurnRequest);
        log.info("multiTurnResponse: {}", JSONObject.toJSONString(multiTurnResponse));
    }

    @Test
    public void streamChat() {
        StreamRequest streamRequest = new StreamRequest();
        List<Content> contents = new ArrayList<>();
        Content content = new Content();
        List<Part> parts = new ArrayList<>();
        TextPart textPart = new TextPart("Write long a story about a magic backpack.");
        parts.add(textPart);
        content.setParts(parts);
        contents.add(content);
        streamRequest.setContents(contents);
        geminiService.steamChat(streamRequest, null);
    }

    @Test
    public void embeddingTest() {
        EmbeddingRequest embeddingRequest = new EmbeddingRequest();
        Content content = new Content();
        List<Part> parts = new ArrayList<>();
        TextPart textPart = new TextPart("Write long a story about a magic backpack.");
        parts.add(textPart);
        content.setParts(parts);
        embeddingRequest.setContent(content);
        EmbeddingResponse embedding = embeddingService.embedding(embeddingRequest);
        log.info("embedding: {}", JSONObject.toJSONString(embedding));
    }
}
