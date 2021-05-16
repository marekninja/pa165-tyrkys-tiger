<template>
    <div class="row full-height" v-if="">
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
              <q-carousel-slide v-for="image in imageGallery" :key="image.id" :name="image.id" :img-src="image._links.self.href"/>
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
          :src="imageTitle._links.self.href"
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
        <q-btn color="positive" @click="doRating()">
          <q-icon left size="2em" :name="ratingIcon" />
          <div>{{ratingText}}</div>
        </q-btn>
      </div>
      <q-dialog v-model="ratingDialog">
      <q-card style="width: 300px" class="q-px-sm q-pb-md">
        <q-card-section>
          <div class="text-h6">Your rating</div>
        </q-card-section>

        <q-item-label header>Story score</q-item-label>
        <q-item dense>
          <q-item-section avatar>
            <q-icon name="auto_stories" />
          </q-item-section>
          <q-item-section>
            <q-slider label-always color="teal" v-model="ratingUserSafe.storyScore" :step="1" :min="0" :max="10"/>
          </q-item-section>
        </q-item>

        <q-item-label header>Visual score</q-item-label>
        <q-item dense>
          <q-item-section avatar>
            <q-icon name="videocam" />
          </q-item-section>
          <q-item-section>
            <q-slider label-always color="teal" v-model="ratingUserSafe.visualScore" :step="1" :min="0" :max="10"/>
          </q-item-section>
        </q-item>

        <q-item-label header>Actor score</q-item-label>
        <q-item dense>
          <q-item-section avatar>
            <q-icon name="group" />
          </q-item-section>
          <q-item-section>
            <q-slider label-always color="teal" v-model="ratingUserSafe.actorScore" :step="1" :min="0" :max="10"/>
          </q-item-section>
        </q-item>
        <q-item-label header>Overall score</q-item-label>
        <!-- <q-item dense>
          <q-item-section avatar>
            <q-icon name="grade" />
          </q-item-section>
          <q-item-section>
            <q-slider label-always color="teal" v-model="ratingUserSafe.overallScore" :step="1" :min="0" :max="10"/>
          </q-item-section>
        </q-item> -->
        <q-btn color="positive" @click="submitRating()" class="q-mx-auto">
          <q-icon left size="2em" name="send" />
          <div>Submit</div>
        </q-btn>
      </q-card>
    </q-dialog>
      
      <!--  -->

    </div>
</template>

<script>
import GenreBadge from './GenreBadge.vue'
import RatingDialog from './RatingDialog.vue'
export default {
  components: { GenreBadge, RatingDialog },
  name: 'MovieDetail',  
  created:function() {
    console.log(" created!")
    if (this.ratingUser){
      this.ratingText = 'Edit rating!'
      this.ratingIcon = 'edit'
    } else {
      this.ratingText = 'Add rating!'
      this.ratingIcon = 'thumbs_up_down'
    }
  },
  // beforeMount:function(){
  //   console.log("before mounted!")
  // },
      // updated:function(){
      //   console.log(" mounted!")
      //   this.currentImage=imageGallery[0].id
      // },
  // beforeCreate:function(){
  //   console.log("before create !")
  // },
  // beforeUpdate:function(){
  //   console.log("before update!")
  // },
  // updated : function(){
  //   currentImage=1
  //   console.log("update!")
  // },
  data() {
    return {
      currentImage:1,
      fullscreen: false,
      ratingIcon: 'thumbs_up_down',
      ratingText: 'Add rating!',
      ratingDialog: false,
    }
  },
  computed:{
    ratingUserSafe: function(){
      if (this.ratingUser){
        return this.ratingUser
      } 
      return {"storyScore":5,"actorScore":5, "visualScore":5}
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
      required: false
    },
    ratingUser: {
        type: Object,
        required: false
    }
    
  },
  methods: {
    doRating(){
      this.ratingDialog = !this.ratingDialog
    },
    submitRating(){
      this.$q.notify({
            color: 'positive',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'Your rating is saved 👍'
            })
    },
    // updateImage(){
    //   this.currentImage = this.imageGallery[0].id
    // }
  },
}
</script>

