package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.BindingException;
import cz.muni.fi.pa165.rest.exceptions.CouldNotCreateException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.MovieDetailRepresentationModelAssembler;
import cz.muni.fi.pa165.rest.hateoas.MovieListRepresentationModelAssembler;
import cz.muni.fi.pa165.service.exceptions.NotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * SpringMVC controller to handle all concerning Movie
 * root: /movies
 * children:
 *  / - get all
 *  /browse - filter by posting JSON
 *  /{id} - get Movie Detail
 *
 *
 * @author Marek Petrovič
 */
//TODO auth
@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@ExposesResourceFor(MovieDetailDTO.class)
@RequestMapping(value = Uris.ROOT_URI_MOVIES)
public class MovieController {

    final static Logger log = LoggerFactory.getLogger(MovieController.class);

    private MovieFacade movieFacade;

    private MovieDetailRepresentationModelAssembler movieDetailRepresentationModelAssembler;

    private MovieListRepresentationModelAssembler movieListRepresentationModelAssembler;

    private EntityLinks entityLinks;


    @Autowired
    public MovieController(MovieFacade movieFacade,
                           MovieDetailRepresentationModelAssembler movieDetailRepresentationModelAssembler,
                           MovieListRepresentationModelAssembler movieListRepresentationModelAssembler,
                           @SuppressWarnings("SpringJavaAutowiringInspection") EntityLinks entityLinks) {

        this.movieFacade = movieFacade;
        this.movieListRepresentationModelAssembler = movieListRepresentationModelAssembler;
        this.movieDetailRepresentationModelAssembler = movieDetailRepresentationModelAssembler;
        this.entityLinks = entityLinks;
    }


    //    MovieDetailDTO findMovieById(Long movieId); OK - needs userRating fillup when logged in
    //    List<MovieListDTO> findMovieByParameters(ParametersDTO parametersDTO); - SEEMS OK

    /**
     * Handles GET request to browse Movies
     *
     * @return list all of Movies (MovieListDTO)
     */
    @ApiOperation(value = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
    })
    @RequestMapping(method = RequestMethod.GET,produces = "application/hal+json")
    public final HttpEntity<CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>>> getAll(){
        log.debug("rest getAll() - get all movies");
        ParametersDTO parametersDTO = new ParametersDTO(null,null,null,null, null);
        List<MovieListDTO> allMovies = movieFacade.findMovieByParameters(parametersDTO);
        CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(allMovies);
        modelCollectionModel.add(linkTo(MovieController.class).withSelfRel());
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }


    /**
     * Handles Movie browsing/filtering.
     * Can be also used for listing all Movies if all parameters are null.
     *
     * @param parametersDTO ParametersDTO
     * @return list of filtered Movies (MovieListDTO)
     */
    @ApiOperation(value = "Find movies by parameters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/browse",method = RequestMethod.POST,produces = "application/hal+json")
    public final HttpEntity<CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>>> browseFilter(@RequestBody @Valid ParametersDTO parametersDTO, BindingResult bindingResult) throws Exception {
        log.debug("browse(parametersDTO={})", parametersDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult);
            throw new BindingException("Couldn't bind provided json");
        }

        List<MovieListDTO> movieListDTOList = movieFacade.findMovieByParameters(parametersDTO);

        CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(movieListDTOList);
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }

    /**
     * Returns Detail of Movie
     * TODO needs fillup with userRating - of logged
     * @param id Movie ID
     * @return application/hal+json
     */
    @ApiOperation(value = "Find movie by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/hal+json")
    public final HttpEntity<EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> getMovieDetail(@PathVariable long id){
        log.debug("getMovieDetail(id={})", id);

        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(id);
        if (movieDetailDTO == null) {
            throw new ResourceNotFoundException("Movie with id="+id+" not found");
        }
        EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> movieDetailDTOEntityModel = movieDetailRepresentationModelAssembler.toModel(movieDetailDTO);
        return new ResponseEntity<>(movieDetailDTOEntityModel, HttpStatus.OK);
    }


