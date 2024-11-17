package com.insuranceApp.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@EnableScheduling
@RequestMapping("/insuranceApp")
public class SpringBatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @GetMapping("/startJob")
    //@Scheduled(cron = "0 0 1 1/1 * ?") // Use this if you want to schedule the job
    public BatchStatus loadInsuranceClients() throws Exception {
        System.out.println("Batch job started.");

        // Build job parameters
        JobParameters parameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();

        // Start the job and wait for completion
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        while (jobExecution.isRunning()) {
            System.out.println("Batch job is still running...");
        }

        System.out.println(jobExecution.getStatus());
        return jobExecution.getStatus();
    }
}