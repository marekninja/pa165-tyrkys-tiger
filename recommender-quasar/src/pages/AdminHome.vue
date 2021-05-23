<template>
  <q-page class="flex flex-center">
    <q-list
    padding
    separator
    bordered
    class="rounded-borders"
    >
        <q-item
        v-for="nav in links"
        @click="navTo(nav.to)"
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
  </q-page>
</template>

<script>
export default {
  //TODO: toto su funkcie admina 

  name: 'AdminHome',
  data:function() {
    return {
      links: [
          {
            label: 'Genres',
            icon: 'category',
            to: '/admin/genres'
          },
          {
            label: 'People',
            icon: 'recent_actors',
            to: '/admin/people'
          },
          {
            label: 'Users',
            icon: 'people',
            to: '/admin/users'
          },
          {
            label: 'Create Movie',
            icon: 'add',
            to: '/admin/movie'
          }]
    }
  }
}
</script>
