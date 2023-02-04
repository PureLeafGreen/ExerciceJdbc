package cal.al420445.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Registration {
    private long id;
    private String lastName;
    private String firstName;
    private int age;

    public Registration(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }
}
