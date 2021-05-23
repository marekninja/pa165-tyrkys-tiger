<template>
  <q-page class="flex flex-center column">
      <!-- :columns="columns" -->
      <div class="text-h2 q-my-sm">
          Users:
      </div>
    <q-list
    padding
    separator
    bordered
    class="rounded-borders fit"
    >
        <q-item
        v-for="user in users"
        :key="user.id"
        class="row justify-between"
        clickable
        v-ripple
        exact>
        <q-item-section>
          <q-item-label> {{user.id}}</q-item-label>
        </q-item-section>
        <q-item-section>
          <q-item-label> {{user.nickName}}</q-item-label>
        </q-item-section>
        <q-item-section>
          <q-item-label>{{user.name}}</q-item-label>
        </q-item-section>
        <q-item-section>
          <q-item-label>{{ user. email}} </q-item-label>
        </q-item-section>
        <q-item-section>
          <q-item-label>{{user.isAdministrator}}</q-item-label>
        </q-item-section>
        <q-item-section>
            <q-btn class="gt-xs" size="12px" @click="deleteUser(user.id)" flat dense round icon="delete" color="negative" />
        </q-item-section>
      </q-item>
    </q-list>
  </q-page>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'
export default {

  name: 'AdminUsers',
  created: function(){
    this.getUsers()
  },
  data:function() {
    return {
      users: [],
    }
  },
  methods: {
      deleteUser(id){
          this.$axios.delete("/users/"+id)
          .then(resp => {
              this.$router.go()
          })
          .catch(e =>{
              NotifHelper.notifyNegat(e)
          })
      },
      getUsers(){
        this.$axios.get("/users")
        .then(resp => {
            this.users = resp.data._embedded.userPasswordlessDTOList
        })
        .catch(e => {
          NotifHelper.notifyNegatResp(e)
        })
    },

  },
}
</script>
