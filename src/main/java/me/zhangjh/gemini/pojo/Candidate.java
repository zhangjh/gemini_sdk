package me.zhangjh.gemini.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 20:13 2023/12/22
 * @Description
 */
@Data
public class Candidate {

    private Content content;

    private String finishReason;

    private Integer index;

    private List<SafetyRating> safetyRatings;
}
