package goroh.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PeopleRequest {

    @NonNull
    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull
    private String lastName;

    @Max(80)
    @Min(18)
    private Integer age;
}
