<template>
  <q-page>
    <div class="q-pa-md">
      <q-carousel
      animated
      v-model="currentImage"
      arrows
      navigation
      infinite
    >
      <q-carousel-slide v-for="image in movie.gallery" :key="image.id" :name="image.id" :img-src="image.image"/>
    </q-carousel>
    </div>

  </q-page>
</template>

<script>
import MovieDetail from "../components/MovieDetail"
export default {
  // TODO: sem patri ziskavanie recommended movies
  name: 'PageIndex',
  components: {
    MovieDetail: MovieDetail
  },
  data () {
    return {
      currentImage:1,
      movie:{
                id:1,
                name: 'Tvoja mamka',
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
                            name: 'Comedy'}]},
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
                }
                
                      
    }
  }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px</style>
