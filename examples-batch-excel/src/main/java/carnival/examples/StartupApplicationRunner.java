package carnival.examples;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartupApplicationRunner implements ApplicationRunner {

    private final ApplicationContext applicationContext;
    private final JobLauncher jobLauncher;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Job job = applicationContext.getBean("userImportingJob", Job.class);

        jobLauncher.run(job,
                new JobParametersBuilder()
                        .addString("input", "classpath:examples/users.xlsx")
                        .addLong("run.id", System.currentTimeMillis())
                        .toJobParameters());
    }

}
