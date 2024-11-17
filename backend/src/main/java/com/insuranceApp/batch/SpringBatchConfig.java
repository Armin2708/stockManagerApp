package com.insuranceApp.batch;

import com.insuranceApp.insuranceClient.InsuranceClient;
import com.insuranceApp.insuranceClient.InsuranceClientRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableTransactionManagement
public class SpringBatchConfig {

    @Autowired
    private InsuranceClientRepository insuranceClientRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private JobRepository jobRepository;


    @Bean
    public Job firstJob(){
        return new JobBuilder("job_clients")
                .repository(jobRepository)
                .start(convertStep())
                .build();
    }

    @Bean
    public Step convertStep() {
        return new StepBuilder("step_clients")
                .<InsuranceClient, InsuranceClient>chunk(100)
                .transactionManager(transactionManager)
                .repository(jobRepository)
                .reader(dbReader())
                .processor(processor())
                .writer(fileWriter())
                .taskExecutor(taskExecutor())
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(64);
        executor.setMaxPoolSize(64);
        executor.setQueueCapacity(64);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("MultiThreaded-");
        return executor;
    }

    @Bean
    public ItemProcessor<InsuranceClient, InsuranceClient> processor() {
        return insuranceClient -> insuranceClient;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<InsuranceClient> fileWriter() {
        FlatFileItemWriter<InsuranceClient> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("data/output.csv"));
        writer.setHeaderCallback(writer1 -> writer1.write("id, email, firstName, lastName, gender, birthDate, phoneNumber, country, state, city, postalCode, street, ageRisk, healthRisk, jobRisk, livingAreaRisk, price"));

        DelimitedLineAggregator<InsuranceClient> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<InsuranceClient> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "email", "firstName", "lastName", "gender", "birthDate", "phoneNumber", "country", "state", "city", "postalCode", "street", "ageRisk", "healthRisk", "jobRisk", "livingAreaRisk", "price"});

        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);

        return writer;
    }

    @Bean
    @StepScope
    public RepositoryItemReader<InsuranceClient> dbReader() {
        RepositoryItemReader<InsuranceClient> reader = new RepositoryItemReader<>();

        reader.setRepository(insuranceClientRepository);
        reader.setMethodName("findAll"); // Use the findAll method from InsuranceClientRepository
        reader.setSort(new HashMap<>() {{put("id", Sort.Direction.ASC);}});

        return reader;
    }
}