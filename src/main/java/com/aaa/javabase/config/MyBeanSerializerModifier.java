package com.aaa.javabase.config;

/**
 * @author liuzhen.tian
 * @version 1.0 MyBeanSerializerModifier.java  2020/10/29 15:56
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class MyBeanSerializerModifier extends BeanSerializerModifier {

    //  数组类型
    private JsonSerializer _nullArrayJsonSerializer = new MyNullArrayJsonSerializer();
    // 字符串等类型
    private JsonSerializer _nullStringSerializer = new MyNullStringSerializer();
    private JsonSerializer _nullObjectSerializer = new MyNullObjectSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List beanProperties) {
        //循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = (BeanPropertyWriter) beanProperties.get(i);
            //判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isArrayType(writer)) {
                //给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this._nullArrayJsonSerializer);
            } else if(isStringType(writer)) {
                writer.assignNullSerializer(this._nullStringSerializer);
            }else {
                writer.assignNullSerializer(this._nullObjectSerializer);
            }
        }
        return beanProperties;
    }

    //判断是什么类型
    protected boolean isArrayType(BeanPropertyWriter writer) {
        Class clazz =  writer.getType().getRawClass();
        return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);
    }

    /**
     * 是否是String
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || clazz == String.class;
    }
    class MyNullArrayJsonSerializer extends JsonSerializer {

        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value == null) {
                jgen.writeStartArray();
                jgen.writeEndArray();
            }
        }
    }
    class MyNullStringSerializer extends JsonSerializer{

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeString("");
        }
    }

    class MyNullObjectSerializer extends JsonSerializer{

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            if (value ==null) {
                jsonGenerator.writeNull();
            }
        }
    }
}
