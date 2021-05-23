<template>
  <q-page class="flex flex-center column">
      <!-- :columns="columns" -->
      <div class="text-h2 q-my-sm">
          Genres:
      </div>
      <q-btn
      size="xl"
      @click="createGenre"
      round
      dense
      color="primary q-ma-md"
      icon="add"
      class="absolute-bottom-right"
    />
        <CreateGenreDialog
        class="absolute"
        v-if="create_genre_dialog"
        v-model="create_genre_dialog"
    />
    <q-list
    padding
    separator
    bordered
    style="min-width: 400px"
    class="rounded-borders column q-gutter-sm"
    >
        <q-item
        v-for="genre in genres"
        :key="genre.id"
        class="row justify-around"
        clickable
        v-ripple
        exact>
        <q-item-section class="q-mx-sm col-1">
          <q-item-label> {{genre.id}}</q-item-label>
        </q-item-section>
        <q-item-section class="q-mx-sm col-8">
            <q-input class="q-my-none q-py-none"
              filled
              v-model="genre.name"
              label="Name"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
        </q-item-section>
        <q-item-section class="q-mx-sm col-3">
            <div class="row no-wrap">
                <div class="q-mx-sm">
                <q-btn class="gt-xs" size="12px" @click="confirmDelete(genre)" flat dense round icon="delete" color="negative" />
            </div>
            <div class="q-mx-sm">
                <q-btn class="gt-xs" size="12px" @click="updateGenre(genre)" flat dense round icon="edit" color="negative" />
            </div>
            </div>
        </q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'
import CreateGenreDialog from '../components/admin_edit/CreateGenreDialog'
export default {
  components: { CreateGenreDialog },

  name: 'AdminGenres',
  created: function(){
    this.getGenres()
  },
  data:function() {
    return {
      genres: [],
      create_genre_dialog: null,
      genreClicked:null,
    }
  },
  methods: {
      createGenre(){
          this.create_genre_dialog=true
      },
      updateGenre(genre){
          this.$axios.put("/genres/update",genre)
          .then(resp => {
              NotifHelper.notifyPosit("Genre updated!")
              this.$router.go()
          })
          .catch(e => {
              NotifHelper.notifyNegatResp(e)
          })
      },
      confirmDelete(genre){
          this.$q.dialog({
            title: 'Confirm',
            message: 'Do you want to delete genre "' + genre.name + '"?',
            cancel: true,
            persistent: true
        }).onOk(() => {
            this.deleteGenre(genre.id)
        })
      },
      deleteGenre(id){
          this.$axios.delete("/genres/"+id)
          .then(resp => {
              this.$router.go()
          })
          .catch(e =>{
              NotifHelper.notifyNegat(e)
          })
      },
    getGenres(){
        this.$axios.get("/genres")
        .then(resp => {
            this.genres = resp.data._embedded.genreDTOList
        })
        .catch(e => {
          NotifHelper.notifyNegatResp(e)
        })
    },

  },
}
</script>
