package goroh.service;

import goroh.dto.request.PeopleRequest;
import goroh.dto.response.DataResponse;
import goroh.dto.response.PeopleResponse;
import goroh.entity.Person;
import goroh.repository.PeopleRepository;
import goroh.specification.PersonSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PeopleRepository peopleRepository;

    public Integer save(PeopleRequest personRequest){
        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setAge(personRequest.getAge());
        person = peopleRepository.save(person);
        return person.getId();
    }

    public DataResponse<PeopleResponse> findAll(String value, Integer page, Integer size, String fieldName, Sort.Direction direction){
        Sort sort = Sort.by(direction, fieldName);
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        Page<Person> pagePerson;
        if(value != null && !value.equals("")) {
            PersonSpecification specification = new PersonSpecification(value);
            pagePerson = peopleRepository.findAll(specification, pageRequest);
        }else{
            pagePerson = peopleRepository.findAll(pageRequest);
        }
        return new DataResponse<PeopleResponse>(pagePerson.stream().map(PeopleResponse::new).collect(Collectors.toList()), pagePerson);
    }

    @Transactional
    public PeopleResponse findOne(Integer id) {
        Optional<Person> personOptional = peopleRepository.findById(id);
        if (personOptional.isPresent()) {
            return new PeopleResponse(personOptional.get());
        } else {
            throw new IllegalArgumentException("People with id " + id + "is not exist");
        }
    }

        public void delete(Integer id) {
            peopleRepository.deleteById(id);
        }


}
