package cz.muni.fi.pa165.genre;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Simple test of comparison behaviour
 * @author Marek Petrovič
 */
public class GenreAndRatingTest {

    private GenreAndRating correctSmall;
    private GenreAndRating correctBig;
    private GenreAndRating correctNull;


    @BeforeMethod
    public void before(){
        Genre genre = new Genre();
        genre.setName("name");

        this.correctSmall = new GenreAndRating(genre,0f);
        this.correctBig = new GenreAndRating(genre,10f);
        this.correctNull = new GenreAndRating(genre,null);
    }

    @Test
    public void compareTest(){
        //small - big = negative
        Assert.assertEquals(correctSmall.compareTo(correctBig),-1);

        //big - small = positive
        Assert.assertEquals(correctBig.compareTo(correctSmall),1);

        //all should be greater than null
        Assert.assertEquals(correctSmall.compareTo(correctNull),1);
        Assert.assertEquals(correctNull.compareTo(correctSmall),-1);

        //null and null equal
        Assert.assertEquals(correctNull.compareTo(correctNull),0);

        Assert.assertEquals(correctSmall.compareTo(null),1);
    }
}
