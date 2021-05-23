<template>
  <q-dialog
      v-bind:value="value"
      v-on:input="$emit('input', $event)"
      style="{min-height: 400px; max-width: 300px}"
      >
    <q-card style="min-width: 300px">
      <q-card-section>
        <span class="text-h3">
          Set title image:
        </span>
      </q-card-section>
      <q-file
        v-model="filesImages"
        filled
        label="Set title image"
        accept=".jpg, image/*"
        @rejected="onRejected"
      >
        <template v-slot:prepend>
          <q-icon name="image" />
        </template>
          <template v-slot:after>
          <q-btn @click="sendTitleImage" round dense flat icon="send" color="positive" />
        </template>
      </q-file>
      <!-- <q-button -->
      <!-- <q-uploader
        url="http://localhost:8080/pa165/api/v1/movies/changetitle"
        label="Set title image"
        method="put"
        :form-fields="addMovieId"
        multiple
        :headers="[{name: 'Content-Type', value: 'application/json'}, {name: 'Accept', value: 'application/json'}]"
        accept=".jpg, image/*"
        @rejected="onRejected"
      /> -->
      </q-card>
    </q-dialog>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'

import getBase64 from '../../services/getBase64'

export default {
  name: 'ChangeTitleImageDialog',
  data () {
    return {
      filesImages: null,
    }
  },
  methods:{
    addMovieId: function(){
      return [{
        "movieId": this.movie.id
      }]
    },

    sendTitleImage (){
      if (this.filesImages == null) {
        NotifHelper.notifyNegat("Can not be empty!")
        return
      }
      console.log(this.filesImages)
      getBase64(this.filesImages)
      .then(data => {
        console.log('base64 conv: ', data) 
        var image = {
          "movieId" :this.movie.id,
          "image" : data,
          "imageMimeType": this.filesImages.type
        }
        this.$axios.put("/movies/changetitle",image)
        .then(resp => {
          console.log('image response: ',JSON.stringify(resp,null,1))
          NotifHelper.notifyPosit("Title changed!")
          this.$router.go()
        })
        .catch(e => {
          console.log('image error: ',JSON.stringify(e,null,1))
          NotifHelper.notifyNegatResp(e);
        })
      })
      .catch(e =>{
        this.$q.notify({
              color: 'negative',
              icon: 'error',
              message: 'Failed to convert file...'
            })
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
  },
  props: { 
    'value':{},
    movie: {
      type: Object,
      required: true
    },
  },
}
</script>
