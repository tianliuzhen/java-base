package com.aaa.javabase.fastjson;

/*
 * Ant Group
 * Copyright (c) 2004-2025 All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author tianliuzhen
 * @version FastJsonEnumDynamicDemoTest.java, v 0.1 2025年12月12日 下午4:56 tianliuzhen
 */
public class FastJsonEnumDynamicDemoTest {
    /* ===== 1. 枚举定义 ===== */
    public enum RefundPhaseModeEnum {
        REFUND_CONSTRUCT,
        REFUND_OTHER
    }

    public enum PayStatusEnum {
        SUCCESS,
        FAIL
    }

    /* ===== 2. Bean ===== */
    public static class Bean {
        public RefundPhaseModeEnum refundPhaseMode;
        public PayStatusEnum payStatus;
    }

    /* ===== 3. 通用枚举反序列化器：兼容对象/字符串 ===== */
    public static class UniversalEnumDeserializer implements ObjectDeserializer {

        @SuppressWarnings("unchecked")
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            JSONLexer lexer = parser.getLexer();
            int token = lexer.token();

            String name;
            if (token == JSONToken.LBRACE) {
                // 对象形式：{ "name": "XXX" }
                JSONObject obj = parser.parseObject();
                Object n = obj.get("name");
                if (n == null) {
                    return null;
                }
                name = String.valueOf(n);
            } else if (token == JSONToken.LITERAL_STRING) {
                // 字符串形式："XXX"
                name = lexer.stringVal();
                lexer.nextToken();
            } else if (token == JSONToken.NULL) {
                lexer.nextToken();
                return null;
            } else {
                throw new JSONException("expect object or string for enum, field: " + fieldName);
            }

            Class<?> enumClass = (Class<?>) type;
            if (!enumClass.isEnum()) {
                throw new JSONException(type + " is not enum");
            }
            return (T) Enum.valueOf((Class<Enum>) enumClass, name);
        }

        @Override
        public int getFastMatchToken() {
            // 既要支持对象，也要支持字符串，这里写成 LBRACE 即可，fastjson 实际会继续判断
            return JSONToken.LBRACE;
        }
    }

    /* ===== 4. 自定义 ParserConfig：所有 enum 类型都用 UniversalEnumDeserializer ===== */
    public static class EnumFriendlyParserConfig extends ParserConfig {

        private final ObjectDeserializer enumDeserializer = new UniversalEnumDeserializer();

        @Override
        public ObjectDeserializer getDeserializer(Type type) {
            // 注意：一定要先判断 enum 类型，再走 super
            if (type instanceof Class && ((Class<?>) type).isEnum()) {
                return enumDeserializer;
            }
            return super.getDeserializer(type);
        }

        /**
         * 继承 ParserConfig，如果属性赋值 一定要实现，无法内部属性值反序列化的
         * public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {}
         *
         * @param clazz
         * @param type
         * @return
         */
        @Override
        public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
            if (clazz != null && clazz.isEnum()) {
                return enumDeserializer;
            }
            return super.getDeserializer(clazz, type);
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 使用我们自己的 ParserConfig
        EnumFriendlyParserConfig config = new EnumFriendlyParserConfig();
        // 2. 关闭 ASM，避免走 ASM 生成的反序列化器绕过我们的逻辑
        config.setAsmEnable(false);

        // 3. 测试 JSON
        String json = "{" +
                "\"refundPhaseMode\": {\"name\":\"REFUND_CONSTRUCT\"}," +
                "\"payStatus\": \"SUCCESS\"" +
                "}";

        // ObjectMapper mapper = new ObjectMapper();
        // Bean bean2 = mapper.readValue(json, Bean.class);

        // 4. 关键：一定要调用这个重载，传入自定义 config
        Bean bean = JSON.parseObject(json,
                Bean.class,
                config,
                JSON.DEFAULT_PARSER_FEATURE);

        System.out.println("refundPhaseMode = " + bean.refundPhaseMode); // REFUND_CONSTRUCT
        System.out.println("payStatus       = " + bean.payStatus);       // SUCCESS
    }
}
