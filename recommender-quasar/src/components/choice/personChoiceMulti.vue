<template>
        <!-- v-model="personsChoice" -->
    <q-select
        v-bind:value="value"
        v-on:input="$emit('input', $event)"
        
        multiple
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
  name: 'PersonChoiceMulti',
  data () {
    return {
      persons: null
    }
  },
  created: function(){
    //   this.$axios.get()
      NotifHelper.notifyNegat("Couldn't load persons");
  },
  props: { 
    'value':{},
  },
}
</script>
