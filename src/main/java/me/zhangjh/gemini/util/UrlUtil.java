package me.zhangjh.gemini.util;

/**
 * @author njhxzhangjihong@126.com
 * @date 14:13 2024/4/15
 * @Description
 */
public class UrlUtil {

    public static String getUrl(String urlBase, String version, String path, String key) {
        return urlBase + "/" + version + "/" + path  + "?key=" + key;
    }
}
