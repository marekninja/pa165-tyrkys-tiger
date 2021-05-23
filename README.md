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

## 2. Milestone

We implemented Services and Facades.
Also we have ~~MANY~~ just the right amount of tests.

2 non-trivial functions:
1.  filtering movies based on parameters
2.  recommendation of movies based on seen (rated) movies
    *  recommends movies from most favorite Genres, which user hasn't seen

## 3. Milestone

### Run webapp:

`mvn clean install -DskipTests && cd recommender-rest && mvn`

webapp route: http://localhost:8080/pa165/ 

webapp author: Marek Petrovič

### REST:

Route: http://localhost:8080/pa165/rest/

**Movie:** author: Marek Petrovič

*   GET /movies/  - returns all movies

produces = "application/hal+json"


*   POST /movies/browse - returns movies by filter

produces = "application/hal+json"

browseFilter

example json:
`{
"genreDTOList": [
{
"id": 3,
"name": "animated"
},
{
"id": 2,
"name": "romantic"
}
],
"personDTOList": [
{
"id": 3,
"name": "Eminem Zamlada"
}
],
"movieName": "Aaj",
"yearMade": null,
"countryCode": "USA"
}`


*   GET /movies/{id}  - returns detail of movie

produces = "application/hal+json"

*   POST /movies/recommended - returns recommended movies of user

consumes = "application/json"

produces = "application/hal+json"

example json:
`{
"administrator": false,
"dateOfBirth": null,
"email": "milanko@azet.sk",
"id": 1,
"name": "Milanko Háčik",
"nickName": "milani$$",
"password": "asdsad"
}`

*   POST /movies/create - creates movie and returns created movie

consumes = "application/json"

produces = "application/hal+json"

example json:
`
{
"name": "Smradľavý Milan a Janko 2",
"description": "Rodinná komédia pre všetky vekové kategórie. Zasmeje sa aj dedko aj babky a vnučka, vnúčik moc nie.",
"imageTitle" : {
"image": "base64 encoded image"
"imageMimeType": "image/jpeg"
},
"gallery" : [
{
"image": "base64 encoded image"
"imageMimeType": "image/jpeg"
},
{
"image": "base64 encoded image"
"imageMimeType": "image/jpeg"
}
],
"yearMade" :"1990-01-01",
"countryCode" : "SVK",
"lengthMin" : 250,
"genres": [
{
"id": 2,
"name": "romantic"
},
{
"id": 3,
"name": "animated"
}
],
"actors" : [
{
"id": 3,
"name": "Eminem Zamlada"
},
{
"id": 6,
"name": "Daniel Dangl"
},
{
"id": 4,
"name": "Dr. Dre"
}
],
"director": {
"id": 2,
"name": "Jana Kratochvilova"
}
}
`

*   PUT /movies/{movieId} - updates textual attributes of movie

consumes = "application/json"

example json:
`
{
"id":10,
"name": "Smradľavý Milan a Janko 256",
"description": "POPIS Rodinná komédia pre všetky vekové kategórie. Zasmeje sa aj dedko aj babky a vnučka, vnúčik moc nie.",
"yearMade" :"2010-01-01",
"countryCode" : "USA",
"lengthMin" : 69,
}
`

*   DELETE /movies/{id} - deletes movie


*   PUT /movies/changetitle - changes title image of movie

consumes = "application/json"

example json:
`
{
"image" : "base64 encoded image"
"imageMimeType": "image/png",
"movieId" : "10"
}
`

*   POST /movies/addimage - add image to movie gallery

consumes = "application/json"

example json:
`
{
"image" : "base64 encoded image"
"imageMimeType": "image/png",
"movieId" : "10"
}
`

*   DELETE /movies/image/{id} - removes image from movie gallery

*   POST /movies/actor - adds actor to movie

consumes = "application/json"

example json:
`{
"id": 15,
"name": "Eminem Zamlada",
"movieId": 3
}
`

*   DELETE /movies/actor - remove actor from movie

consumes = "application/json"

example json:
`{
"id": 15,
"name": "Eminem Zamlada",
"movieId": 3
}
`

*   POST /movies/genre - adds genre to movie

consumes = "application/json"

example json:
`{
"id": 100,
"name": "motivational",
"movieId": 2
}`

*   DELETE /movies/genre - removes genre from movie

*   POST /movies/director - changes director of movie

consumes = "application/json"

example json:
`{
"id": 15,
"name": "Eminem Zamlada",
"movieId": 3
}
`

**Image:** author: Marek Petrovič

GET /images/{id} - returns entity of image with id
produces = "application/json"

GET /images/url/{id} - returns image data (not entity, just image)

**User:** author: Matej Turek

*   GET /users - returns all users

produces = "application/hal+json"

*   GET /users/{id} - returns user with specified id

produces = "application/hal+json"

*   GET /users/email/{email} - returns user with specified email

produces = "application/hal+json"

*   GET /users/nickname/{nickName} - returns user with specified nickName

produces = "application/hal+json"

*   PUT /users/update - updates user

consumes = "application/json"
produces = "application/hal+json"

example input json:
`{
    "id": "1",
    "nickName": "admidfdfgn",
    "password": "heslo",
    "name": "Admin Administrátorský",
    "email": "admin@adddfmin.com",
    "dateOfBirth": null,
    "administrator": true
}`

*   DELETE /users/{id} - deletes the user with specified id

*   GET /users/authenticate - authenticates the user

consumes = "application/json"
produces = "application/hal+json"

example input json:
`{
    "nickName": "milani$$",
    "password": "heslo"
}`

*   POST /users/registration - registrates the user

consumes = "application/json"
produces = "application/hal+json"

example input json:
`{
    "nickName": "accountName",
    "password": "heslo",
    "name": "Milanko",
    "email": "milo@azevfgt.sk",
    "dateOfBirth": null,
    "administrator": false
}`

**UserRating:** author: Matej Turek

*   GET /ratings/{id} - returns rating with specified id

produces = "application/hal+json"

*   POST /ratings/create - updates rating

consumes = "application/json"
produces = "application/hal+json"

example input json:
`{
    "userId": 1,
    "movieId": 2,
    "storyScore": 5,
    "visualScore": 5,
    "actorScore": 5,
    "overallScore": 5
}`

*   PUT /ratings/update - updates rating

consumes = "application/json"
produces = "application/hal+json"

example input json:
`{
    "id": 1,
    "userId": 1,
    "movieId": 2,
    "storyScore": 5,
    "visualScore": 5,
    "actorScore": 5,
    "overallScore": 5
}`

*   DELETE /ratings/{id} - deletes the rating with specified id
