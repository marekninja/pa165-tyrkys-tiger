<template>
    <q-card class="my-card" @click="clicked(id)" v-ripple="{color:'secondary'}">
        <q-card-section>
            <div class="text-h6 q-mb-xs">{{ name }}</div>
            <div class="row no-wrap items-center">
            <q-rating icon="star_border"
                readonly
                icon-selected="star"
                icon-half="star_half" 
                v-model="score" :max="10" color="accent" />
            <span class="text-caption text-grey q-ml-sm">{{score}}</span>
            </div>
        </q-card-section>
        <img :src="image">
        <q-card-section class="q-pt-none">
            {{ description }}
        </q-card-section>
        <q-card-section>
          <div  class="row justify-start items-baseline q-gutter-sm">
            <GenreBadge v-for="genre in genres" :key="genre.id"
            :id ="genre.id"
            :name="genre.name"
            />.
          </div>
            
        </q-card-section>
    </q-card>
</template>

<script>
import GenreBadge from './GenreBadge.vue'
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
          this.$router.push("/movie")
      }
  }
}
</script>

