package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Marek Petrovič
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade{

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    public static final String JPEG = "image/jpeg";

    private final ImageService imageService;
    private final MovieService movieService;
    private final GenreService genreService;
    private final PersonService personService;
    private final UserService userService;
    private final UserRatingService userRatingService;

    @Autowired
    public SampleDataLoadingFacadeImpl(ImageService imageService, MovieService movieService, GenreService genreService, PersonService personService, UserService userService, UserRatingService userRatingService) {
        this.imageService = imageService;
        this.movieService = movieService;
        this.genreService = genreService;
        this.personService = personService;
        this.userService = userService;
        this.userRatingService = userRatingService;
    }

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        log.info("started loading sample data");
        Image imageTitle1 = image("spiderman.jpg",JPEG);
        Image imageTitle2 = image("aaj_kal.jpg",JPEG);
        Image imageTitle3 = image("commando.jpg",JPEG);
        Image imageTitle4 = image("jaguar.jpg",JPEG);
        Image imageGallery1 = image("gallery1.jpg",JPEG);
        Image imageGallery2 = image("gallery2.jpg",JPEG);
        Image imageGallery3 = image("gallery3.jpg",JPEG);
        Image imageGallery4 = image("gallery4.jpg",JPEG);
        Image imageGallery5 = image("gallery5.jpg",JPEG);
        Image imageGallery6 = image("gallery6.jpg",JPEG);
        Image imageGallery7 = image("gallery7.jpg",JPEG);
        Image imageGallery8 = image("gallery8.jpg",JPEG);

        List<Image> images1 = new ArrayList<>(Arrays.asList(imageGallery1,imageGallery2));
        List<Image> images2 = new ArrayList<>(Arrays.asList(imageGallery3,imageGallery4));
        List<Image> images3 = new ArrayList<>(Arrays.asList(imageGallery5,imageGallery6));
        List<Image> images4 = new ArrayList<>(Arrays.asList(imageGallery7,imageGallery8));
        log.info("loaded images");

        Genre genre1 = genre("action");
        Genre genre2 = genre("romantic");
        Genre genre3 = genre("animated");
        Genre genre4 = genre("hindi");
        Genre genre5 = genre("motivational");

        List<Genre> genres1 = new ArrayList<>(Arrays.asList(genre1,genre2));
        List<Genre> genres2 = new ArrayList<>(Arrays.asList(genre2,genre3));
        List<Genre> genres3 = new ArrayList<>(Arrays.asList(genre3,genre4));
        List<Genre> genres4 = new ArrayList<>(Arrays.asList(genre4,genre5));
        log.info("loaded genres");

        Person person1 = person("Johny Cash");
        Person person2 = person("Jana Kratochvilova");
        Person person3 = person("Eminem Zamlada");
        Person person4 = person("Dr. Dre");
        Person person5 = person("Jožko Vajda");
        Person person6 = person("Daniel Dangl");
        Person person7 = person("Oliver Oswald");
        Person person8 = person("Jozef Pročko");

        List<Person> actorList1 = new ArrayList<>(Arrays.asList(person1,person2,person3,person4));
        List<Person> actorList2 = new ArrayList<>(Arrays.asList(person3,person4,person5,person6));
        List<Person> actorList3 = new ArrayList<>(Arrays.asList(person5,person6,person7,person8));
        List<Person> actorList4 = new ArrayList<>(Arrays.asList(person7,person8,person1,person2));
        log.info("loaded person - actors/directors");

        User user = user("Milanko Háčik","milanko@azet.sk",null, "milani$$",false,"heslo");
        User user2 = user("Milanko Uhrincik","milanko.uhrincik@azet.sk", LocalDate.of(1998, Month.JANUARY, 7),"xXx_destroyer_xXx",false,"hesloJeNaNic");
        User admin = user("Admin Administrátorský","admin@admin.com",null, "admin",true,"admin");
        log.info("loaded users");

        Movie movie1 = movie("Spiderman a nečakaná pasca",
                "Tento film je veľmi dojemný o živote spidermana.",imageTitle1,images1,1990,
                "SVK",150,genres1,actorList1,person1);

        Movie movie2 = movie("Aaj-Kalova pomsta",
                "Tento film je o tom ako Spidermanov život pokračuje, ale do cesty sa mu postaví Aaj Kal, postrach Indie.",
                imageTitle2,images2,1995,
                "USA",150,genres2,actorList2,person2);

        Movie movie3 = movie("Commando",
                "Celý film sa tam ľudia naháňajú a nikdy nevieš od ktorého rohu niekto vyskočí. Lebo sú tam samé rohy. " +
                        "Ako režisér sa preslávil Eminem Zamlada, vďaka tomuto filmu sa rozbehol v jeho kariére moderného speváka.",
                imageTitle3,images3,2010,
                "USA",300,genres3,actorList3,person3);

        Movie movie4 = movie("Jaguar",
                "Film o synovi upratovačky (Oliver Oswald), ktorý spozná školníka (Jozef Pročko) a spolu sa snažia zachrániť svet pred Jaguármi Type-F." +
                        "Režírovanie klasiky mal pod palcom Dr. Dre, ktorý chcel zopakovať úspech Eminema Zamlada.",
                imageTitle4,images4,2011,
                "SVK",300,genres4,actorList4,person4);

        log.info("loaded movies");

        UserRating userRating1 = userRating(user,movie1,6,10,5);
        UserRating userRating2 = userRating(user,movie2,6,6,6);
        UserRating userRating3 = userRating(user,movie3,10,10,10);
        UserRating userRating4 = userRating(user,movie4,1,10,10);
        log.info("loaded user ratings");
        log.info("everything loaded!");

    }

    private UserRating userRating(User user, Movie movie, int story, int visual, int actor){
        UserRating userRating = new UserRating();
        userRating.setStoryScore(story);
        userRating.setVisualScore(visual);
        userRating.setActorScore(actor);
        userRatingService.createUserRating(userRating,user,movie);
        return userRating;
    }

    private User user(String name, String email, LocalDate dateOfBirth, String nickname, Boolean isAdmin, String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        user.setNickName(nickname);
        user.setAdministrator(isAdmin);
        userService.registerUser(user,password);
        return user;
    }


    private Person person(String name){
        Person person = new Person();
        person.setName(name);
        personService.create(person);
        return person;
    }

    private Genre genre(String name){
        Genre genre = new Genre();
        genre.setName(name);
        genreService.createGenre(genre);
        return genre;
    }

    private Movie movie(String name, String description, Image titleImageName, List<Image> imageGallery,
                        int yearMade, String countryCode, int lengthMin, List<Genre> genres,
                        List<Person> persons, Person director){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setImageTitle(titleImageName);
        for (Image image:imageGallery) {
            movie.addToGallery(image);
        }
        movie.setYearMade(LocalDate.of(yearMade,1,1));
        movie.setCountryCode(countryCode);
        movie.setLengthMin(lengthMin);
        for (Genre genre: genres) {
            movie.addGenre(genre);
        }
        for (Person person: persons) {
            movie.addActor(person);
        }
        movie.setDirector(director);
        return movieService.create(movie);
    }

    private Image image(String filename, String mimeType) throws IOException {
        Image image = new Image();
        image.setImage(readImage(filename));
        image.setImageMimeType(mimeType);
        return imageService.create(image);
    }

    /**
     * Taken from
     * https://github.com/fi-muni/PA165/blob/master/eshop-sample-data/src/main/java/cz/muni/fi/pa165/sampledata/SampleDataLoadingFacadeImpl.java
     * @author Martin Kuba makub@ics.muni.cz
     *
     * @param filename String name of stored image
     * @return byte[]
     * @throws IOException
     */
    private byte[] readImage(String filename) throws IOException{
        try (InputStream is = this.getClass().getResourceAsStream("/" + filename)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
