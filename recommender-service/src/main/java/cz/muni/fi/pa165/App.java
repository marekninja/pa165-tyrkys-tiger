package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Movie;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Movie movie = new Movie();
        movie.setName("Velka sranda");

        System.out.println(movie);
        System.out.println( "Hello World!" );
    }
}
