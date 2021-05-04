<template>
  <q-page>
    <div>
    <q-expansion-item
    expand-separator
    icon="tune"
    label="Filters"
    
    >
        <q-card class="q-pa-md">
            <q-card-section>
                Choose by which parameters you want to filter movies:
            </q-card-section>
            <q-form       
                @reset="onReset" class="row wrap justify-around items-baseline q-gutter-sm">       
                <!-- :option-value="opt => Object(opt) === opt && 'id' in opt ? opt.id : null" -->
                <div class="col-4">
                    <q-select
                        v-model="genresChoice"
                        multiple
                        :options="genres"
                        :option-value="opt => Object(opt) === opt && opt"
                        :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
                        :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
                        emit-value
                        map-options
                        behavior="menu"
                        label="Genres"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ genresChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4" >
                    <q-select
                        v-model="personsChoice"
                        multiple
                        behavior="menu"
                        :options="persons"
                        :option-value="opt => Object(opt) === opt && 'id' in opt ? opt.id : null"
                        :option-label="opt => Object(opt) === opt && 'name' in opt ? opt.name : '- Null -'"
                        :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
                        emit-value
                        map-options
                        label="People"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ personsChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4">
                    <q-input 
                    ref="movieNameInput"
                    v-model="movieNameChoice" 
                    lazy-rules="ondemand" 
                    label="Movie name" 
                    :rules="[val => val.length <= 3 || 'Please use maximum 3 characters']"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ movieNameChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4">
                    Production year
                    <q-slider 
                        label 
                        v-model="yearMadeChoice" 
                        :min="yearMin" :max="yearMax"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ yearMadeChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4">
                    <q-input
                    ref="countryCodeInput"
                    clearable 
                    v-model="countryCodeChoice" 
                    label="Country code" 
                    lazy-rules="ondemand"
                    :rules="[val => val.length <= 3 || 'Please use maximum 3 characters', val => /^[A-Z]+$/.test(val) || 'Must contain only capital letters' ]"/>
                    <div class="col-12">
                        <q-badge color="secondary" multi-line>
                            Model: "{{ countryCodeChoice }}"
                        </q-badge>
                    </div>
                </div>
                <div class="col-4 order-last">
                    <q-btn label="Submit" type="submit" @click="onSubmit"   color="positive"/>
                    <q-btn label="Reset" type="reset" color="negative" flat class="q-ml-sm" />
                </div>
            </q-form>
        </q-card>
    </q-expansion-item>
    </div>
    <div class="flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md">
        <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>   
        <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6 q-mb-xs">Our Changing Planet</div>
        <div class="row no-wrap items-center">
          <q-rating icon="star" size="18px" v-model="stars" :max="5" color="primary" />
          <span class="text-caption text-grey q-ml-sm">4.2 (551)</span>
        </div>
      </q-card-section>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="q-pt-none">
        {{ lorem }}
      </q-card-section>
    </q-card>   
    </div>
  </q-page>
</template>

<script>

export default {
  name: 'PageIndex',
  data () {
    return {
      stars: 4,
      lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      movieNameChoice: null,
      genresChoice: null,
      personsChoice: null,
      yearMadeChoice: null,
      countryCodeChoice: null,
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
      genres: [
          {
          id: 1,
          name: 'Action'
        },
        {
          id: 2,
          name: 'Comedy'
        },
        {
          id: 3,
          name: 'Sci-fi'
        },
        {
          id: 4,
          name: 'A tak ďalej'
        },
        ],
        yearMin: 1900,
        yearMax: 2021,
    }
  },
   methods: {
    onSubmit () {
        // send ParametersDTO format here
        var nameError = false
        if (this.movieNameChoice != null){
            this.$refs.movieNameInput.validate()
            nameError = this.$refs.movieNameChoice.hasError
        }
        var codeError = false
        if (this.countryCodeChoice != null){
            this.$refs.countryCodeInput.validate()
            codeError = this.$refs.countryCodeInput.hasError
        }
        if ( nameError || codeError){
            this.$q.notify({
            color: 'negative',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'Can not submit invalid form 😢'
            })
        } else {
            var ParametersDTO= {
            genreDTOList: this.genresChoice,
            personDTOList: this.personsChoice,
            movieName: this.movieNameChoice,
            yearMade: this.yearMadeChoice,
            countryCode: this.countryCodeChoice
            }
            this.$q.notify({
            color: 'positive',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'Submitted' + JSON.stringify(ParametersDTO)
            })
        }
        
    },

    onReset () {
      this.movieNameChoice = null,
      this.genresChoice = null,
      this.personsChoice = null,
      this.yearMadeChoice = null
      this.countryCodeChoice = null
    }
   }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 300px</style>
