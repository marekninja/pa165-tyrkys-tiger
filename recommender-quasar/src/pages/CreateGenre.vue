<template>
  <q-page padding>
      <!-- <q-list style="min-width: 400px"> -->
        <!-- <div> -->

        <!-- </div> -->
        <q-form @submit="submit" style="max-width: 400px" class="q-mx-auto">
<!--        <q-item> -->
          <q-input
              class="q-my-sm"
              filled
              v-model="name"
              label="Name"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
<!--        </q-item> -->
<!--        <q-item> -->
          <q-btn flat label="Cancel" v-close-popup color="negative"/>
          <q-btn flat label="Create" type="submit" color="positive" />
<!--        </q-item> -->
        </q-form>
      <!-- </q-list> -->
  </q-page>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'

export default {
  name: 'CreateGenre',
  created: function(){
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
