package me.zhangjh.gemini;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.gemini.client.GeminiService;
import me.zhangjh.gemini.pojo.Content;
import me.zhangjh.gemini.pojo.InlineData;
import me.zhangjh.gemini.pojo.Part;
import me.zhangjh.gemini.request.MixRequest;
import me.zhangjh.gemini.request.TextRequest;
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

    @Test
    public void textOnly() {
        System.out.println(1);
        TextRequest textRequest = new TextRequest();
        List<Content> contents = new ArrayList<>();
        Content content = new Content();
        Part part = new Part();
        part.setText("你好");
        content.setParts(List.of(part));
        contents.add(content);
        textRequest.setContents(contents);
        TextResponse textResponse = geminiService.generateByText(textRequest);
        System.out.println(textResponse.getCandidates());
        System.out.println(textResponse.getPromptFeedback());
    }

    @Test
    public void vision() throws Exception {
        MixRequest mixRequest = new MixRequest();
        List<Content> contents = new ArrayList<>();
        Content content = new Content();
        Part part = new Part();
//        part.setText("这幅图说了什么？");
        InlineData inlineData = new InlineData();
        inlineData.setMimeType("image/jpeg");
        Path img = Paths.get("img.png");
        // 获取该图片文件的base64编码值
        String base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(img));
        inlineData.setData(base64);
        part.setInlineData(inlineData);
        content.setParts(List.of(part));
        contents.add(content);
        mixRequest.setContents(contents);
        log.info("mixRequest: {}", mixRequest);
        VisionResponse visionResponse = geminiService.generateByMix(mixRequest);
        System.out.println(JSONObject.toJSONString(visionResponse));
    }
}
