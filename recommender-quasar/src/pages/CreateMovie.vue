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
            <q-input
            class="q-my-sm"
              filled
              autogrow
              v-model="description"
              label="Description"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 255 || 'Can not be more than 255 chars' ]"
            />
<!--        </q-item> -->
<!--        <q-item> -->
          <q-file
          class="q-my-sm"
            v-model="imageTitleFiles"
            accept="image/jpeg"
            filled
            @input="encodeTitle(imageTitleFiles)"
            label="Set title image"
            @rejected="onRejected"
          >
          <template v-slot:prepend>
                <q-icon name="image" />
              </template>
          </q-file>
<!--        </q-item> -->
<!--        <q-item> -->
          <q-file
          class="q-my-sm"
            v-model="imageGalleryFiles"
            @input="encodeGallery(imageGalleryFiles)"
            filled
            multiple
            label="Set gallery images"
            accept="image/jpeg"
            @rejected="onRejected"
          >
              <template v-slot:prepend>
                <q-icon name="collections" />
              </template>
          </q-file>
<!--        </q-item> -->
<!--        <q-item class="row"> -->
            <q-date
            class="q-my-sm"
             :emit-immediately='true'
              mask="YYYY-MM-DD"
              v-model="yearMade"
              default-view="Years"
              :options="notFutureYear"
              :navigation-max-year-month="max_year_month"
            />
<!--        </q-item> -->
<!--        <q-item> -->
            <q-badge color="teal">
              Only year choice is supported: {{yearMade}}
            </q-badge>
<!--        </q-item> -->
<!--        <q-item> -->
            <q-input
            class="q-my-sm"
              filled
              v-model="countryCode"
              label="Country code"
              :rules="[val => val.length <= 3 || 'Please use maximum 3 characters', val => /^[A-Z]+$/.test(val) || 'Must contain only capital letters' ]"
            />
<!--        </q-item> -->
<!--        <q-item> -->
            <q-input
            class="q-my-sm"
              filled
              type="number"
              v-model="lengthMin"
              lazy-rules
              label="Length min"
            />
<!--        </q-item> -->
<!--        <q-item> -->
          <q-select
          class="q-my-sm"
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
<!--        </q-item> -->
<!--        <q-item> -->
            <q-select
            class="q-my-sm"
            v-model="actorsChoice"
            multiple
            behavior="menu"
            :options="persons"
            :option-value="opt => Object(opt) === opt && opt"
            :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
            :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
            emit-value
            map-options
            label="Actors"/>
<!--        </q-item> -->
<!--        <q-item> -->
            <q-select
            class="q-my-sm"
            v-model="directorChoice"
            behavior="menu"
            :options="persons"
            :option-value="opt => Object(opt) === opt && opt"
            :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
            :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
            emit-value
            map-options
            label="Director"/>
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

import getBase64 from '../services/getBase64'

export default {
  name: 'CreateMovie',
  created: function(){
    //TODO: load genres 
    //TODO: loade persons
  },
  data () {
    return {
      name: null,
      description: null,
      imageTitleFiles: null,
      imageGalleryFiles: null,
      yearMade: null,
      countryCode: null,
      lengthMin: null,
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
      genresChoice: null,
      persons: [{
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
      actorsChoice: null,
      directorChoice:null,
      titleImageObj:null,
      galleryImageObjs:null,
    }
  },
  computed: {
    max_year_month: function(){
      return new Date().toJSON().slice(0,7).replace(/-/g,'/')
    }
  },
  methods: {
    submit(){
      var year = new Date(this.yearMade).getFullYear()
      this.yearMade = year + "-01-01"
      
      var create = {
        "name": this.name,
        "description": this.description,
        "imageTitle": this.titleImageObj,
        "gallery": this.galleryImageObjs,
        "yearMade" : this.yearMade,
        "countryCode" : this.countryCode,
        "lengthMin" : this.lengthMin,
        "genres" :this.genresChoice,
        "actors" :this.actorsChoice,
        "director":this.directorChoice
      }

      console.log("movie to create: ", JSON.stringify(create))
      this.$axios.post('/movies/create',create)
      .then((response) => {
        NotifHelper.notifyPosit("Movie created! id: ",response.data.id)
        this.$router.push('/movie/'+response.data.id)
      })
      .catch((e)=>{
        NotifHelper.notifyNegatResp(e)
      })  
    },
    encodeTitle(title){
      getBase64(title)
      .then(data => {
        // console.log('base64 conv: ', data) 
        NotifHelper.notifyPosit("Title image converted")
        var image = {
          "image" : data,
          "imageMimeType": "image/jpeg"
        }
        this.titleImageObj = image;
      })
      .catch(e =>{
        this.$q.notify({
              color: 'negative',
              icon: 'error',
              message: 'Failed to convert file...'
            })
      })
    },
    encodeGallery(gallery){
      var galleryDataPromises = []

      gallery.forEach(element => {
        galleryDataPromises.push(getBase64(element))
      });

      Promise.all(galleryDataPromises)
      .then((vals) => {

        var galleryObjs = []
        vals.forEach(el => {
           var image = {
          "image" : el,
          "imageMimeType": "image/jpeg"
          }
          galleryObjs.push(image)
        })

        this.galleryImageObjs = galleryObjs
        NotifHelper.notifyPosit("Gallery converted")
      }) 
      .catch((e) =>{
        NotifHelper.notifyNegat("Couldn't convert gallery...")
      })

      // getBase64(title)
      // .then(data => {
      //   // console.log('base64 conv: ', data) 
      //   NotifHelper.notifyPosit('Converted title image')
      //   var image = {
      //     "movieId" :this.movie.id,
      //     "image" : data,
      //     "imageMimeType": this.filesImages.type
      //   }
      //   this.titleImageObj = image;
      // })
      // .catch(e =>{
      //   this.$q.notify({
      //         color: 'negative',
      //         icon: 'error',
      //         message: 'Failed to convert title image...'
      //       })
      // })
    },
    notFutureYear () {
        return new Date(this.yearMade) <= new Date()
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
