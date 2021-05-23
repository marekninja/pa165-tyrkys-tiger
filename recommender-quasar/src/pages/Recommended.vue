<template>
  <q-page class="flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md">
        <MovieList v-for="movie in movies" :key="movie.id"
            :id="movie.id"
            :name="movie.name"
            :description="movie.description"
            :image="movie._embedded.titleImage._links.self.href"
            :score="movie.overallScoreAgg"
            :genres="movie.genres"
            />
  </q-page>
</template>

<script>
import MovieList from "../components/MovieList"
export default {
  // TODO: musi byt user prihlaseny
  name: 'PageIndex',
  components: {
    MovieList: MovieList
  },
  data () {
    return {
      movies: null,
      links:null,
      moviesExample:[{
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
  },
  created: function(){
    var user = this.$store.getters['auth/userFull']
    console.log(JSON.stringify(user,null,1))
    this.$axios.post("/movies/recommended", {data : user, headers : {'Content-Type' : 'application/json'}})
    .then((response)=>{
      console.log("response",JSON.stringify(response,null,1))
      if (Object.keys(response.data).length === 0 && response.data.constructor === Object){
        this.$axios.get("/movies")
        .then((response)=>{
          this.fillFromResponse(response)
        })
        .catch((error)=>{
          this.notifyError(error)
        })
      } else{
        this.fillFromResponse(response)
      }
    })
    .catch((error)=>{
      console.log("error",error)
      var errorText=null
      if (error.response){
        errorText = error.response.status
      } else{
        errorText = error
      }
      this.notifyError(errorText)
    })
  },
  methods: {
    fillFromResponse(response){
        this.movies = response.data._embedded.halRepresentationModelList
        this.links = response.data._links
    },
    notifyError(error){
      this.$q.notify({
      color: 'negative',
      textColor: 'white',
      icon: 'error',
      message: error
      })
    }
  }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px</style>
