package goroh.dto.response;

import goroh.entity.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    public PeopleResponse(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.age = person.getAge();
    }
}
