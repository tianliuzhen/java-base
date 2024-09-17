package com.aaa.javabase.spring.batch;

import com.aaa.javabase.h2.Model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @author liuzhen.tian
 * @version 1.0 BatchConfiguration.java  2024/9/17 10:20
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    public ItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                // .resource(new ClassPathResource("F:\\我的\\用户信息.csv"))
                .resource(new FileSystemResource("F:\\我的\\用户信息.csv"))
                .delimited()
                .names(new String[]{"id", "name"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(User.class);
                }})
                .build();
    }

    public ItemProcessor<User, User> processor() {
        return user -> {
            user.setName(user.getName().toUpperCase());
            return user;
        };
    }

    public ItemWriter<User> writer() {
        return items -> {
            items.forEach(item -> System.out.println("Writing item: " + item));
        };
    }

    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .flow(csvStep())
                .end()
                .build();
    }

    public Step csvStep() {
        return stepBuilderFactory.get("csvStep")
                .<User, User>chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
