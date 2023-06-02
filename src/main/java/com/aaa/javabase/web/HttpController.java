package com.aaa.javabase.web;

import com.aaa.javabase.domain.BaseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 httpController.java  2022/8/31 20:39
 */
@RestController
@RequestMapping(value = "/httpController")
public class HttpController {

    /**
     * ==========================  springboot如何接收application/x-www-form-urlencoded类型的
     * 注意：请求类型只能是 post 否则无法接收
     */
    @PostMapping("/req1")
    public String detailByParam(@RequestParam Integer id, @RequestParam(value = "name") String name ) {
        System.out.println(">>> id=" + id + ",name=" + name);
        return ">>> id=" + id + ",name=" + name;
    }

    @PostMapping("/req2")
    public void detailByParam(@RequestParam Map<String, String> params) {
        System.out.println(">>>id=" + params.get("id") + ",name=" + params.get("name"));
    }

    @PostMapping("/req3")
    public void detailByParam(@RequestBody String params) {
        System.out.println(">>>"+params);
    }


    @PostMapping("/req4")
    @ResponseBody
    public Object detailByParam() {
      return new BaseEntity(1000L);
    }

}
