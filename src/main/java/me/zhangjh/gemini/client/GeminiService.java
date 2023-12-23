package me.zhangjh.gemini.client;

import me.zhangjh.gemini.request.MixRequest;
import me.zhangjh.gemini.request.MultiTurnRequest;
import me.zhangjh.gemini.request.TextRequest;
import me.zhangjh.gemini.response.MultiTurnResponse;
import me.zhangjh.gemini.response.StreamResponse;
import me.zhangjh.gemini.response.TextResponse;
import me.zhangjh.gemini.response.VisionResponse;

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
     * conversations across multiple turns
     * @param request
     * @return MultiTurnResponse
     * */
    MultiTurnResponse multiTurnChat(MultiTurnRequest request);

    /**
     * Gemini returns a response after completing the entire generation process as default.
     * You can achieve faster interactions by not waiting for the entire result,
     * and instead use streaming to handle partial results.
     * @param request
     * @return StreamResponse
     * */
    StreamResponse steamChat(TextRequest request);

}
