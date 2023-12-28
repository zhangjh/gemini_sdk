# gemini_java
[官方API文档地址](https://ai.google.dev/docs/gemini_api_overview?hl=zh-cn)

![img](https://github.com/zhangjh/gemini_sdk/assets/3371714/a6e7e799-b13d-4b54-be16-5ff5128c9407)

Java已经这么没有排面了吗。。Gemini的API竟然没有Java的，我来封装一个吧。

注意：
**访问gemini国内需要科学上网工具，需美国IP**

使用方式：

0. 需要将配置文件application.properties中API_KEY配置成你自己的
1. 懒得上传maven中央仓库了，非常费劲
2. 下载release目录下的gemini.sdk-1.0.jar，或者下载源码自行编译也行
3. jar包的三元组坐标，以maven为例：
   ```maven
    <groupId>me.zhangjh</groupId>
    <artifactId>gemini.sdk</artifactId>
    <version>1.0</version>
   ```
4. 使用方式

   - 问答类的接口
   ```java
    @Autowired
    private GeminiService geminiService;
   ```
   
   - 向量嵌入接口
   ```java
    @Autowired
    private EmbeddingService embeddingService;
   ```
   
   - 简单文本生成接口
   ```java
   TextResponse generateByText(String text);
   ```
   - 简单多模态接口
   ```java
   VisionResponse generateByMix(String text, String image, String mimeType);
   ```
   - 多轮对话接口（gemini仅支持文本）
   ```java
    TextResponse multiTurnChat(MultiTurnRequest request);
   ```
   - 简单多轮对话接口
   ```java
   String multiTurnChat(String question, List<ChatContent> context);
   ```
   - 流式接口
   ```java
    void steamChat(StreamRequest request, Function<String, Void> cb);
   ```
   - 简单流式接口
   ```java
   void streamChat(String question, List<ChatContent> context, Function<String, Void> cb);
   ```
   流式接口需要传入一个回调函数，回调函数接收一个字符串参数，表示gemini返回的文本。
5. 其他接口不多说了，有需要的可以自行查看源码。
6. 更具体的使用方式，可以查看单测代码：
   ```src/test/java/me/zhangjh/gemini/GeminiTest.java```