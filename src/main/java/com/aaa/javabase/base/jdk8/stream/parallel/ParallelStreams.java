package com.aaa.javabase.base.jdk8.stream.parallel;

import com.aaa.javabase.base.jdk8.stream.groupby.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhen.tian
 * @version 1.0 ParallelStreams.java  2020/12/25 12:57
 */
@Slf4j
public class ParallelStreams {
    public static void main(String[] args) {
        List<Fruit> list = Lists.newArrayList();

        Date begin = new Date();
        list.add(new Fruit("apple", 23.0,2.0,0.0));
        list.add(new Fruit("banner", 43.0,1.0,0.0));
        list.add(new Fruit("pink", 13.0,3.0,0.0));
        list.parallelStream().forEach(stu->{
            stu.setSum(stu.getPrice()*stu.getWeight());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Date end = new Date();
        log.info("数量：{}个, 耗时：{}s", list.size(), (end.getTime() - begin.getTime()) /1000);
    }
}
