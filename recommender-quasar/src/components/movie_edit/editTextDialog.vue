<template>
  <q-dialog 
      v-bind:value="value"
      v-on:input="$emit('input', $event)" >
      
      <q-card style="min-width: 350px">
      <q-form @submit="submitVal(movieCopy)">
         <q-card-section>
            <q-input
              filled
              v-model="movieCopy.name"
              label="Name"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
          </q-card-section>
          <q-card-section>
            <q-input
              filled
              autogrow
              v-model="movieCopy.description"
              label="Description"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 255 || 'Can not be more than 255 chars' ]"
            />
          </q-card-section>
          <q-card-section class="column justify-center">
             <q-date
             :emit-immediately='true'
              mask="YYYY-MM-DD"
              v-model="movieCopy.yearMade"
              default-view="Years"
              :options="notFutureYear"
              :navigation-max-year-month="max_year_month"
            />
            <q-badge color="teal">
              Only year choice is supported: {{movieCopy.yearMade}}
            </q-badge>
          </q-card-section>
          <q-card-section>
            <q-input
              filled
              v-model="movieCopy.countryCode"
              label="Country code"
              lazy-rules
              :rules="[val => val.length <= 3 || 'Please use maximum 3 characters', val => /^[A-Z]+$/.test(val) || 'Must contain only capital letters' ]"
            />
          </q-card-section>
          <q-card-section>
            <q-input
              filled
              type="number"
              v-model="movieCopy.lengthMin"
              lazy-rules
              label="Length min"
            />
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
  name: 'EditTextDialog',
  // props: [, "movie"],
  props: { 
    'value':{},
    movie: {
      type: Object,
      required: true
    },
  },
  data () {
    return {
      movieCopy: JSON.parse(JSON.stringify(this.movie))
    }
  },
  computed: {
    max_year_month: function(){
      return new Date().toJSON().slice(0,7).replace(/-/g,'/')
    }
  },
  methods: {
    submitVal(movieCopy){
      var year = new Date(movieCopy.yearMade).getFullYear()
      movieCopy.yearMade = year + "-01-01"
      var update = {
        "id":movieCopy.id,
        "name": movieCopy.name,
        "description": movieCopy.description,
        "yearMade" : movieCopy.yearMade,
        "countryCode" : movieCopy.countryCode,
        "lengthMin" : movieCopy.lengthMin,
      }
      this.$axios.put('/movies/'+movieCopy.id,update)
      .then((response) => {
        NotifHelper.notifyPosit("Update sent!")
        this.$router.go()
      })
      .catch((e)=>{
        NotifHelper.notifyNegatResp(e)
      })

      
    },
    notFutureYear () {
      return new Date(this.movieCopy.yearMade) <= new Date()
    },
  },
  watch: {
    value: function(){
      this.movieCopy = JSON.parse(JSON.stringify(this.movie))
    }
  }
}
</script>
