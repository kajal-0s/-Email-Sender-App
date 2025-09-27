package helper;

import com.example.demo.Entity.entityofmail;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;


@Getter
@Setter
@Builder
public class EmailRequest extends entityofmail {

    public EmailRequest() {
        super(); // call parent no-arg constructor if exists
    }

    public EmailRequest(String to, String subject, String message) {
        super(to, subject, message); // call parent constructor
    }
}