//    /**
//     * Not used ?
//     * @param id
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "/{id}/titleImage",method = RequestMethod.GET)
//    public void movieTitleImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException{
//        log.debug("movieTitleImage(id={})",id);
//        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(id);
//        byte[] image = movieDetailDTO.getImageTitle().getImage();
//        if (image == null) {
//            response.sendRedirect(request.getContextPath() + "/cz/muni/fi/pa165/rest/resources/no-image.png");
//        } else {
//            response.setContentType(movieDetailDTO.getImageTitle().getImageMimeType());
//            ServletOutputStream out = response.getOutputStream();
//            out.write(image);
//            out.flush();
//        }
//    }

//    WHEN AUTHORIZED
//    List<MovieListDTO> getRecommendedMovies(UserDTO userDTO);
    //TODO usera si ziskam z tokenu, alebo podla mena :/
    // na frontende nemam cele UserDTO, keby mam, tak neni problem...
    @ApiOperation(value = "Get recommended movies for user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Couldn't bind provided json")
    })
    @RequestMapping(value = "/recommended",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>>> getRecommendedMovies(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) throws Exception {

        log.debug("getRecommendedMovies(UserDTO={})", userDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult);
            throw new BindingException("Couldn't bind provided json");
        }

        List<MovieListDTO> movieListDTOList = movieFacade.getRecommendedMovies(userDTO);
        CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(movieListDTOList);
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }



//    ONLY FOR ADMINS
//    Long createMovie(MovieCreateDTO movieCreateDTO); - OK
    //TODO user auth solve, can be by id/token...
    @ApiOperation(value = "Get recommended movies for user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Couldn't bind provided json")
    })
    @RequestMapping(value = "/create",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> createMovie(@RequestBody @Valid MovieCreateDTO movieCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("createMovie(MovieCreateDTO={})", movieCreateDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        Long id = movieFacade.createMovie(movieCreateDTO);
        if (id == null){
            throw new CouldNotCreateException("Could not create movie 😢");
        }
        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(id);
        if (movieDetailDTO == null) {
            log.error("createMovie not found");
            throw new ResourceNotFoundException("Movie with id="+id+" not found");
        }
        EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> movieDetailDTOEntityModel = movieDetailRepresentationModelAssembler.toModel(movieDetailDTO);
        return new ResponseEntity<>(movieDetailDTOEntityModel, HttpStatus.OK);
    }


//    TODO auth
//    Long updateMovieAttrs(MovieDetailDTO movieDetailDTO); - OK
    @ApiOperation(value = "Update attributes of movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Unspecified error happened")
    })
    @RequestMapping(value = "/{movieId}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> updateMovie(@PathVariable long movieId, @RequestBody @Valid MovieDetailDTO movieDetailDTO, BindingResult bindingResult) throws Exception {
        log.debug("updateMovie(MovieDetailDTO={})", movieDetailDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult);
            throw new Exception("Failed validation");
        }

        Long id = null;
        try {
            id = movieFacade.updateMovieAttrs(movieDetailDTO);
        } catch (DataAccessException e){
            log.error("updateMovie exception {}",e.toString());
            throw new ResourceNotFoundException("movie to update does not exist.");
        }

        if (id == null){
            throw new CouldNotCreateException("Could not create movie.");
        }
        MovieDetailDTO movieById = movieFacade.findMovieById(id);
        if (movieById == null) {
            log.error("updateMovie not found");
            throw new ResourceNotFoundException("Movie with id="+id+" not found");
        }
        EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> movieDetailDTOEntityModel = movieDetailRepresentationModelAssembler.toModel(movieById);
        return new ResponseEntity<>(movieDetailDTOEntityModel, HttpStatus.OK);
    }

