<template>
  <q-dialog 
      v-bind:value="value"
      v-on:input="$emit('input', $event)" >
      
      <q-card style="min-width: 350px">
        <q-card-section>
            <span class="text-h4">
                Add genre:
            </span>
        </q-card-section>
        <q-form @submit="submit(genresChoice)">
            <q-card-section>
                <q-select
                        v-model="genresChoice"
                        :options="genres"
                        :option-value="opt => Object(opt) === opt && opt"
                        :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
                        :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
                        emit-value
                        map-options
                        behavior="menu"
                        label="Genres"/>
            </q-card-section>
            <q-card-actions align="right" class="text-primary">
            <q-btn flat label="Cancel" v-close-popup color="negative"/>
            <q-btn flat label="Submit" type="submit" color="positive" />
            </q-card-actions>
        </q-form>
      </q-card>
    </q-dialog>
</template>

<script>
import NotifHelper from '../../services/NotifHelper'
import PersonChoice from '../choice/personChoice.vue'

export default {
  components: { PersonChoice },
  name: 'AddGenreDialog',
  // props: [, "movie"],
  props: { 
    'value':{},
    movie_id: {
      type: Number,
      required: true
    },
  },
  data () {
    return {
      genresChoice: null,
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
    }
  },
  computed: {
  },
  methods: {
    getGenres(){
        //TODO: load genres via axios
    },
    submit(choice){
        choice['movieId'] = this.movie_id
        this.$axios.post("/movies/genre",choice)
            .then(resp => {
                NotifHelper.notifyPosit("Changed director to: "+choice.name)
                this.$router.go()
            })
            .catch(e =>{
                NotifHelper.notifyNegatResp(e);
            })
    },
  },
}
</script>
