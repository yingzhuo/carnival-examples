package carnival.examples.batch;

import carnival.examples.vo.User;
import com.github.yingzhuo.carnival.batch.bean.DataBindingUtils;
import com.github.yingzhuo.carnival.batch.excel.ExcelData;
import com.github.yingzhuo.carnival.batch.excel.ExcelDataRowMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Slf4j
@AllArgsConstructor
public class UserImportingBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ResourceLoader resourceLoader;

    @StepScope
    @Bean("userImportingReader")
    PoiItemReader<ExcelData> excelDataItemReader(@Value("#{jobParameters['input']}") String resourceLocation) {
        log.debug("resourceLocation = {}", resourceLocation);

        final Resource resource = resourceLoader.getResource(resourceLocation);
        log.debug("resource = {}", resource);
        log.debug("resource is readable ? {}", resource.isReadable());

        final PoiItemReader<ExcelData> bean = new PoiItemReader<>();
        bean.setResource(resource);
        bean.setLinesToSkip(1);
        bean.setRowMapper(new ExcelDataRowMapper());
        return bean;
    }

    @StepScope
    @Bean("userImportingProcessor")
    ItemProcessor<ExcelData, ExcelData> excelDataItemProcessor() {
        return i -> i;
    }

    @StepScope
    @Bean("userImportingWriter")
    ItemWriter<ExcelData> excelDataItemWriter() {
        return items -> {
            for (ExcelData item : items) {
                User user = new User();
                DataBindingUtils.bind(user, item.getRow());
                log.debug("{}", user);
            }
        };
    }

    @Bean("userImportingStep1")
    Step step(
            @Qualifier("userImportingReader") ItemReader<ExcelData> reader,
            @Qualifier("userImportingProcessor") ItemProcessor<ExcelData, ExcelData> processor,
            @Qualifier("userImportingWriter") ItemWriter<ExcelData> writer
    ) {
        return stepBuilderFactory.get("user-importing-step-1")
                .<ExcelData, ExcelData>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean("userImportingJob")
    Job job(@Qualifier("userImportingStep1") Step step) {
        return jobBuilderFactory.get("user-importing")
                .start(step)
                .build();
    }

}
