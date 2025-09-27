package helper;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomeResponse {

    private String message;

    private HttpStatus httpStatus;  // typo fixed
    private boolean success = false;
}
