package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.CouldNotCreateException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.GenreRepresentationModelAssembler;
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
@ExposesResourceFor(GenreDTO.class)
@RequestMapping(Uris.ROOT_URI_GENRES)
public class GenreController {

    final static Logger log = LoggerFactory.getLogger(GenreController.class);

    private GenreFacade genreFacade;
    private GenreRepresentationModelAssembler genreRepresentationModelAssembler;


    @Autowired
    public GenreController(GenreFacade genreFacade, GenreRepresentationModelAssembler genreRepresentationModelAssembler) {
        this.genreFacade = genreFacade;
        this.genreRepresentationModelAssembler = genreRepresentationModelAssembler;
    }

    /**
     * Handles GET request to find genre by specific ID.
     *
     * @return HttpEntity with GenreDTO object in json repressentation and status report
     */
    @ApiOperation(value = "Find genre with given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Not Found"),
    })
    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<GenreDTO>> findGenreById(@PathVariable Long id) {
        log.debug("rest findGenreById(" + id + ") - get genre by id.");

        GenreDTO genre = genreFacade.findGenreById(id);

        if (genre == null) {
            throw new ResourceNotFoundException("Genre with id = {" + id + "} not found.");
        }

        EntityModel<GenreDTO> entityModel = genreRepresentationModelAssembler.toModel(genre);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles GET request to browse all genres.
     *
     * @return HttpEntity with collection of GenreDTO objects in json repressentation and status report
     */
    @ApiOperation(value = "Find all genres")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @GetMapping(produces = "application/hal+json")
    public final ResponseEntity<CollectionModel<EntityModel<GenreDTO>>> getAllGenres() {
        log.debug("rest GetAllGenres() - get all genres");

        List<GenreDTO> allGenre = genreFacade.findAllGenres();
        CollectionModel<EntityModel<GenreDTO>> entityModels = genreRepresentationModelAssembler.toCollectionModel(allGenre);

        // self link to collection
        entityModels.add(linkTo(GenreController.class).withSelfRel());

        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    /**
     * Handles DELETE request to delete genre specified with id.
     *
     * @return HttpEntity with status report
     */
    @ApiOperation(value = "delete specific genre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @DeleteMapping(value = "/{id}", produces = "application/hal+json")
    public final HttpEntity<HttpStatus> deleteGenre(@PathVariable Long id) {
        log.debug("rest deleteGenre(" + id + ") - delete specific genre");

        genreFacade.deleteGenre(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles POST request to create new genre
     *
     * @return HttpEntity with status report
     */
    @ApiOperation(value = "create new genre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @PostMapping(value = "/create", produces = "application/hal+json")
    //public final ResponseEntity<EntityModel<GenreDTO>> createGenre(@RequestBody @Valid GenreDTO genreDTO, BindingResult bindingResult) throws Exception {
    public final HttpEntity<HttpStatus> createGenre(@RequestBody @Valid GenreDTO genreDTO, BindingResult bindingResult) throws Exception {
        log.debug("createGenre(GenreDTO={})", genreDTO);

        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        genreFacade.createGenre(genreDTO);

        return new ResponseEntity<>(HttpStatus.OK);

//        Long id = genreFacade.createGenre(genreDTO);   // TODO
//        if (id == null){
//            throw new CouldNotCreateException("Could not create genre 😢");
//        }
//        GenreDTO genreDTOinDB = genreFacade.findGenreById(id);
//        if (genreDTOinDB == null) {
//            throw new ResourceNotFoundException("Genre with id="+id+" not found");
//        }

//        EntityModel<GenreDTO> entityModel = genreRepresentationModelAssembler.toModel(genreDTOinDB);
//        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles PUT request to update genre
     *
     * @return HttpEntity with status report   // TODO
     */
    @ApiOperation(value = "update genre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @PutMapping(value = "/update", produces = "application/hal+json")
    public final ResponseEntity<EntityModel<GenreDTO>> updateGenre(@RequestBody @Valid GenreDTO genreDTO, BindingResult bindingResult) throws Exception {
        log.debug("updateGenre(GenreDTO={})", genreDTO);

        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        Long id = genreFacade.updateGenre(genreDTO).getId();
        if (id == null){
            throw new CouldNotCreateException("Could not create genre 😢");
        }
        GenreDTO genreDTOinDB = genreFacade.findGenreById(id);
        if (genreDTOinDB == null) {
            throw new ResourceNotFoundException("Genre with id="+id+" not found");
        }

        EntityModel<GenreDTO> entityModel = genreRepresentationModelAssembler.toModel(genreDTOinDB);
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }
}
