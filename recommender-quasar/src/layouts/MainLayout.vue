<template>
  <q-layout view="hHh lpR lff">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="leftDrawerOpen = !leftDrawerOpen" />

        <q-toolbar-title>
          <q-avatar>
            <q-img
                src="/icons/favicon-32x32.png"
                srcset="/icons/favicon-32x32.png 300w,
                        /icons/favicon-96x96.png 3x,
                        /icons/favicon-128x128.png 4x">
              </q-img>
          </q-avatar>
          Movie Recommender System
        </q-toolbar-title>

        <q-btn flat round dense icon="login" class="q-mr-xs" @click="loginDialogVisible=true"/>
        <q-btn flat round dense :icon="dark_mode" @click="darkToggle"/>
      </q-toolbar>
    </q-header>
      <LoginDialog
        ref="loginDialog"
        v-model="loginDialogVisible"
      />


    <!-- <q-dialog v-model="loginDialogVisible">
      <q-card style="max-width: 500px" class="q-px-sm q-pb-md">
        <q-form
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
              v-model="nickname"
              label="Nickname"
              lazy-rules
              :rules="[ val => val && val.length > 0 || 'Please type something']"
            />
          </q-card-section>
          <q-card-section>
            <q-input v-model="password" label="Password" filled :type="isPwd ? 'password' : 'text'">
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
    </q-dialog> -->

    <q-drawer v-model="leftDrawerOpen" side="left" overlay elevated>
      <q-list>
        <q-item-label
          header
          class="text-grey-8"
        >
          Essential Links
        </q-item-label>
        <q-item
        v-for="nav in essentialLinks"
        :key="nav.to"
        :to="nav.to"
        class="row"
        clickable 
        v-ripple
        exact>
        <q-item-section avatar>
          <q-icon :name="nav.icon" />
        </q-item-section>
        <q-item-section >
          <q-item-label> {{nav.label}} </q-item-label>
        </q-item-section>
      </q-item>
      </q-list>
    </q-drawer>

    <q-page-container class="container-width ">
      <router-view />
    </q-page-container>

    <q-footer bordered class="bg-grey-8 text-white row ">
      <q-toolbar>
        <q-toolbar-title class="row justify-center items-center q-gutter-md">
            <q-avatar>
            <q-img
                src="/icons/favicon-32x32.png"
                srcset="/icons/favicon-32x32.png 300w,
                        /icons/favicon-96x96.png 3x,
                        /icons/favicon-128x128.png 4x">
              </q-img>
          </q-avatar>
          <div>
          Tyrkys Tiger &copy; 2021
          </div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script>
import EssentialLink from 'components/EssentialLink.vue'
import LoginDialog from 'src/components/LoginDialog.vue';

const linksData = [
  {
    label: 'Browse',
    icon: 'search',
    to: '/'
  },
  {
    label: 'Recommended',
    icon: 'theaters',
    to: '/recommended'
  },
  {
    label: 'My Account',
    icon: 'account_circle',
    to: '/user'
  },
  {
    label: 'Administrator',
    icon: 'verified_user',
    to: '/admin'
  },
];

export default {
  name: 'MainLayout',
  components: { EssentialLink, LoginDialog },
  data () {
    return {
      leftDrawerOpen: false,
      essentialLinks: linksData,
      dark_mode:'dark_mode',
      loginDialogVisible:false,
      slideVol: 39,
      slideAlarm: 56,
      slideVibration: 63,
      nickname: '',
      password: '',
      isPwd: true,
    }
  },
  methods: {
    darkToggle(){
      this.$q.dark.set(! this.$q.dark.isActive)
      if (this.$q.dark.isActive){
        this.dark_mode = "light_mode"
      } else {
        this.dark_mode = "dark_mode"
      }
    },
    onSubmit () {
      this.$q.notify({
        color: 'green-4',
        textColor: 'white',
        icon: 'cloud_done',
        message: 'Submitted'
      })
      this.$store.dispatch('global/saveNickname',this.nickname)
      this.$store.dispatch('global/savePassword',this.password)
    },
  }
}
</script>
<style scoped>
  .container-width{
    /* min-width: 1200px;  */
    max-width: 1200px;
    margin: auto;
  }
</style>
