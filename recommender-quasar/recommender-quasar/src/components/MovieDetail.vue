<template>
    <q-list>
        <q-item>
            
        </q-item>
    </q-list>
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
    imageTitle: {
      type: String,
      required: true
    },
    imageGallery: {
      type: Array,
      required: true
    },
    yearMade:{
        type: Date,
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

