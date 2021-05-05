<template>
  <q-page class="row full-height">
    <!--  style="background:#e68484" -->
    <div class="col-xs-12 col-sm-8 col-md-10 q-pa-sm">
      <!-- toto je lavy stlpec -->
      <!-- v tomto poradi veci
          nazov
          ratingAgg
          rating user
          galeria - resizable podla obrazka
          description
             -->
      <div class="row items-center justify-between">
        <span class="col-auto text-h2">
        {{movie.name}}
        </span>
        <div class="col-auto">
          <div class="justify-end">
              <q-rating icon="star_border"
                  readonly
                  size='md'
                  icon-selected="star"
                  icon-half="star_half" 
                  v-model="movie.ratingAgg" :max="10" color="accent" />
              <div class="text-caption q-mr-sm text-accent text-right text-weight-bold" >{{movie.ratingAgg}}</div>
          </div>
        </div>

      </div>
      <q-responsive :ratio="4/3">
        <q-carousel
          v-model="currentImage"
          :fullscreen.sync="fullscreen"
          animated
          swipeable
          thumbnails
          padding
          infinite>
            <q-carousel-slide v-for="image in movie.gallery" :key="image.id" :name="image.id" :img-src="image.image"/>
          <template v-slot:control>
          <q-carousel-control
            position="bottom-right"
            :offset="[18, 18]"
          >
            <q-btn
              push round dense color="white" text-color="primary"
              :icon="fullscreen ? 'fullscreen_exit' : 'fullscreen'"
              @click="fullscreen = !fullscreen"
            />
          </q-carousel-control>
        </template>
        </q-carousel>
      </q-responsive>
      <div class="text-body1">
        {{movie.description}}
      </div>
    </div>
    <!--  style="background:blue" -->
    <div class="col-xs-12 col-sm-4 col-md-2 q-pa-sm">
      <!-- toto je pravy stlpec -->
      <!-- v tomto poradi
            title image
            zanre
            yearMade
            countryCode
            cast
              director
              herci
             -->
      <q-img
        :src="movie.imageTitle"
        contain
      >
      </q-img>
      <div  class="row justify-start items-baseline q-gutter-sm q-pt-lg">
        <GenreBadge v-for="genre in movie.genres" :key="genre.id"
              :id ="genre.id"
              :name="genre.name"
              />
      </div>
      <div class="text-subtitle1">
        <span class="text-weight-bold">
          Year of production: 
        </span>
        {{movie.yearMade}}
      </div>
      <div class="text-subtitle1">
        <span class="text-weight-bold">
          Country of origin: 
        </span>
        {{movie.countryCode}}
      </div>
      <div class="text-subtitle1">
        <span class="text-weight-bold">
          Director: 
        </span>
        {{movie.director.name}}
      </div>
      <div class="text-subtitle1">
        <span class="text-weight-bold">
          Cast: 
        </span> 
        <span v-for="actor in movie.actors" :key="actor.id"> {{actor.name}}, </span>
      </div>
    </div>
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
    this.movie = this.database[id]
  },
  data () {
    return {
      currentImage:1,
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
