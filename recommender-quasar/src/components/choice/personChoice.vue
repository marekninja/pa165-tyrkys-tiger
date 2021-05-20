<template>
        <!-- v-model="personsChoice" -->
    <q-select
        v-bind:value="value"
        v-on:input="$emit('input', $event)"

        behavior="menu"
        :options="persons"
        :option-value="opt => Object(opt) === opt && opt"
        :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
        :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
        emit-value
        map-options
        label="People"/>
</template>

<script>
import NotifHelper from 'src/services/NotifHelper'
export default {
  name: 'PersonChoice',
  data () {
    return {
    persons:[{
          id: 1,
          name: 'Milanko Háčik'
        },
        {
          id: 2,
          name: 'Jožko Vajda'
        },
        {
          id: 3,
          name: 'Marika Gombitová'
        }
        ],
    }
  },
  created: function(){
    //   this.$axios.get()
    //TODO: load persons from server
      NotifHelper.notifyNegat("Couldn't load persons");
  },
  props: ['value'],
}
</script>
