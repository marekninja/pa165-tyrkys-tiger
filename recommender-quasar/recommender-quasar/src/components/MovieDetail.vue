<template>
    <div class="row full-height">
      <!--  style="background:#e68484" -->
        <div class="col-xs-12 col-sm-8 col-md-10 q-pa-sm">
        <!-- toto je lavy stlpec -->
        <!-- v tomto poradi veci
            nazov
            ratingAgg
            rating user
            galeria - resizable podla obrazka
            description
              -->
        <div class="row items-center justify-between">
          <span class="col-auto text-h2">
          {{name}}
          </span>
          <div class="col-auto">
            <div class="justify-end">
                <q-rating icon="star_border"
                    readonly
                    size='md'
                    icon-selected="star"
                    icon-half="star_half" 
                    v-model="ratingAgg" :max="10" color="accent" />
                <div class="text-caption q-mr-sm text-accent text-right text-weight-bold" >{{ratingAgg}}</div>
            </div>
          </div>

        </div>
        <q-responsive :ratio="4/3">
          <q-carousel
            v-model="currentImage"
            :fullscreen.sync="fullscreen"
            animated
            swipeable
            thumbnails
            padding
            infinite>
              <q-carousel-slide v-for="image in imageGallery" :key="image.id" :name="image.id" :img-src="image.image"/>
            <template v-slot:control>
            <q-carousel-control
              position="bottom-right"
              :offset="[18, 18]"
            >
              <q-btn
                push round dense color="white" text-color="primary"
                :icon="fullscreen ? 'fullscreen_exit' : 'fullscreen'"
                @click="fullscreen = !fullscreen"
              />
            </q-carousel-control>
          </template>
          </q-carousel>
        </q-responsive>
        <div class="text-body1">
          {{description}}
        </div>
      </div>
      <!--  style="background:blue" -->
      <div class="col-xs-12 col-sm-4 col-md-2 q-pa-sm">
        <!-- toto je pravy stlpec -->
        <!-- v tomto poradi
              title image
              zanre
              yearMade
              countryCode
              cast
                director
                herci
              -->
        <q-img
          :src="imageTitle"
          contain
        >
        </q-img>
        <div  class="row justify-start items-baseline q-gutter-sm q-pt-lg">
          <GenreBadge v-for="genre in genres" :key="genre.id"
                :id ="genre.id"
                :name="genre.name"
                />
        </div>
        <div class="text-subtitle1">
          <span class="text-weight-bold">
            Year of production: 
          </span>
          {{yearMade}}
        </div>
        <div class="text-subtitle1">
          <span class="text-weight-bold">
            Country of origin: 
          </span>
          {{countryCode}}
        </div>
        <div class="text-subtitle1">
          <span class="text-weight-bold">
            Director: 
          </span>
          {{director.name}}
        </div>
        <div class="text-subtitle1">
          <span class="text-weight-bold">
            Cast: 
          </span> 
          <span v-for="actor in actors" :key="actor.id"> {{actor.name}}, </span>
        </div>
      </div>
    </div>
</template>

<script>
import GenreBadge from './GenreBadge.vue'
export default {
  components: { GenreBadge },
  name: 'MovieDetail',  
  data() {
    return {
      currentImage:1,
      fullscreen: false,
    }
  },
  props: {
    id: {
      type: Number,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    description: {
      type: String,
      required: true
    },
    imageTitle: {
      type: Object,
      required: true
    },
    imageGallery: {
      type: Array,
      required: true
    },
    yearMade:{
        type: Number,
    },
    countryCode:{
        type:String,
    },
    lengthMin:{
        type:Number
    },
    genres: {
        type: Array,
        required: false
    },
    actors: {
        type: Array,
    },
    director: {
        type: Object,
    },
    ratingAgg: {
      type: Number,
      required: true
    },
    ratingUser: {
        type: Object
    }
    
  },
  methods: {
      clicked(){
          this.$q.notify({
            color: 'positive',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'clicked' + JSON.stringify(this.id)
            })
          this.$router.push("/movie")
      }
  }
}
</script>

