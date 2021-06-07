<template>
  <q-page padding>
    <span class="text-h2">
      Update user data:
    </span>
    <q-form @submit="submit" style="max-width: 400px" class="q-mx-auto column justify-center">
            <q-input
              filled
              v-model="userData.nickName"
              label="Nickname"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 50 || 'Can not be more than 50 chars']"
            />
            <q-input
              filled
              autogrow
              v-model="userData.name"
              label="Name"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 255 || 'Can not be more than 255 chars' ]"
            />
            <q-input
              type="email"
              filled
              autogrow
              v-model="userData.email"
              label="E-mail"
              :rules="[ val => val && val.length > 0 || 'Please type something', val => val && val.length < 255 || 'Can not be more than 255 chars' ]"
            />
            <q-input v-model="userData.password" label="Password" filled :type="isPwd ? 'password' : 'text'">
              <template v-slot:append>
                <q-icon
                  :name="isPwd ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="isPwd = !isPwd"
                />
              </template>
            </q-input>
            <q-date
              class="q-mx-auto"
              mask="YYYY-MM-DD"
              v-model="userData.dateOfBirth"
              default-view="Calendar"
              :options="notFutureYear"
              :navigation-max-year-month="max_year_month"
            />
            <div class="row justify-center">
              <q-btn flat label="Update" type="submit" color="positive" />
            </div>
          
        </q-form>
  </q-page>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'
export default {
  name: 'UserHome',
  data (){
    return {
      isPwd: true,
      userData: {
        nickName: null,
        name: null,
        email: null,
        dateOfBirth: null,
        password:null,
      }
    }
    
  },
  created: function(){
    this.getUserPaswordless()
  },
  computed: {
    max_year_month: function(){
      return new Date().toJSON().slice(0,7).replace(/-/g,'/')
    }
  },
  methods: {
    getUserPaswordless(){
      this.userData = this.$store.getters['auth/user']
      this.$axios.get("/users/nickname/"+this.$store.getters['auth/user'].nickName)
      .then(resp => {
        this.userData = resp.data;
        this.userData.password = this.$store.getters['auth/userFull'].password
      })
      .catch((e) => {
        NotifHelper.notifyNegatResp(e);
      })
    },
    submit(){
      this.$axios.put('/users/update',this.userData)
      .then((response) => {
        NotifHelper.notifyPosit("Updated! Login with new credentials.")
        this.$store.dispatch('auth/logout')
        this.$router.push("/")
      })
      .catch((e)=>{
        NotifHelper.notifyNegatResp(e)
      })
    },
    notFutureYear () {
      return new Date(this.userData.dateOfBirth) <= new Date()
    },
  },
}
</script>
