package goroh.controller;

import goroh.dto.request.PeopleRequest;
import goroh.dto.response.DataResponse;
import goroh.dto.response.PeopleResponse;
import goroh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequestMapping("/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public Integer createPerson(@RequestBody @Valid PeopleRequest personRequest){
        System.out.println("SAVE PERSON IN DB with first name -> "+ personRequest.getFirstName());
        return personService.save(personRequest);
    }


    @GetMapping
    public DataResponse<PeopleResponse> getPeople(@RequestParam(required = false) String value,
                                                  @RequestParam Integer page,
                                                  @RequestParam Integer size,
                                                  @RequestParam String sortFieldName,
                                                  @RequestParam Sort.Direction direction) {
        System.out.println("Show all people");
        return personService.findAll(value, page, size, sortFieldName, direction);
    }

    @GetMapping("/{id}")
    public PeopleResponse getPersonById(@PathVariable Integer id){
        System.out.println("Get person by id " + id);
        return personService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void  search(@PathVariable Integer id) {
        System.out.println("Delete person by id " + id);
        personService.delete(id);
    }

}
