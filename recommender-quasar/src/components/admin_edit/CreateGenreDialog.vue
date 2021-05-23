<template>
  <q-page padding>
      <q-dialog 
      v-bind:value="value"
      v-on:input="$emit('input', $event)" >
      
      <q-card style="min-width: 350px">
        <q-card-section>
            <span class="text-h4">
                Create genre:
            </span>
        </q-card-section>
        <q-form @submit="submit" style="max-width: 400px" class="q-mx-auto">
        <q-card-section>
          <q-input
              class="q-my-sm"
              filled
              v-model="name"
              label="Name"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
        </q-card-section>
            <q-card-actions>
                <q-btn flat label="Cancel" v-close-popup color="negative"/>
                <q-btn flat label="Create" type="submit" color="positive" />
            </q-card-actions>
        </q-form>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'

export default {
  name: 'CreateGenre',
  props:{
    'value':{},
  },
  data () {
    return {
      name: null
    }
  },
  computed: {
  },
  methods: {
    submit(){    
      var create = {
        "name": this.name
      }
      console.log("genre to create: ", JSON.stringify(create))
      this.$axios.post('/genres/create',create)
      .then((response) => {
        NotifHelper.notifyPosit("Genre created! id: ",response.data.id)
        this.$router.go()
      })
      .catch((e)=>{
        NotifHelper.notifyNegatResp(e)
      })  
    },
    onRejected (rejectedEntries) {
      // Notify plugin needs to be installed
      // https://quasar.dev/quasar-plugins/notify#Installation
      this.$q.notify({
        type: 'negative',
        message: `${rejectedEntries.length} file(s) did not pass validation constraints`
      })
    }
  }
}
</script>
