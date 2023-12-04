package com.aaa.javabase.web.webflux;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author liuzhen.tian
 * @version 1.0 WebFluxController.java  2023/12/2 21:23
 */
@RestController
public class WebFluxController {
    @SneakyThrows
    @GetMapping("/webflux")
    public Mono<String> test() {
        return Mono.just("test");
    }
}
