<template>
  <q-page>
    <MovieDetail
      ref="MovieDetail"
      :id="movie.id"
      :name="movie.name" 
      :description="movie.description"
      :yearMade="movie.yearMade"
      :countryCode="movie.countryCode"
      :lengthMin="movie.lengthMin"
      :genres="movie.genres"
      :actors="movie.actors"
      :director="movie.director"
      :ratingAgg="movie.ratingAgg"
      :ratingUser="movie.ratingUser"
      :imageTitle="movie._embedded.titleImage"
      :imageGallery="movie._embedded.imageGallery._embedded.imageDetailDTOList"
      :movie = "movie"
    />
  </q-page>
</template>

<script>
import MovieDetail from "../components/MovieDetail"
import GenreBadge from "../components/GenreBadge"
export default {
  // TODO: sem patri ziskavanie recommended movies
  name: 'PageIndex',
  components: {
    MovieDetail: MovieDetail,
    GenreBadge: GenreBadge
  },
  created: function() {
    let id = this.$router.currentRoute.params.id
    this.$q.loading.show()
    this.$axios.get("/movies/"+id)
      .then((response) => {
          this.movie = response.data
          this.links = response.data._links
          console.log("got response!")
          this.$q.loading.hide()
      })
      .catch((e)=>{
          console.error(e);
          this.$q.loading.hide()
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading failed',
            icon: 'report_problem'
          })
          
      })
  },
  data () {
    return {
      currentImage:1,
      links: null,
      movie: null,  
      fullscreen: false,
      exampleMovie:{
                    "id": 2,
                    "name": "Aaj-Kalova pomsta",
                    "description": "Tento film je o tom ako Spidermanov život pokračuje, ale do cesty sa mu postaví Aaj Kal, postrach Indie.",
                    "yearMade": {
                        "year": 1995,
                        "month": "JANUARY",
                        "monthValue": 1,
                        "dayOfMonth": 1,
                        "dayOfYear": 1,
                        "chronology": {
                            "id": "ISO",
                            "calendarType": "iso8601"
                        },
                        "dayOfWeek": "SUNDAY",
                        "era": "CE",
                        "leapYear": false
                    },
                    "countryCode": "USA",
                    "lengthMin": 150,
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
                    "actors": [
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
                        },
                        {
                            "id": 5,
                            "name": "Jožko Vajda"
                        }
                    ],
                    "director": {
                        "id": 2,
                        "name": "Jana Kratochvilova"
                    },
                    "ratingAgg": null,
                    "ratingUser": null,
                    "_embedded": {
                        "titleImage": {
                            "id": 2,
                            "image": "binarny string",
                            "imageMimeType": "image/jpeg",
                            "_links": {
                                "self": {
                                    "href": "http://localhost:8080/pa165/api/v1/images/url/2"
                                }
                            }
                        },
                        "imageGallery": {
                            "_embedded": {
                                "imageDetailDTOList": [
                                    {
                                        "id": 7,
                                        "image": "binarny string",
                                        "imageMimeType": "image/jpeg",
                                        "_links": {
                                            "self": {
                                                "href": "http://localhost:8080/pa165/api/v1/images/url/7"
                                            }
                                        }
                                    },
                                    {
                                        "id": 8,
                                        "image": "binarny string",
                                        "imageMimeType": "image/jpeg",
                                        "_links": {
                                            "self": {
                                                "href": "http://localhost:8080/pa165/api/v1/images/url/8"
                                            }
                                        }
                                    }
                                ]
                            }
                        }
                    },
                    "_links": {
                        "self": {
                            "href": "http://localhost:8080/pa165/api/v1/movies/2"
                        },
                        "browse": {
                            "href": "http://localhost:8080/pa165/api/v1/movies"
                        }
                    }
                },
    }
  }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px  </style>
