<template>
  <q-page>
    <div>
    <q-expansion-item
    expand-separator
    icon="tune"
    label="Filters"
    >
    <!-- TODO: mozno ci sa tie navolene veci aby sa nemazali -->
        <q-card class="q-pa-md">
            <q-card-section>
                Choose by which parameters you want to filter movies:
            </q-card-section>
            <q-form       
                @reset="onReset" class="row wrap justify-around items-baseline q-gutter-sm">       
                <!-- :option-value="opt => Object(opt) === opt && 'id' in opt ? opt.id : null" -->
                <div class="col-4">
                    <q-select
                        v-model="genresChoice"
                        multiple
                        :options="genres"
                        :option-value="opt => Object(opt) === opt && opt"
                        :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
                        :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
                        emit-value
                        map-options
                        behavior="menu"
                        label="Genres"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ genresChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4" >
                    <q-select
                        v-model="personsChoice"
                        multiple
                        behavior="menu"
                        :options="persons"
                        :option-value="opt => Object(opt) === opt && opt"
                        :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
                        :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
                        emit-value
                        map-options
                        label="People"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ personsChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4">
                    <q-input 
                    ref="movieNameInput"
                    v-model="movieNameChoice" 
                    lazy-rules="ondemand" 
                    label="Movie name" 
                    :rules="[val => val.length <= 50 || 'Please use maximum 50 characters']"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ movieNameChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4">
                    Production year
                    <q-slider 
                        label 
                        v-model="yearMadeChoice" 
                        :min="yearMin" :max="yearMax"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ yearMadeChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4">
                    <q-input
                    ref="countryCodeInput"
                    clearable 
                    v-model="countryCodeChoice" 
                    label="Country code" 
                    lazy-rules="ondemand"
                    :rules="[val => val.length <= 3 || 'Please use maximum 3 characters', val => /^[A-Z]+$/.test(val) || 'Must contain only capital letters' ]"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ countryCodeChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4 order-last">
                    <q-btn label="Submit" type="submit" @click="onSubmit"   color="positive"/>
                    <q-btn label="Reset" type="reset" color="negative" flat class="q-ml-sm" />
                </div>
            </q-form>
        </q-card>
    </q-expansion-item>
    </div>
    <div class="flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md">
        <MovieList v-for="movie in movies" :key="movie.id" 
            :id="movie.id"
            :name="movie.name" 
            :description="movie.description"
            :image="movie._embedded.titleImage._links.self.href"
            :score="movie.overallScoreAgg"
            :genres="movie.genres"
            />
    </div>
  </q-page>
</template>

<script>

import MovieList from "../components/MovieList";
import Axios from 'axios';
import NotifHelper from 'src/services/NotifHelper';

export default {
  name: 'PageIndex',
  data () {
    return {
      movieNameChoice: null,
      genresChoice: null,
      personsChoice: null,
      yearMadeChoice: null,
      countryCodeChoice: null,
      links: null,
      movies:[{
                "name": "Spiderman a nečakaná pasca",
                "description": "Tento film je veľmi dojemný o živote spidermana.",
                "overallScoreAgg": 7,
                "genres": [
                    {
                    "id": 2,
                    "name": "romantic"
                    },
                    {
                    "id": 1,
                    "name": "action"
                    }
                ],
                "id": 1,
                "_links": {
                    "self": {
                    "href": "http://localhost:8080/pa165/api/v1/movies/1"
                    },
                    "browse": {
                    "href": "http://localhost:8080/pa165/api/v1/movies"
                    }
                },
                "_embedded": {
                    "titleImage": {
                    "id": 1,
                    "image": "tu je strasne dlhy binarny string",
                    "imageMimeType": "image/jpeg",
                    "_links": {
                        "self": {
                        "href": "http://localhost:8080/pa165/api/v1/images/url/1"
                        }
                    }
                    }
                }
                }
            ],
      persons:[{
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
        }
        ],
      genres: [
          {
          id: 1,
          name: 'Action'
        },
        {
          id: 2,
          name: 'Comedy'
        },
        {
          id: 3,
          name: 'Sci-fi'
        },
        {
          id: 4,
          name: 'A tak ďalej'
        },
        ],
        yearMin: 1900,
        yearMax: new Date().getFullYear(),
    }
  },

  created: function(){
      this.getAll()
  },
   methods: {
    getAll(){   
    this.$axios.get("/movies")
      .then((response) => {
          this.fillFromResponse(response)
          console.log("got response!")
        //   console.log(JSON.stringify(response,null,2))
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
    fillFromResponse(obj){
        if (obj.data // 👈 null and undefined check
                && Object.keys(obj.data).length === 0 && obj.data.constructor === Object){
            NotifHelper.notifyNegat('No results found!')
        } else {
            this.movies = obj.data._embedded.halRepresentationModelList
            this.links = obj.data._links
            
            
        }

        
    },
    onSubmit () {
        //TODO: posielanie a cakanie na odpoved cez AXIOS 
        // send ParametersDTO format here
        var nameError = false
        // console.log("moviename", this.movieNameChoice)
        if (this.movieNameChoice != null){
            this.$refs.movieNameInput.validate()
            nameError = this.$refs.movieNameInput.hasError
            // console.log("moviename nameerror", nameError)
            //TODO: ked zadam spravny tak hadze hasError undefined  
            
        }
        var codeError = false
        // console.log("countrycode",this.countryCodeChoice)
        if (this.countryCodeChoice != null){
            this.$refs.countryCodeInput.validate()
            codeError = this.$refs.countryCodeInput.hasError
            // console.log("countrycode codeerror",codeError)
        }
        if ( nameError || codeError){
            this.$q.notify({
            color: 'negative',
            textColor: 'white',
            icon: 'error',
            message: 'Can not submit invalid form 😢'
            })
        } else {
            var ParametersDTO= {
            genreDTOList: this.genresChoice,
            personDTOList: this.personsChoice,
            movieName: this.movieNameChoice,
            yearMade: this.yearMadeChoice,
            countryCode: this.countryCodeChoice
            }

            this.$axios.post("/movies/browse",ParametersDTO)
            .then((response) =>{
                this.fillFromResponse(response)
                this.$q.notify({
                color: 'positive',
                textColor: 'white',
                icon: 'cloud_done',
                message: 'Submitted' + JSON.stringify(ParametersDTO)
                })
            })
            .catch((error)=>{
                 this.$q.notify({
                color: 'negative',
                textColor: 'white',
                icon: 'error',
                message: error
                })
            })

            
        }
        
    },

    onReset () {
      this.movieNameChoice = null,
      this.genresChoice = null,
      this.personsChoice = null,
      this.yearMadeChoice = null
      this.countryCodeChoice = null
      this.getAll()
    }
   },
   components: {
       MovieList: MovieList
   }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px</style>
