package com.mini.SpringBatchExample.config;


import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.mini.SpringBatchExample.entty.Users;




@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
						StepBuilderFactory stepBuilderFactory,
						ItemReader<Users> itemReader,
						ItemProcessor<Users,Users> itemProcessor ,
						ItemWriter<Users> itemWriter ){
		Step step= stepBuilderFactory.get("ETL-FILE-LOAD")
				.<Users,Users>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
							
		
		
		return jobBuilderFactory.get("ETL-Load")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		 
	}
	
	@Bean
	public FlatFileItemReader<Users> itemReader(@Value("${input}") Resource resource){
		FlatFileItemReader<Users> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(LineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<Users> LineMapper() {
		// TODO Auto-generated method stud
		DefaultLineMapper<Users> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] {"id","name","dept","salary"});
		BeanWrapperFieldSetMapper<Users> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Users.class);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		
		return defaultLineMapper;
	}
}
