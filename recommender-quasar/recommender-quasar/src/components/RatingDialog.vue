<template>
    
</template>

<script>
import GenreBadge from './GenreBadge.vue'
export default {
  components: { GenreBadge },
  name: 'MovieDetail',  
  created:function() {
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
      visible: true
    }
  },
  props: {
    id: {
      type: Number,
      required: true
    },
    storyScore: {
      type: Number,
      required: true
    },
    visualScore: {
      type: Number,
      required: true
    },
    actorScore: {
      type: Number,
      required: true
    },
    overallScore: {
      type: Number,
      required: true
    },
  },
}
</script>

