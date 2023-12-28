package me.zhangjh.gemini.client;

import me.zhangjh.gemini.request.EmbeddingRequest;
import me.zhangjh.gemini.response.EmbeddingResponse;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:34 2023/12/22
 * @Description
 */
public interface EmbeddingService {

    /**
     * embedding content
     * @param request
     * @return EmbeddingResponse
     * */
    EmbeddingResponse embedding(EmbeddingRequest request);

}
