<template>
    <q-card class="my-card" v-ripple="{color:'secondary'}">
        <q-card-section>
            <div class="text-h6 q-mb-xs cursor-pointer" @click="clicked(id)" v-if="name.length > 25" >
              {{ name.substring(0,22) }}...
            </div>
            <div class="text-h6 q-mb-xs cursor-pointer" @click="clicked(id)" v-else >
              {{ name }}
            </div>

            <div class="row no-wrap items-end">
              <q-rating icon="star_border"
                  class="q-my-auto"
                  size="xs"
                  readonly
                  icon-selected="star"
                  icon-half="star_half" 
                  v-model="score" :max="10" color="accent" />
              <span class="text-caption text-grey q-ml-sm">{{score}}</span>
            </div>
      </q-card-section>
      <q-card-section>
        <q-responsive style="max-height:300px" :ratio="1">
          <q-img contain  @click="clicked(id)" class="cursor-pointer" :src="image"></q-img>
        </q-responsive>
      </q-card-section>
      
        <!-- <img @click="clicked(id)" class="cursor-pointer" :src="image"> -->
        <q-card-section class="q-pt-none">
            {{ description.substring(0,47) }}...
        </q-card-section>
        <q-card-section>
          <div  class="row justify-start items-baseline q-gutter-sm">
            <GenreBadge v-for="genre in genres" :key="genre.id"
            :id ="genre.id"
            :name="genre.name"
            />
          </div>
            
        </q-card-section>

        <q-card-actions v-if="isAdmin" align="right">
          <q-btn flat round @click="deleteMovie(id)" color="red" icon="delete" />
      </q-card-actions>

    </q-card>
</template>

<script>
import GenreBadge from './GenreBadge.vue'
import NotifHelper from '../services/NotifHelper'
import router from 'src/router'
export default {
  components: { GenreBadge },
  name: 'MovieList',  
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
    image: {
      type: String,
      required: true
    },
    score: {
      type: Number,
      required: true
    },
    genres: {
        type: Array,
        required: false
    }
  },
  methods: {
      clicked(id){
          this.$q.notify({
            color: 'positive',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'clicked' + JSON.stringify(this.id)
            })
          this.$router.push("/movie/"+this.id)
      },
      deleteMovie(id){
        this.$axios.delete('/movies/'+id)
        .then((response)=>{
          NotifHelper.notifyPosit('Movie ' + id + ' deleted!')
          this.$router.go()
        })
        .catch((e) =>{
          NotifHelper.notifyNegatResp('Could not delete')
        })
      }
  },
  computed: {
        isAdmin: function(){
      var user = this.$store.getters['auth/user']
      console.log(user)
      if (user){
        return user.isAdmin
      } else {
        return false
      }
    },
  }
}
</script>

