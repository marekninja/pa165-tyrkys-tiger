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

export default {
  name: 'DeleteGenreDialog',
  // props: [, "movie"],
  props: { 
    'value':{},
    movie_id: {
      type: Number,
      required: true
    },
    genres:{
        type: Array,
        required: true
    }
  },
  data () {
    return {
      genresChoice: null,
      genres: JSON.parse(JSON.stringify(this.genres))
    }
  },
  computed: {
  },
  methods: {
    submit(choice){
        choice['movieId'] = this.movie_id
        this.$axios.delete("/movies/genre",{data : choice, headers : {'Content-Type' : 'application/json'}})
            .then(resp => {
                NotifHelper.notifyPosit("Changed director to: "+choice.name)
                this.$router.go()
            })
            .catch(e =>{
                NotifHelper.notifyNegatResp(e);
            })
    },
  },
  watch: {
    value: function(){
      this.movieCopy = JSON.parse(JSON.stringify(this.movie))
    }
  }
}
</script>
