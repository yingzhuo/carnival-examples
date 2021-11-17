package carnival.examples;

import com.github.yingzhuo.carnival.config.propertysource.HoconPropertySourceFactory;
import com.github.yingzhuo.carnival.config.propertysource.YamlPropertySourceFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Slf4j
@PropertySources({
        @PropertySource(value = "classpath:config.yaml", factory = YamlPropertySourceFactory.class),
        @PropertySource(value = "classpath:config.conf", factory = HoconPropertySourceFactory.class)
})
@SpringBootApplication
@AllArgsConstructor
public class AppBoot implements ApplicationRunner {

    private final Environment env;

    public static void main(String[] args) {
        SpringApplication.run(AppBoot.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(env.getProperty("yaml"));
        System.out.println(env.getProperty("hocon"));
    }

}
