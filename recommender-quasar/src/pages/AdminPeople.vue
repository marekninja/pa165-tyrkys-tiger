a<template>
  <q-page class="flex flex-center column">
      <!-- :columns="columns" -->
      <div class="text-h2 q-my-sm">
          People:
      </div>
    <q-btn
      size="xl"
      @click="createPerson"
      round
      dense
      color="primary q-ma-md"
      icon="add"
      class="absolute-bottom-right"
    />
        <CreatePersonDialog
        class="absolute"
        v-if="create_person_dialog"
        v-model="create_person_dialog"
    />
    <q-list
    padding
    separator
    bordered
    style="min-width: 400px"
    class="rounded-borders column q-gutter-sm"
    >
        <q-item
        v-for="person in persons"
        :key="person.id"
        class="row justify-around"
        clickable
        v-ripple
        exact>
        <q-item-section class="q-mx-sm col-1">
          <q-item-label> {{person.id}}</q-item-label>
        </q-item-section>
        <q-item-section class="q-mx-sm col-8">
            <q-input class="q-my-none q-py-none"
              filled
              v-model="person.name"
              label="Name"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
        </q-item-section>
        <q-item-section class="q-mx-sm col-3">
            <div class="row no-wrap">
                <div class="q-mx-sm">
                <q-btn class="gt-xs" size="12px" @click="confirmDelete(person)" flat dense round icon="delete" color="negative" />
            </div>
            <div class="q-mx-sm">
                <q-btn class="gt-xs" size="12px" @click="updateGenre(person)" flat dense round icon="edit" color="negative" />
            </div>
            </div>
        </q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'
import CreatePersonDialog from 'src/components/admin_edit/createPersonDialog.vue'

export default {
  components: { CreatePersonDialog },

  name: 'AdminPeople',
  created: function(){
    this.getGenres()
  },
  data:function() {
    return {
      persons: null,
      create_person_dialog: null,
      personClicked:null,
    }
  },
  methods: {
      createPerson(){
          this.create_person_dialog=true
      },
      updateGenre(person){
          this.$axios.put("/persons/update",person)
          .then(resp => {
              NotifHelper.notifyPosit("Person updated!")
              this.$router.go()
          })
          .catch(e => {
              NotifHelper.notifyNegatResp(e)
          })
      },
      confirmDelete(person){
          this.$q.dialog({
            title: 'Confirm',
            message: 'Do you want to delete person "' + person.name + '"?',
            cancel: true,
            persistent: true
        }).onOk(() => {
            this.deleteGenre(person.id)
        })
      },
      deleteGenre(id){
          this.$axios.delete("/persons/"+id)
          .then(resp => {
              this.$router.go()
          })
          .catch(e =>{
              NotifHelper.notifyNegat(e)
          })
      },
    getGenres(){
        this.$axios.get("/persons")
        .then(resp => {
            this.persons = resp.data._embedded.personDTOList
        })
        .catch(e => {
          NotifHelper.notifyNegatResp(e)
        })
    },

  },
}
</script>
