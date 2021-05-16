<template>
  <q-badge color="secondary" multi-line class="col-auto" @click="clicked">
      {{ name }}
  </q-badge>
</template>

<script>
export default {
  name: 'MovieList',  
                // id:1,
                // name: 'Tvoja mamka',
                // description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                // titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                // overallScoreAgg : 4.2,
                // genres : [{
                //         id: 1,
                //         name: 'Action'
                //             },
                //             {
                //             id: 2,
                //             name: 'Comedy'}]},
  props: {
    id: {
      type: Number,
      required: true
    },
    name: {
      type: String,
      required: true
    },
  },
  methods: {
      clicked(){
          this.$q.notify({
            color: 'positive',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'clicked' + JSON.stringify(this.id)
            })
      }
  }
}
</script>