<template>
  <q-page class="flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md">
    <MovieList v-for="movie in movies" :key="movie.id" 
            :id="movie.id"
            :name="movie.name" 
            :description="movie.description"
            :image="movie.titleImage"
            :score="movie.overallScoreAgg"
            :genres="movie.genres"
            />
  </q-page>
</template>

<script>
import MovieList from "../components/MovieList"
export default {
  // TODO: sem patri ziskavanie recommended movies
  name: 'PageIndex',
  components: {
    MovieList: MovieList
  },
  data () {
    return {
      movies:[{
                id:1,
                name: 'Tvoja mamka',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 4.2,
                genres : [{
                        id: 1,
                        name: 'Action'
                            },
                            {
                            id: 2,
                            name: 'Comedy'}]},
            {
                id:2,
                name: 'Tvoja Mamka 2',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 3.6,
                genres : [{
                id: 1,
                name: 'Action'
                    },
                    {
                    id: 2,
                    name: 'Comedy'}]},
            {
            id:3,
            name: 'Velke srandy',
            description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
            titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
            overallScoreAgg : 4.0,
            genres : [{
                    id: 1,
                    name: 'Action'
                        },
                        {
                        id: 2,
                        name: 'Comedy'}]},
            {
                id:4,
                name: 'Zaujimavy film',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 4.5,
                genres : [{
                id: 1,
                name: 'Action'
                    },
                    {
                    id: 2,
                    name: 'Comedy'}]},
            {
                id:5,
                name: 'Tento neni velmi zaujimavy',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 1.3,
                genres : [
                    {
                    id: 1,
                    name: 'Action'},
                    {
                    id: 2,
                    name: 'Comedy'}]},
            {
                id:6,
                name: 'Da sa zvladnut',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 2.5,
                genres : [{
                id: 1,
                name: 'Action'
                    },
                    {
                    id: 2,
                    name: 'Comedy'
                    }]},
            ],
    }
  }

}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px</style>
