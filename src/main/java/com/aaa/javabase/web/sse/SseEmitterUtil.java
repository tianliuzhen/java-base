package com.aaa.javabase.web.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzhen.tian
 * @version 1.0 SseEmitterUtil.java  2024/8/5 22:29
 */
@Slf4j
public class SseEmitterUtil {
    private static final Map<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();


    public static SseEmitter getConn(@NotBlank String clientId) {
        final SseEmitter sseEmitter = SSE_CACHE.get(clientId);

        if (sseEmitter != null) {
            return sseEmitter;
        } else {
            // 设置连接超时时间，需要配合配置项 spring.mvc.async.request-timeout: 600000 一起使用
            final SseEmitter emitter = new SseEmitter(600_000L);
            // 注册超时回调，超时后触发
            emitter.onTimeout(() -> {
                log.info("连接已超时，正准备关闭，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
            });
            // 注册完成回调，调用 emitter.complete() 触发
            emitter.onCompletion(() -> {
                log.info("连接已关闭，正准备释放，clientId = {}", clientId);
                SSE_CACHE.remove(clientId);
                log.info("连接已释放，clientId = {}", clientId);
            });
            // 注册异常回调，调用 emitter.completeWithError() 触发
            emitter.onError(throwable -> {
                log.error("连接已异常，正准备关闭，clientId = {}", clientId, throwable);
                SSE_CACHE.remove(clientId);
            });

            SSE_CACHE.put(clientId, emitter);

            return emitter;
        }
    }

    /**
     * 模拟类似于 chatGPT 的流式推送回答
     *
     * @param clientId 客户端 id
     * @throws IOException 异常
     */
    public static void send(String clientId) throws IOException {
        final SseEmitter emitter = SSE_CACHE.get(clientId);
        // 推流内容到客户端
        emitter.send("《卧梅》", org.springframework.http.MediaType.APPLICATION_JSON);
        emitter.send("韩寒");
        emitter.send("暗梅幽闻花");
        emitter.send("岸枝伤恨第");
        emitter.send("遥闻卧似水");
        emitter.send("易透达春绿");
        emitter.send("岸似绿");
        emitter.send("岸似透绿");
        emitter.send("岸似透黛绿");
        // 结束推流
        emitter.complete();
    }

    public static void closeConn(@NotBlank String clientId) {
        final SseEmitter sseEmitter = SSE_CACHE.get(clientId);
        if (sseEmitter != null) {
            sseEmitter.complete();
        }
    }

}
