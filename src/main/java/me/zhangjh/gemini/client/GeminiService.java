package me.zhangjh.gemini.client;

import me.zhangjh.gemini.request.MixRequest;
import me.zhangjh.gemini.request.MultiTurnRequest;
import me.zhangjh.gemini.request.StreamRequest;
import me.zhangjh.gemini.request.TextRequest;
import me.zhangjh.gemini.response.TextResponse;
import me.zhangjh.gemini.response.VisionResponse;

import java.util.function.Function;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:11 2023/12/22
 * @Description
 */
public interface GeminiService {

    /**
     * text only
     * @param request
     * @return TextResponse
     * */
    TextResponse generateByText(TextRequest request);

    /**
     * Text-and-image input
     * @param request
     * @return VisionResponse
     * */
    VisionResponse generateByMix(MixRequest request);

    /**
     * simplify interface of generateByText
     * @param text
     * @return TextResponse
     * */
    TextResponse generateByText(String text);

    /**
     * simplify interface of generateByMix
     * @param text
     * @param image format base64 of image
     * @param mimeType such as image/png, @link me.zhangjh.gemini.common.MimeTypeEnum
     * @return VisionResponse
     * */
    VisionResponse generateByMix(String text, String image, String mimeType);

    /**
     * conversations across multiple turns
     * only text input only support now by gemini
     * @param request
     * @return TextResponse
     * */
    TextResponse multiTurnChat(MultiTurnRequest request);

    /**
     * Gemini returns a response after completing the entire generation process as default.
     * You can achieve faster interactions by not waiting for the entire result,
     * and instead use streaming to handle partial results.
     * @param request
     * @param cb callback function which receives a string per stream
     * no response returned, stream response data should be handled by HttpServletResponse
     * */
    void steamChat(StreamRequest request, Function<String, Void> cb);

}
