<template>
  <q-dialog 
      v-bind:value="value"
      v-on:input="$emit('input', $event)" >
      
      <q-form @submit="submit(genreCopy)">
        <q-card-section>
            <q-input
              filled
              v-model="genreCopy.name"
              label="Name"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
        </q-card-section>
        </q-form>
    </q-dialog>
</template>

<script>
import NotifHelper from '../../services/NotifHelper'

export default {
  name: 'EditGenreDialog',
  // props: [, "movie"],
  props: { 
    value:{
      type: Object,
      required: true
    },
  },
  data () {
    return {
      genreCopy: JSON.parse(JSON.stringify(this.value)),

    }
  },
  created: function(){
  },
  computed: {
  },
  methods: {
    submit(choice){
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
    watch: {
    value: function(){
      this.genreCopy = JSON.parse(JSON.stringify(this.value))
      console.log("editing genre: "+JSON.stringify(this.genreCopy,null,1))
    }
  }
}
</script>
