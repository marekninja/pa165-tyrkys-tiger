<template>
  <q-dialog 
      v-bind:value="value"
      v-on:input="$emit('input', $event)" >
      
      <q-card style="min-width: 350px">
        <q-card-section>
            <span class="text-h4">
                {{ headerComp }}
            </span>
        </q-card-section>
        <q-form @submit="submit(personChoice)">
            <q-card-section>
                <q-select
                v-model="personChoice"
                behavior="menu"
                :options="actors"
                :option-value="opt => Object(opt) === opt && opt"
                :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
                :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
                emit-value
                map-options
                label="People"/>
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
import routes from 'src/router/routes'

import NotifHelper from '../../services/NotifHelper'

export default {
  name: 'EditCastDialog',
  // props: [, "movie"],
  props: { 
    'value':{},
    movie_id: {
      type: Number,
      required: true
    },
    changeDirector:{
        type: Boolean,
        required: true
    }
  },
  data () {
    return {
      personChoice: null,
      actors: null,
      // [{
      //     id: 1,
      //     name: 'Milanko Háčik'
      //   },
      //   {
      //     id: 2,
      //     name: 'Jožko Vajda'
      //   },
      //   {
      //     id: 3,
      //     name: 'Marika Gombitová'
      //   }
      //   ],
    }
  },
  computed: {
      headerComp(){
          if (this.changeDirector){
              return "Choose director:"
          } else {
              return "Add to cast:"
          }
      }
  },
  created: function(){
    this.getActors()
  },
  methods: {
    getActors(){
      this.$axios.get("/persons")
        .then(resp => {
            this.actors = resp.data._embedded.personDTOList
        })
        .catch(e => {
          NotifHelper.notifyNegatResp(e)
        })
    },
    submit(choice){
        choice['movieId'] = this.movie_id

        if (this.changeDirector){
            this.$axios.post("/movies/director",choice)
            .then(resp => {
                NotifHelper.notifyPosit("Changed director to: "+choice.name)
                this.$router.go()
            })
            .catch(e =>{
                NotifHelper.notifyNegatResp(e);
            })
        } else {
            this.$axios.post("/movies/actor",choice)
            .then((resp)=>{
                NotifHelper.notifyPosit("Actor " + choice.name + " added!")
                this.$router.go()
            })
            .catch(e => {
                NotifHelper.notifyNegatResp(e)
            })
        }
    },
  },
}
</script>
