package me.zhangjh.gemini.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:14 2023/12/22
 * @Description
 */
@Data
public class PromptFeedback {

    private List<SafetyRating> safetyRatings;
}
