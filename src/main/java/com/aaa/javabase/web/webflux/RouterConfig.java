package com.aaa.javabase.web.webflux;

/**
 * 路由写法
 *
 * @author liuzhen.tian
 * @version 1.0 RouterConfig.java  2023/12/2 22:43
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
public class RouterConfig {


    /**
     * 普通的请求：http://localhost:8080/hello
     */
    @Bean
    public RouterFunction<ServerResponse> hello() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello"),
                        request -> {
                            return ServerResponse.ok()
                                    .bodyValue("sddd");
                        });
    }

    /**
     * 带url入参的请求：http://localhost:8080/hello/1
     */
    @Bean
    public RouterFunction<ServerResponse> hello2() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello/{id}"),
                        request -> {
                            Long id = Long.valueOf(request.pathVariable("id"));
                            return ServerResponse.ok()
                                    .bodyValue("req:"+id);
                        });
    }


    /**
     * 带通用路径的入参：http://localhost:8080/pre/hello/1
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> hello3() {
        return RouterFunctions.nest(path("/pre"),
                RouterFunctions.route(RequestPredicates.GET("/hello/{id}"),
                        request -> {
                            Long id = Long.valueOf(request.pathVariable("id"));
                            return ServerResponse.ok()
                                    .bodyValue("req:"+id);
                        }
                ));
    }

    /**
     * 普通的请求：http://localhost:8080/hello
     */
    @Bean
    public RouterFunction<ServerResponse> hello4() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello4"),
                        request -> {
                            Mono<WebFluxUser> o = Mono.just(new WebFluxUser("o"));
                            return ServerResponse.ok()
                                    .body(o,WebFluxUser.class);
                        });
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/getUser").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
                .andRoute(RequestPredicates.GET("/{user}/customers").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getUserCustomers)
                .andRoute(RequestPredicates.DELETE("/{user}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
    }


    @Data
    @AllArgsConstructor
    public class WebFluxUser{
        private String name;
    }



    @Component
    public class UserHandler {

        public Mono<ServerResponse> getUser(ServerRequest request) {
            // ...
            return Mono.empty();
        }

        public Mono<ServerResponse> getUserCustomers(ServerRequest request) {
            // ...
            return Mono.empty();
        }

        public Mono<ServerResponse> deleteUser(ServerRequest request) {
            // ...
            return Mono.empty();
        }
    }
}


