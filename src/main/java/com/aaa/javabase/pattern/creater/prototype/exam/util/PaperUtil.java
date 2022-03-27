package com.aaa.javabase.pattern.creater.prototype.exam.util;

import com.aaa.javabase.pattern.creater.prototype.exam.model.Topic;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author liuzhen.tian
 * @version 1.0 PaperUtil.java  2022/3/27 21:06
 */
public class PaperUtil {

    /**
     * 乱序考试题选项
     *
     * @param option 考试题选项：A、B、C、D
     * @param key    正确选项：B
     * @return Topic
     */
    public static Topic random(Map<String, String> option, String key) {

        List<String> keyOptions = new ArrayList<>(option.keySet());
        // 乱序选项
        Collections.shuffle(keyOptions);

        // 新的选项
        Map<String, String> optionNew = Maps.newHashMap();
        // 新的对应答案选项
        String keyNew = null;

        int index = 0;
        for (String opt : option.keySet()) {
            String randomKey = keyOptions.get(index);
            // 匹配新的答案选项
            if (key.equals(opt)) {
                keyNew = randomKey;
            }
            optionNew.put(randomKey, option.get(opt));
            index++;
        }

        return new Topic(optionNew, keyNew);
    }
}
