<template>
  <q-page class="flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md">
    <q-card class="my-card" v-for="movie in movies" :key="movie.id">
                <q-card-section>
                    <div class="text-h6 q-mb-xs">{{movie.name}}</div>
                    <div class="row no-wrap items-center">
                    <q-rating icon="star_border"
                        icon-selected="star"
                        icon-half="star_half" 
                        v-model="movie.overallScoreAgg" :max="10" color="accent" />
                    <span class="text-caption text-grey q-ml-sm">{{movie.overallScoreAgg}}</span>
                    </div>
                </q-card-section>
                <img :src="movie.titleImage">
                <q-card-section class="q-pt-none">
                    {{ movie.description }}
                </q-card-section>
            </q-card>
  </q-page>
</template>

<script>
export default {
  // TODO: sem patri ziskavanie recommended movies
  name: 'PageIndex',
  data () {
    return {
      movies:[{
                id:1,
                name: 'Tvoja mamka',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 4.2,
                genres : [{
                        id: 1,
                        name: 'Action'
                            },
                            {
                            id: 2,
                            name: 'Comedy'}]},
            {
                id:2,
                name: 'Tvoja Mamka 2',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 3.6,
                genres : [{
                id: 1,
                name: 'Action'
                    },
                    {
                    id: 2,
                    name: 'Comedy'}]},
            {
            id:3,
            name: 'Velke srandy',
            description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
            titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
            overallScoreAgg : 4.0,
            genres : [{
                    id: 1,
                    name: 'Action'
                        },
                        {
                        id: 2,
                        name: 'Comedy'}]},
            {
                id:4,
                name: 'Zaujimavy film',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 4.5,
                genres : [{
                id: 1,
                name: 'Action'
                    },
                    {
                    id: 2,
                    name: 'Comedy'}]},
            {
                id:5,
                name: 'Tento neni velmi zaujimavy',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 1.3,
                genres : [
                    {
                    id: 1,
                    name: 'Action'},
                    {
                    id: 2,
                    name: 'Comedy'}]},
            {
                id:6,
                name: 'Da sa zvladnut',
                description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
                titleImage: "https://cdn.quasar.dev/img/mountains.jpg",
                overallScoreAgg : 2.5,
                genres : [{
                id: 1,
                name: 'Action'
                    },
                    {
                    id: 2,
                    name: 'Comedy'
                    }]},
            ],
    }
  }

}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px</style>
