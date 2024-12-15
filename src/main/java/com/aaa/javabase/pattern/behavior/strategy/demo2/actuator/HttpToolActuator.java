package com.aaa.javabase.pattern.behavior.strategy.demo2.actuator;

import com.aaa.javabase.pattern.behavior.strategy.demo2.ChooserActuator;
import com.aaa.javabase.pattern.behavior.strategy.demo2.ToolActuator;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.HttpToolMeta;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.ToolExecRequest;
import com.aaa.javabase.pattern.behavior.strategy.demo2.model.enums.ToolTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author liuzhen.tian
 * @version 1.0 HttpToolActuator.java  2024/12/15 14:00
 * <p>
 * 在这个定义中，ToolActuator 接口自身是泛型的，泛型参数 T 在接口级别上被声明。这意味着，
 * 当你实现这个接口时，你必须为 T 指定一个具体的类型，或者如果你打算在多个地方使用不同的类型，你需要为每个类型创建一个具体的实现类
 * <p>
 * 总结：
 * 如果你希望接口的实现是特定于某种类型的，那么你应该在接口级别上声明泛型。
 * public interface ToolActuator <T extends ToolMeta> {
 * public Object execute(ToolExecRequest<T> request);
 * }
 * 如果你希望接口的实现能够处理多种类型，并且你希望在方法调用时确定这些类型，那么你应该在方法级别上声明泛型。
 * public interface ToolActuator {
 * public <T> Object execute(ToolExecRequest<T> request);
 * }
 */
@ChooserActuator(ToolTypeEnum.http)
@Component
public class HttpToolActuator implements ToolActuator<HttpToolMeta> {

    @Override
    public Object execute(ToolExecRequest<HttpToolMeta> request) {
        HttpToolMeta toolBaseMetaModel = request.getToolBaseMetaModel();

        // 执行http请求，返回结果
        return "http request success";
    }
}
