package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.facade.PersonFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.BindingException;
import cz.muni.fi.pa165.rest.exceptions.CouldNotUpdateException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.PersonRepresentationModelAssembler;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * @author Peter Mravec
 */
@RestController
@ExposesResourceFor(PersonDTO.class)
@RequestMapping(Uris.ROOT_URI_PERSONS)
public class PersonController {

    final static Logger log = LoggerFactory.getLogger(PersonController.class);

    private PersonFacade personFacade;
    private PersonRepresentationModelAssembler personRepresentationModelAssembler;


    @Autowired
    public PersonController(PersonFacade personFacade, PersonRepresentationModelAssembler personRepresentationModelAssembler) {
        this.personFacade = personFacade;
        this.personRepresentationModelAssembler = personRepresentationModelAssembler;
    }

    /**
     * Handles GET request to find person by specific ID.
     *
     * @return ResponseEntity with PersonDTo object in json representation and status report
     */
    @ApiOperation(value = "Find person with given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Not Found"),
    })
    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<PersonDTO>> findPersonById(@PathVariable Long id) {
        log.debug("rest findPersonById(" + id + ") - get person by id.");

        PersonDTO person = personFacade.findById(id);

        if (person == null) {
            throw new ResourceNotFoundException("Person with id = {" + id + "} not found.");
        }

        EntityModel<PersonDTO> entityModel = personRepresentationModelAssembler.toModel(person);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles GET request to find person by specific NAME.
     *
     * @return ResponseEntity with PersonDTO object in json representation and status report
     */
    @ApiOperation(value = "Find person with given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Not Found"),
    })
    @GetMapping(value = "/name/{name}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<PersonDTO>> findPersonByName(@PathVariable String name) {
        log.debug("rest findPersonByName( {" + name + "} ) - get person by name.");

        PersonDTO person = personFacade.findByName(name);

        if (person == null) {
            throw new ResourceNotFoundException("Person with name = {" + name + "} not found.");
        }

        EntityModel<PersonDTO> entityModel = personRepresentationModelAssembler.toModel(person);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles GET request to browse all persons.
     *
     * @return ResponseEntity with collection of PersonDTO objects in json representation and status report
     */
    @ApiOperation(value = "Find all persons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @GetMapping(produces = "application/hal+json")
    public final ResponseEntity<CollectionModel<EntityModel<PersonDTO>>> getAllPersons() {
        log.debug("rest getAllPersons() - get all persons");

        List<PersonDTO> allPersons = personFacade.findAll();
        CollectionModel<EntityModel<PersonDTO>> entityModels = personRepresentationModelAssembler.toCollectionModel(allPersons);

        // self link to collection
        entityModels.add(linkTo(PersonController.class).withSelfRel());

        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    /**
     * Handles DELETE request to delete person specified with id.
     *
     */
    @ApiOperation(value = "delete specific person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        log.debug("rest deletePerson(" + id + ") - delete specific person");

        try {
            personFacade.delete(id);
        }
        catch (NullArgumentException ex) {
            log.error("Person with id: {} is not in db.", id);
            throw new ResourceNotFoundException(String.format("Person with id: {%d} is not in db.", id), ex);
        }
        catch (IllegalArgumentException ex) {
            log.error("Error has occurred during deleting person with id: {}", id);
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Handles POST request to create new person
     *
     * @return HttpEntity with status report    // TODO
     */
    @ApiOperation(value = "create new person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @PostMapping(value = "/create", produces = "application/hal+json")
    //public final ResponseEntity<EntityModel<PersonDTO>> createPerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) throws Exception {
    public final HttpEntity<HttpStatus> createPerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) throws Exception {
        log.debug("createPerson(PersonDTO={})", personDTO);

        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        personFacade.create(personDTO);

        return new ResponseEntity<>(HttpStatus.OK);

//        Long id = personFacade.create(personDTO);   // TODO
//        if (id == null){
//            throw new CouldNotCreateException("Could not create person 😢");
//        }
//        PersonDTO personDTOinDB = personFacade.findById(id);
//        if (personDTOinDB == null) {
//            throw new ResourceNotFoundException("Person with id="+id+" not found");
//        }
//
//        EntityModel<PersonDTO> entityModel = personRepresentationModelAssembler.toModel(personDTOinDB);
//        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles PUT request to update person
     *
     * @return ResponseEntity with PersonDTO object in json representation and status report
     */
    @ApiOperation(value = "update person")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/hal+json")
    public final ResponseEntity<EntityModel<PersonDTO>> updatePerson(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        log.debug("updatePerson(PersonDTO={})", personDTO);

        if (bindingResult.hasErrors()){
            log.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding.");
        }

        try {
            PersonDTO updatedPerson = personFacade.update(personDTO);
            EntityModel<PersonDTO> entityModel = personRepresentationModelAssembler.toModel(updatedPerson);

            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }
        catch (Exception ex) {
            log.error("Error has occurred during update.");
            throw new CouldNotUpdateException("Error occurred during update.", ex);
        }
    }
}
