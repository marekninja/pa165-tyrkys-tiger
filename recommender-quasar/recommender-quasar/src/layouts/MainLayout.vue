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

        <q-btn flat round dense icon="login" class="q-mr-xs" />
        <q-btn flat round dense icon="dark_mode" @click="darkToggle"/>
      </q-toolbar>
    </q-header>

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
          Tyrkis Tiger &copy; 2021
          </div>
        </q-toolbar-title>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script>
  import EssentialLink from 'components/EssentialLink.vue'

const linksData = [
  {
    label: 'Recommended',
    icon: 'theaters',
    to: '/'
  },
  {
    label: 'Browse',
    icon: 'search',
    to: '/browse'
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
  components: { EssentialLink },
  data () {
    return {
      leftDrawerOpen: false,
      essentialLinks: linksData
    }
  },
  methods: {
    darkToggle(){
      this.$q.dark.set(! this.$q.dark.isActive)
    }
  }
}
</script>
<style scoped>
  .container-width{
    min-width: 1200px; 
    max-width: 1200px;
    margin: auto;
  }
</style>
