<template>
  <q-page>
    <MovieDetail
      :id="movie.id"
      :name="movie.name" 
      :description="movie.description"
      :imageTitle="movie.imageTitle"
      :imageGallery="movie.gallery"
      :yearMade="movie.yearMade"
      :countryCode="movie.countryCode"
      :lengthMin="movie.lengthMin"
      :genres="movie.genres"
      :actors="movie.actors"
      :director="movie.director"
      :ratingAgg="movie.ratingAgg"
      :ratingUser="movie.ratingUser"  
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
    this.$axios.get("/movies/"+id)
      .then((response) => {
          this.movie = response.data.content
          this.links = response.data.links
          console.log("got response!")
      })
      .catch((e)=>{
          console.error(e);
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
      database:[{
                  id:1,
                  name: 'Tvoja mamka film 1',
                  description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  imageTitle: "https://cdn.quasar.dev/img/mountains.jpg",
                  gallery:[{
                      id: 1,
                      image: "https://cdn.quasar.dev/img/mountains.jpg",
                      imageMimeType: "jpg"},
                      {
                        id: 2,
                        image: "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2019/09/01093013/Endgame-Lead-1.jpg",
                        imageMimeType:"jgp",
                      }],
                  yearMade: 2020,
                  countryCode: "USA",
                  lengthMin:200,
                  genres : [{
                          id: 1,
                          name: 'Action'
                              },
                              {
                              id: 2,
                              name: 'Comedy'}],
                  actors: [{
                          id: 1,
                          name: 'Milanko Háčik'
                        },
                        {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                        {
                          id: 3,
                          name: 'Marika Gombitová'
                        }],
                  director: {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                  ratingAgg : 4.2,
                  ratingUser: {
                    id:1,
                    storyScore:4.5,
                    visualScore:4.5,
                    actorScore:4.5,
                    overallScore:9.3
                  },
                },
                {
                  id:2,
                  name: 'Tvoja mamka film 2',
                  description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  imageTitle: "https://cdn.quasar.dev/img/mountains.jpg",
                  gallery:[{
                      id: 1,
                      image: "https://cdn.quasar.dev/img/mountains.jpg",
                      imageMimeType: "jpg"},
                      {
                        id: 2,
                        image: "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2019/09/01093013/Endgame-Lead-1.jpg",
                        imageMimeType:"jgp",
                      }],
                  yearMade: 2020,
                  countryCode: "USA",
                  lengthMin:200,
                  genres : [{
                          id: 1,
                          name: 'Action'
                              },
                              {
                              id: 2,
                              name: 'Comedy'}],
                  actors: [{
                          id: 1,
                          name: 'Milanko Háčik'
                        },
                        {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                        {
                          id: 3,
                          name: 'Marika Gombitová'
                        }],
                  director: {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                  ratingAgg : 4.2,
                  ratingUser: {
                    id:1,
                    storyScore:4.5,
                    visualScore:4.5,
                    actorScore:4.5,
                    overallScore:9.3
                  },
                },
                {
                  id:3,
                  name: 'Tvoja mamka film 3',
                  description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  imageTitle: "https://cdn.quasar.dev/img/mountains.jpg",
                  gallery:[{
                      id: 1,
                      image: "https://cdn.quasar.dev/img/mountains.jpg",
                      imageMimeType: "jpg"},
                      {
                        id: 2,
                        image: "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2019/09/01093013/Endgame-Lead-1.jpg",
                        imageMimeType:"jgp",
                      }],
                  yearMade: 2020,
                  countryCode: "USA",
                  lengthMin:200,
                  genres : [{
                          id: 1,
                          name: 'Action'
                              },
                              {
                              id: 2,
                              name: 'Comedy'}],
                  actors: [{
                          id: 1,
                          name: 'Milanko Háčik'
                        },
                        {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                        {
                          id: 3,
                          name: 'Marika Gombitová'
                        }],
                  director: {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                  ratingAgg : 4.2,
                  ratingUser: {
                    id:1,
                    storyScore:4.5,
                    visualScore:4.5,
                    actorScore:4.5,
                    overallScore:9.3
                  },
                },
                {
                  id:4,
                  name: 'Tvoja mamka film 4',
                  description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  imageTitle: "https://cdn.quasar.dev/img/mountains.jpg",
                  gallery:[{
                      id: 1,
                      image: "https://cdn.quasar.dev/img/mountains.jpg",
                      imageMimeType: "jpg"},
                      {
                        id: 2,
                        image: "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2019/09/01093013/Endgame-Lead-1.jpg",
                        imageMimeType:"jgp",
                      }],
                  yearMade: 2020,
                  countryCode: "USA",
                  lengthMin:200,
                  genres : [{
                          id: 1,
                          name: 'Action'
                              },
                              {
                              id: 2,
                              name: 'Comedy'}],
                  actors: [{
                          id: 1,
                          name: 'Milanko Háčik'
                        },
                        {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                        {
                          id: 3,
                          name: 'Marika Gombitová'
                        }],
                  director: {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                  ratingAgg : 4.2,
                  ratingUser: {
                    id:1,
                    storyScore:4.5,
                    visualScore:4.5,
                    actorScore:4.5,
                    overallScore:9.3
                  },
                },
                {
                  id:5,
                  name: 'Tvoja mamka film 5',
                  description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  imageTitle: "https://cdn.quasar.dev/img/mountains.jpg",
                  gallery:[{
                      id: 1,
                      image: "https://cdn.quasar.dev/img/mountains.jpg",
                      imageMimeType: "jpg"},
                      {
                        id: 2,
                        image: "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2019/09/01093013/Endgame-Lead-1.jpg",
                        imageMimeType:"jgp",
                      }],
                  yearMade: 2020,
                  countryCode: "USA",
                  lengthMin:200,
                  genres : [{
                          id: 1,
                          name: 'Action'
                              },
                              {
                              id: 2,
                              name: 'Comedy'}],
                  actors: [{
                          id: 1,
                          name: 'Milanko Háčik'
                        },
                        {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                        {
                          id: 3,
                          name: 'Marika Gombitová'
                        }],
                  director: {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                  ratingAgg : 4.2,
                  ratingUser: {
                    id:1,
                    storyScore:4.5,
                    visualScore:4.5,
                    actorScore:4.5,
                    overallScore:9.3
                  },
                },
                {
                  id:6,
                  name: 'Tvoja mamka film 6',
                  description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                  imageTitle: "https://cdn.quasar.dev/img/mountains.jpg",
                  gallery:[{
                      id: 1,
                      image: "https://cdn.quasar.dev/img/mountains.jpg",
                      imageMimeType: "jpg"},
                      {
                        id: 2,
                        image: "https://s3-us-west-2.amazonaws.com/flx-editorial-wordpress/wp-content/uploads/2019/09/01093013/Endgame-Lead-1.jpg",
                        imageMimeType:"jgp",
                      }],
                  yearMade: 2020,
                  countryCode: "USA",
                  lengthMin:200,
                  genres : [{
                          id: 1,
                          name: 'Action'
                              },
                              {
                              id: 2,
                              name: 'Comedy'}],
                  actors: [{
                          id: 1,
                          name: 'Milanko Háčik'
                        },
                        {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                        {
                          id: 3,
                          name: 'Marika Gombitová'
                        }],
                  director: {
                          id: 2,
                          name: 'Jožko Vajda'
                        },
                  ratingAgg : 4.2,
                  ratingUser: {
                    id:1,
                    storyScore:4.5,
                    visualScore:4.5,
                    actorScore:4.5,
                    overallScore:9.3
                  },
                },
              ],
    }
  }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px  </style>
