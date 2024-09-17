package com.aaa.javabase.web;

import com.aaa.javabase.spring.batch.BatchConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author liuzhen.tian
 * @version 1.0 BatchController.java  2024/9/17 9:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/batchTest")
@EnableBatchProcessing
public class BatchController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private BatchConfiguration batchConfiguration;

    @GetMapping("/start")
    public ResponseEntity<?> startJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("someParameter", UUID.randomUUID().toString()) // 如果需要的话，添加作业参数
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(
                batchConfiguration.importUserJob(null),
                jobParameters);

        // 根据需要处理jobExecution结果
        return ResponseEntity.ok("Job started. Execution ID: " + jobExecution.getId());
    }
}
