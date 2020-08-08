package carnival.examples.controller;

import com.github.yingzhuo.carnival.id.util.IdGeneratorUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SnowflakeController {

    @GetMapping("/snowflake")
    public Long snowflake() {
        return IdGeneratorUtils.nextId();
    }

}
