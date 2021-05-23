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
            <!--  -->
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
              <q-btn
                v-if="isAdmin"
                push round dense color="white" text-color="negative"
                icon="delete"
                @click="deleteCurrentImage"
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
        <q-btn
                v-if="isAdmin"
                push round dense color="white" text-color="negative"
                icon="add"
                @click="addGenre"
              />
            <AddGenreDialog
                v-model="add_genre_dialog"
                :movie_id="this.movie.id"
            />
        <q-btn
                v-if="isAdmin"
                push round dense color="white" text-color="negative"
                icon="delete"
                @click="deleteGenre"
              />
            <DeleteGenreDialog
                v-model="delete_genre_dialog"
                :genres ="this.genres"
                :movie_id="this.movie.id"
            />
        <div class="text-subtitle1">
          <span class="text-weight-bold">
            Year of production: 
          </span>
          {{yearMadeSafe}}
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
          <div>
            {{director.name}}
            <q-btn
                v-if="isAdmin"
                push round dense color="white" text-color="negative"
                icon="edit"
                @click="editDirector"
              />
            <EditCastDialog
                v-model="edit_director_dialog"
                :movie_id="this.movie.id"
                :changeDirector="true"
            />
          </div>
        </div>
        <div class="text-subtitle1">
          <div class="text-weight-bold">
            Cast: 
            <q-btn
                v-if="isAdmin"
                push round dense color="white" text-color="negative"
                icon="add"
                @click="editCast"
              />
            <EditCastDialog
                v-model="edit_cast_dialog"
                :movie_id="this.movie.id"
                :changeDirector="false"
            />
          </div> 
          <div v-for="actor in actors" :key="actor.id"> {{actor.name}} 
            <q-btn v-if="isAdmin"
                  push round dense color="white" text-color="negative"
                  icon="delete"
                  @click="deleteActor(actor)"
              />
          </div>
        </div>
        <q-btn color="positive" class="q-my-md" @click="doRating()">
          <q-icon left size="2em" :name="ratingIcon" />
          <div>{{ratingText}}</div>
        </q-btn>
        <q-btn v-if="isAdmin" class="q-my-md" color="secondary" @click="editAllText">
          <q-icon left size="2em" name="edit" />
          <div>Edit text</div>
        </q-btn>
        <EditTextDialog 
                v-model="edit_text_dialog"
                :movie="movie"
              />
        <q-btn v-if="isAdmin" class="q-my-md" color="secondary" @click="setTitleImage">
          <q-icon left size="2em" name="image" />
          <div>Set title image</div>
        </q-btn>
        <ChangeTitleImageDialog 
                v-model="change_title_dialog"
                :movie="movie"
              />
        <q-btn v-if="isAdmin" class="q-my-md" color="secondary" @click="addGalleryImage">
          <q-icon left size="2em" name="collections" />
          <div>Add to gallery</div>
        </q-btn>
        <AddImageDialog 
                v-model="add_gallery_dialog"
                :movie="movie"
              />
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
import EditTextDialog from './movie_edit/editTextDialog'
import AddImageDialog from './movie_edit/addImageDialog'
import ChangeTitleImageDialog from './movie_edit/ChangeTitleImageDialog'
import NotifHelper from 'src/services/NotifHelper'
import EditCastDialog from './movie_edit/editCastDialog.vue'
import AddGenreDialog from './movie_edit/addGenreDialog'
import DeleteGenreDialog from './movie_edit/deleteGenreDialog'

export default {
  components: { GenreBadge, RatingDialog, EditTextDialog, AddImageDialog, ChangeTitleImageDialog, EditCastDialog, ChangeTitleImageDialog, AddGenreDialog, DeleteGenreDialog },
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
  data() {
    return {
      currentImage:1,
      fullscreen: false,
      ratingIcon: 'thumbs_up_down',
      ratingText: 'Add rating!',
      ratingDialog: false,
      edit_text_dialog: false,
      change_title_dialog: false,
      add_gallery_dialog: false,
      edit_cast_dialog: false,
      edit_director_dialog: false,
      add_genre_dialog: false,
      delete_genre_dialog: false,

    }
  },
  computed:{
    ratingUserSafe: function(){
      if (this.ratingUser){
        return this.ratingUser
      } 
      return {"storyScore":5,"actorScore":5, "visualScore":5}
    },
    imageGallerySafe(){
      console.log("gallery length: "+ this.imageGallery.length)
      if (this.imageGallery.length > 0){
        return this.imageGallery
      } 
      return [{id: null,
          image: "",
          imageMimeType: "image/png",
          _links: {self: {href: "~assets/no-image.png"}}
      }]
    },
    isAdmin: function(){
      var user = this.$store.getters['auth/user']
      console.log(user)
      if (user){
        return user.isAdmin
      } else {
        return false
      }
    },
    yearMadeSafe: function(){
      console.log(this.yearMade)
      if(this.yearMade){
        console.log(this.yearMade)
        return this.yearMade.substr(0,4)
      }
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
        type: String,
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
    },
    movie :{
      type: Object,
      required: true,
    }
    
  },
  methods: {
    deleteActor(actor){
        actor['movieId'] = this.id
        console.log("actor to delete: " + JSON.stringify(actor, null, 1 ))
        this.$axios.delete("/movies/actor",{data : actor, headers : {'Content-Type' : 'application/json'}})
        .then(resp => {
            NotifHelper.notifyPosit("Actor " + actor.name + " deleted!")
            this.$router.go()
        })
        .catch((e) =>{
            NotifHelper.notifyNegatResp(e)
        })
    },
    deleteCurrentImage(){
      console.log(this.currentImage)
      console.log(this.imageGallery)
      this.$axios.delete('/movies/image/'+this.currentImage)
      .then((resp) =>{
        NotifHelper.notifyPosit('Image ' + this.currentImage + ' deleted')
        this.$router.go()
      })
      .catch((e) =>{
        NotifHelper.notifyNegatResp(e)
      })
    },
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
    editAllText(){
      this.edit_text_dialog = true;
    },
    setTitleImage(){
      this.change_title_dialog = true;
    },
    addGalleryImage(){
      this.add_gallery_dialog = true;
    },
    editCast(){
      this.edit_cast_dialog = true;
    },
    editDirector(){
      this.edit_director_dialog = true;
    },
    addGenre(){
      this.add_genre_dialog = true;
    },
    deleteGenre(){
      this.delete_genre_dialog = true
    }
  },
  watch: {
    imageGallery: function(){
      this.currentImage = this.imageGallery[0].id
    }
  }
}
</script>

