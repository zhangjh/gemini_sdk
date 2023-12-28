package me.zhangjh.gemini.util;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.gemini.request.HttpRequest;
import me.zhangjh.gemini.response.ErrorResponse;
import okhttp3.*;
import okio.BufferedSource;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author njhxzhangjihong@126.com
 * @date 21:11 2023/12/22
 * @Description
 */
@Slf4j
public class HttpClientUtil {

    protected static final OkHttpClient OK_HTTP_CLIENT;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.callTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.connectionPool(new ConnectionPool(32,
                60,TimeUnit.SECONDS));
        OK_HTTP_CLIENT = builder.build();
    }

    /**
     * curl https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=$API_KEY \
     *     -H 'Content-Type: application/json' \
     *     -X POST \
     *     -d '{
     *       "contents": [{
     *         "parts":[{
     *           "text": "Write a story about a magic backpack."}]}]}' 2> /dev/null
     * */
    public static String sendSync(HttpRequest httpRequest) {
        Request request = buildRequest(httpRequest);
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()){
            return handleResponse(Objects.requireNonNull(response.body()));
        } catch (IOException e) {
            log.error("sendNormally exception, {}, httpRequest: {}, e: {}",
                    e.getMessage(), JSONObject.toJSONString(httpRequest), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * stream request, need handle response in stream
     * */
    public static void sendStream(HttpRequest httpRequest, Function<String, Void> cb) {
        Request request = buildRequest(httpRequest);
        try (Response res = OK_HTTP_CLIENT.newCall(request).execute()) {
            // handle response
            assert res.body() != null;
            try (InputStreamReader inputStreamReader = new InputStreamReader(res.body().byteStream(),
                    StandardCharsets.UTF_8)) {
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    String line;
                    List<String> responseContent = new ArrayList<>();
                    while ((line = bufferedReader.readLine()) != null) {
                        line = line.trim();
                        if(line.startsWith("\"text\":")) {
                            String[] parts = line.split(":");
                            String content = String.join("", Arrays.copyOfRange(parts, 1, parts.length));
                            content = content.replaceAll("^\s?\"", "")
                                        .replaceAll("\s?\"$", "");
                            log.info("content: {}", content);
                            responseContent.add(content);
                            if(cb != null) {
                                cb.apply(content);
                            }
                        }
                    }
                    log.info("response content: {}", String.join("\n", responseContent));
                }
            }
        } catch (Exception e) {
            log.error("sendNormally exception, {}, httpRequest: {}, e: {}",
                    e.getMessage(), JSONObject.toJSONString(httpRequest), e);
            throw new RuntimeException(e);
        }
    }

    private static Request buildRequest(HttpRequest httpRequest) {
        try {
            JSONObject.parseObject(httpRequest.getReqData());
        } catch (Exception e) {
            throw new IllegalArgumentException("reqData isn't json format");
        }
        RequestBody requestBody = RequestBody
                .create(httpRequest.getReqData(), MediaType.get("application/json"));
        Request.Builder builder = new Request.Builder();
        builder.url(httpRequest.getUrl())
                .method(httpRequest.getMethod(), requestBody);
        Map<String, String> headerMap = httpRequest.getBizHeaderMap();
        if(headerMap == null) {
            headerMap = new HashMap<>();
        }
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private static String handleResponse(ResponseBody responseBody) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedSource source = responseBody.source();
            while (!source.exhausted()) {
                String line = source.readUtf8Line();
                if (StringUtils.isNotEmpty(line)) {
                    sb.append(line);
                }
            }
            ErrorResponse error = JSONObject.parseObject(sb.toString(), ErrorResponse.class);
            if(error != null && error.getError() != null) {
                log.error("error response: {}", error);
                throw new RuntimeException(error.getError().getMessage());
            }
        } catch (Exception e) {
            log.error("handleResponse exception: {}, e: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
