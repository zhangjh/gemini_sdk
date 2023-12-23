package me.zhangjh.gemini.response;

import lombok.Data;
import me.zhangjh.gemini.pojo.Candidate;
import me.zhangjh.gemini.pojo.PromptFeedback;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:12 2023/12/22
 * @Description
 */
@Data
public class TextResponse {

    private List<Candidate> candidates;

    private PromptFeedback promptFeedback;
}
