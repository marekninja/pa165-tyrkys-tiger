# pa165-tyrkys-tiger
PA165 group project. Spring App.

## Movie Recommendation System

The web application is a catalogue of movies of different genres. Each movie has a title, description, film director, and list of actors, and image(s) from the main playbill. Each movie is categorized in one or more genres. Only the administrator can create, remove and update the list of movies. Users can rate the movies according to different criteria (e.g how novel are the ideas of the movie, their final score, etc…). The main feature of the system is that users can pick one movie and get the list of similar movies and / or movies that were liked the most by other users watching the same movie (no need of complex algorithms, some simple recommendation is enough!).

Authors:
1.  Marek Petrovič, marekninja
2.  Matej Turek, Tmatej
3.  Peter Mravec, xmravec3

## 1. Milestone
We implemented 3 entities, corresponding DAOs and tests. 

Other implementations are just preparations and should not be evaluated in this milestone.

Class Diagram and Use case diagram are in project wiki.

**Entities:**
1.  Movie, MovieDao, MovieDaoImpl
    *   author: Marek Petrovič
2.  Person, PersonDao, PersonDaoImpl
    *   author: Peter Mravec
3.  User, UserDao, UserDaoImpl
    *   author: Matej Turek

**Tests:**
1.  MovieTest, MovieDaoTest
    *   author: Peter Mravec
2.  PersonTest, PersonDaoTest
    *   author: Matej Turek
3.  UserTest, UserDaoTest
    *   author: Marek Petrovič



