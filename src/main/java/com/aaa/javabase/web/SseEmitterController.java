package com.aaa.javabase.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器端实时推送技术之 SseEmitter 的用法测试
 * <p>
 * 测试步骤:
 * 1.请求http://localhost:8080/sse/start?clientId=111接口,浏览器会阻塞,等待服务器返回结果;
 * 2.请求http://localhost:8080/sse/send?clientId=111接口,可以请求多次,并观察第1步的浏览器返回结果;
 * 3.请求http://localhost:8080/sse/end?clientId=111接口结束某个请求,第1步的浏览器将结束阻塞;
 * 其中clientId代表请求的唯一标志;
 * <p>
 * @author liuzhen.tian
 * @version 1.0 SseEmitterController.java  2024/8/5 21:25
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/sse2")
public class SseEmitterController {

    public class SeeResult {
        public String clientId;
        public long timestamp;
        public SseEmitter sseEmitter;

        public SeeResult(String clientId, long timestamp, SseEmitter sseEmitter) {
            this.clientId = clientId;
            this.timestamp = timestamp;
            this.sseEmitter = sseEmitter;
        }
    }

    public static class SseMap {
        public static final Map<String, SeeResult> sseEmitterMap = new ConcurrentHashMap<>();
    }

    /**
     * 返回SseEmitter对象
     *
     * @param clientId
     * @return
     */
    @GetMapping("/start")
    public SseEmitter testSseEmitter(@RequestParam(defaultValue = "a") String clientId) throws IOException {
        if (StringUtils.isBlank(clientId)) {
            throw new RuntimeException("参数不能为空");
        }
        // 默认30秒超时,设置为0L则永不超时
        SseEmitter sseEmitter = new SseEmitter(1000L * 1000);
        SseMap.sseEmitterMap.put(clientId, new SeeResult(clientId, System.currentTimeMillis(), sseEmitter));
        return sseEmitter;
    }

    /**
     * 向SseEmitter对象发送数据
     *
     * @param clientId
     * @return
     */
    @RequestMapping("/send")
    public String setSseEmitter(@RequestParam(defaultValue = "a") String clientId) {
        try {
            SeeResult result = SseMap.sseEmitterMap.get(clientId);
            if (result != null && result.sseEmitter != null) {
                result.sseEmitter.send(new HashMap<String, Object>() {{
                    put("success", "true");
                    put("message", "操作成功");
                    put("timestamp", System.currentTimeMillis());
                }});
            }
        } catch (IOException e) {
            log.error("IOException!", e);
            return "error";
        }

        return "Succeed!";
    }

    /**
     * 将SseEmitter对象设置成完成
     *
     * @param clientId
     * @return
     */
    @GetMapping("/end")
    public String completeSseEmitter(@RequestParam(defaultValue = "a") String clientId) {
        SeeResult result = SseMap.sseEmitterMap.get(clientId);
        if (result != null) {
            SseMap.sseEmitterMap.remove(clientId);
            result.sseEmitter.complete();
        }

        return "Succeed!";
    }


}