//    void deleteMovie(Long movieId); - OK
    @ApiOperation(value = "Update attributes of movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unspecified error happened")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public final HttpEntity<HttpStatus> deleteMovie(@PathVariable long id){
        log.debug("getMovieDetail(id={})", id);

        try {
            movieFacade.deleteMovie(id);
        } catch (DataAccessException e){
            log.error("Movie not found e: {}",e.toString());
            throw new ResourceNotFoundException("Not found");
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void changeTitleImage(ImageCreateDTO imageCreateDTO); - OK - pri neexistujucej Null Argument Exception
    @ApiOperation(value = "Change title image of movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unspecified error happened")
    })
    @RequestMapping(value = "/changetitle",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> changeTitleImage(@RequestBody @Valid ImageCreateDTO imageCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("changeTitleImage(ImageCreateDTO={})", imageCreateDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult);
            throw new BindingException("Could not bind provided json");
        }

        try{
            movieFacade.changeTitleImage(imageCreateDTO);
        } catch (DataAccessException e){
            log.error("changeTitleImage movie does not exist, exception: {}",e.toString());
            throw new ResourceNotFoundException("Movie does not exist");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void addImage(ImageCreateDTO imageCreateDTO); - OK doesnt allow duplicates (equals)
    @ApiOperation(value = "Add image to gallery of movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Unspecified error happened")
    })
    @RequestMapping(value = "/addimage",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> addImage(@RequestBody @Valid ImageCreateDTO imageCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("addImage(ImageCreateDTO={})", imageCreateDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult);
            throw new BindingException("could not bind provided json");
        }
        try {
            movieFacade.addImage(imageCreateDTO);
        } catch (DataAccessException e){
            log.error("movie does not exist e: {}",e.toString());
            throw new ResourceNotFoundException("movie does not exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void deleteImage(Long imageId); - OK povoli mazat iba z galerie, inak hadze 500
    @ApiOperation(value = "Delete image from gallery of movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @RequestMapping(value = "/image/{id}",method = RequestMethod.DELETE)
    public final HttpEntity<HttpStatus> deleteImage(@PathVariable long id){
        log.debug("deleteImage(id={})", id);

        try {
            movieFacade.deleteImage(id);
        } catch (DataAccessException e){
            log.error("Image does not exist, e:{}",e.toString());
            throw new ResourceNotFoundException("Image id does not exist");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void addActor(PersonToMovieDTO personDTO); - OK, duplicity nepridava
    @ApiOperation(value = "Add actor to movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @RequestMapping(value = "/actor",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> addActor(@RequestBody @Valid PersonToMovieDTO personToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("addActor(PersonToMovieDTO={})", personToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }
        try {
            movieFacade.addActor(personToMovieDTO);
        } catch (DataAccessException e){
            log.error("Movie or actor dont exist e:{}",e.toString());
            throw new ResourceNotFoundException("Movie or actor dont exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void deleteActor(PersonToMovieDTO personDTO); - OK
    @ApiOperation(value = "Add actor to movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @RequestMapping(value = "/actor",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> deleteActor(@RequestBody @Valid PersonToMovieDTO personToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("deleteActor(PersonToMovieDTIO={})", personToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }
        try {
            movieFacade.deleteActor(personToMovieDTO);
        } catch (DataAccessException e){
            log.error("Not found e: {}",e.toString());
            throw new ResourceNotFoundException("Person or Movie not found");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void addGenre(GenreToMovieDTO genreToMovieDTO); OK
    @ApiOperation(value = "Add genre to movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @RequestMapping(value = "/genre",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> addGenre(@RequestBody @Valid GenreToMovieDTO genreToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("addGenre(GenreToMovie={})", genreToMovieDTO);
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        try {
            movieFacade.addGenre(genreToMovieDTO);
        } catch (Exception e){
            log.error("Not found e: {}",e.toString());
            throw new ResourceNotFoundException("Genre or Movie was not found");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void removeGenre(GenreToMovieDTO genreToMovieDTO);
    @ApiOperation(value = "Delete genre from movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @RequestMapping(value = "/genre",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> deleteGenre(@RequestBody @Valid GenreToMovieDTO genreToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("removeGenre(GenreToMovie={})", genreToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        try{
            movieFacade.removeGenre(genreToMovieDTO);
        } catch (Exception e){
            log.error("Not found e: {}",e.toString());
            throw new ResourceNotFoundException("Genre or Movie was not found");
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }


//    void changeDirector(PersonToMovieDTO personDTO); - OK
    @ApiOperation(value = "Change Director of movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @RequestMapping(value = "/director",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> changeDirector(@RequestBody @Valid PersonToMovieDTO personToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("changeDIrector(PersonToMovie={})", personToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        try {
            movieFacade.changeDirector(personToMovieDTO);
        } catch (Exception e){
            log.error("Not found e: {}",e.toString());
            throw new ResourceNotFoundException("Director or Movie was not found");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
