<template>
  <q-dialog 
    v-bind:value="value"
      v-on:input="$emit('input', $event)"
    >
      <q-card style="max-width: 500px" class="q-px-sm q-pb-md">
        <q-form
          v-if="loggedIn"
          @submit="handleLogout"
          class="q-gutter-md"
        >
          <q-card-section>
            <div class="text-h4">
              User:
            </div>
          </q-card-section>
          <q-card-section class="text-h4">
            {{ this.$store.getters['auth/user'].username}}
          </q-card-section>
          <q-card-section>
              <q-btn label="Log out" type="submit" color="secondary"/>
          </q-card-section>
        </q-form>
        <q-form
          v-else
          @submit="handleLogin"
          @reset="loginReset"
          class="q-gutter-md"
        >
          <q-card-section>
            <div class="text-h3">
              Log in:
            </div>
          </q-card-section>
          <q-card-section>
            <q-input
              filled
              v-model="user.username"
              label="Username"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please type something']"
            />
          </q-card-section>
          <q-card-section>
            <q-input v-model="user.password" label="Password" filled :type="isPwd ? 'password' : 'text'">
              <template v-slot:append>
                <q-icon
                  :name="isPwd ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="isPwd = !isPwd"
                />
              </template>
            </q-input>
          </q-card-section>
          <q-card-section>
              <q-btn label="Submit" type="submit" color="primary"/>
              <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
          </q-card-section>
        </q-form>
      </q-card>
    </q-dialog>
</template>

<script>

import User from '../models/user';

import NotifHelper from '../services/NotifHelper'

export default {
  // name: 'ComponentName',
 props: ['value'],
 data() {
    return {
      user: new User('', ''),
      dialog: true,
      loading: false,
      message: '',
      isPwd: true
    };
  },
  computed: {
    loggedIn() {
      return this.$store.getters['auth/loggedIn'];
      // return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    console.log('logged in: ',this.$store.getters['auth/loggedIn'])
    console.log('vue', JSON.stringify(this.$auth,null,1))
    console.log('fun: ', JSON.stringify(this.$fun,null,1))
    // if (this.loggedIn) {
    //   this.$router.push('/profile');
    // }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      if (this.user.username && this.user.password) {
        this.$store.dispatch('auth/login', this.user).then(
          () => {
            NotifHelper.notifyPosit("Logged in succesfullly");

            this.$axios.get("/users/nickname/"+this.user.username)
            .then((resp) => {
              NotifHelper.notifyPosit("got resp.data: "+JSON.stringify(resp.data))
              var userFull = resp.data
              userFull.password = this.user.password
              this.$store.dispatch('auth/storeFullUser', userFull);
              this.$router.push('/recommended')
            })
            .catch(e=>{
              NotifHelper.notifyNegat("Failed getting full user");
            })
          },
          error => {
            this.loading = false;
            this.message =
              (error.response && error.response.data) ||
              error.message ||
              error.toString();
            NotifHelper.notifyNegat( this.message)
          }
        );
      }
    },
    handleLogout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/')
    },
    loginReset () {
      this.user = new User('', '')
    }
  }
}
</script>
