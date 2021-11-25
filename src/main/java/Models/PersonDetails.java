package Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDetails {

    private String name;
    private String email;
    private String mobilePhone;
    private String subject;
    private String message;
}
