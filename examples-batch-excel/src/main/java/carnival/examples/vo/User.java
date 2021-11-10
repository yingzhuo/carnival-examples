package carnival.examples.vo;

import com.github.yingzhuo.carnival.batch.bean.ArrayIdx;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    @ArrayIdx(-1)
    private Long id;

    @ArrayIdx(1)
    private String username;

    @ArrayIdx(2)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

}
