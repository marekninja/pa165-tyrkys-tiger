(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[3],{1837:function(e,t,i){"use strict";var o=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-badge",{staticClass:"col-auto",attrs:{color:"secondary","multi-line":""},on:{click:e.clicked}},[e._v("\n    "+e._s(e.name)+"\n")])},n=[],a={name:"MovieList",props:{id:{type:Number,required:!0},name:{type:String,required:!0}},methods:{clicked(){this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"clicked"+JSON.stringify(this.id)})}}},s=a,r=i("2877"),c=i("58a81"),l=i("eebe"),d=i.n(l),u=Object(r["a"])(s,o,n,!1,null,null,null);t["a"]=u.exports;d()(u,"components",{QBadge:c["a"]})},"5cc0":function(e,t,i){"use strict";i("6052")},6052:function(e,t,i){},a442:function(e,t,i){"use strict";var o=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-card",{directives:[{name:"ripple",rawName:"v-ripple",value:{color:"secondary"},expression:"{color:'secondary'}"}],staticClass:"my-card"},[i("q-card-section",[e.name.length>25?i("div",{staticClass:"text-h6 q-mb-xs cursor-pointer",on:{click:function(t){return e.clicked(e.id)}}},[e._v("\n          "+e._s(e.name.substring(0,22))+"...\n        ")]):i("div",{staticClass:"text-h6 q-mb-xs cursor-pointer",on:{click:function(t){return e.clicked(e.id)}}},[e._v("\n          "+e._s(e.name)+"\n        ")]),i("div",{staticClass:"row no-wrap items-end"},[i("q-rating",{staticClass:"q-my-auto",attrs:{icon:"star_border",size:"xs",readonly:"","icon-selected":"star","icon-half":"star_half",max:10,color:"accent"},model:{value:e.score,callback:function(t){e.score=t},expression:"score"}}),i("span",{staticClass:"text-caption text-grey q-ml-sm"},[e._v(e._s(e.score))])],1)]),i("q-card-section",[i("q-responsive",{staticStyle:{"max-height":"300px"},attrs:{ratio:1}},[i("q-img",{staticClass:"cursor-pointer",attrs:{contain:"",src:e.image},on:{click:function(t){return e.clicked(e.id)}}})],1)],1),i("q-card-section",{staticClass:"q-pt-none"},[e._v("\n        "+e._s(e.description.substring(0,47))+"...\n    ")]),i("q-card-section",[i("div",{staticClass:"row justify-start items-baseline q-gutter-sm"},e._l(e.genres,(function(e){return i("GenreBadge",{key:e.id,attrs:{id:e.id,name:e.name}})})),1)]),e.isAdmin?i("q-card-actions",{attrs:{align:"right"}},[i("q-btn",{attrs:{flat:"",round:"",color:"red",icon:"delete"},on:{click:function(t){return e.deleteMovie(e.id)}}})],1):e._e()],1)},n=[],a=i("1837"),s=i("1069"),r=(i("a18c"),{components:{GenreBadge:a["a"]},name:"MovieList",props:{id:{type:Number,required:!0},name:{type:String,required:!0},description:{type:String,required:!0},image:{type:String,required:!0},score:{type:Number,required:!0},genres:{type:Array,required:!1}},methods:{clicked(e){this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"clicked"+JSON.stringify(this.id)}),this.$router.push("/movie/"+this.id)},deleteMovie(e){this.$axios.delete("/movies/"+e).then((t=>{s["a"].notifyPosit("Movie "+e+" deleted!")})).catch((e=>{s["a"].notifyNegatResp(e)}))}},computed:{isAdmin:function(){var e=this.$store.getters["auth/user"];return console.log(e),!!e&&e.isAdmin}}}),c=r,l=i("2877"),d=i("f09f"),u=i("a370"),m=i("daf4"),p=i("0e51"),h=i("068f"),v=i("4b7e"),f=i("9c40"),C=i("714f"),g=i("eebe"),y=i.n(g),b=Object(l["a"])(c,o,n,!1,null,null,null);t["a"]=b.exports;y()(b,"components",{QCard:d["a"],QCardSection:u["a"],QRating:m["a"],QResponsive:p["a"],QImg:h["a"],QCardActions:v["a"],QBtn:f["a"]}),y()(b,"directives",{Ripple:C["a"]})},aba2:function(e,t,i){"use strict";i.r(t);var o=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-page",[i("div",[i("q-expansion-item",{attrs:{"expand-separator":"",icon:"tune",label:"Filters"}},[i("q-card",{staticClass:"q-pa-md"},[i("q-card-section",[e._v("\n              Choose by which parameters you want to filter movies:\n          ")]),i("q-form",{staticClass:"row wrap justify-around items-baseline q-gutter-sm",on:{reset:e.onReset}},[i("div",{staticClass:"col-4"},[i("q-select",{attrs:{multiple:"",options:e.genres,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",behavior:"menu",label:"Genres"},model:{value:e.genresChoice,callback:function(t){e.genresChoice=t},expression:"genresChoice"}}),i("div",{staticClass:"col-12"},[i("q-badge",{attrs:{color:"secondary","multi-line":""}},[e._v('\n                          Model: "'+e._s(e.genresChoice)+'"\n                      ')])],1)],1),i("div",{staticClass:"col-4"},[i("q-select",{attrs:{multiple:"",behavior:"menu",options:e.persons,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",label:"People"},model:{value:e.personsChoice,callback:function(t){e.personsChoice=t},expression:"personsChoice"}}),i("div",{staticClass:"col-12"},[i("q-badge",{attrs:{color:"secondary","multi-line":""}},[e._v('\n                          Model: "'+e._s(e.personsChoice)+'"\n                      ')])],1)],1),i("div",{staticClass:"col-4"},[i("q-input",{ref:"movieNameInput",attrs:{"lazy-rules":"ondemand",label:"Movie name",rules:[function(e){return e.length<=50||"Please use maximum 50 characters"}]},model:{value:e.movieNameChoice,callback:function(t){e.movieNameChoice=t},expression:"movieNameChoice"}}),i("div",{staticClass:"col-12"},[i("q-badge",{attrs:{color:"secondary","multi-line":""}},[e._v('\n                          Model: "'+e._s(e.movieNameChoice)+'"\n                      ')])],1)],1),i("div",{staticClass:"col-4"},[e._v("\n                  Production year\n                  "),i("q-slider",{attrs:{label:"",min:e.yearMin,max:e.yearMax},model:{value:e.yearMadeChoice,callback:function(t){e.yearMadeChoice=t},expression:"yearMadeChoice"}}),i("div",{staticClass:"col-12"},[i("q-badge",{attrs:{color:"secondary","multi-line":""}},[e._v('\n                          Model: "'+e._s(e.yearMadeChoice)+'"\n                      ')])],1)],1),i("div",{staticClass:"col-4"},[i("q-input",{ref:"countryCodeInput",attrs:{clearable:"",label:"Country code","lazy-rules":"ondemand",rules:[function(e){return e.length<=3||"Please use maximum 3 characters"},function(e){return/^[A-Z]+$/.test(e)||"Must contain only capital letters"}]},model:{value:e.countryCodeChoice,callback:function(t){e.countryCodeChoice=t},expression:"countryCodeChoice"}}),i("div",{staticClass:"col-12"},[i("q-badge",{attrs:{color:"secondary","multi-line":""}},[e._v('\n                          Model: "'+e._s(e.countryCodeChoice)+'"\n                      ')])],1)],1),i("div",{staticClass:"col-4 order-last"},[i("q-btn",{attrs:{label:"Submit",type:"submit",color:"positive"},on:{click:e.onSubmit}}),i("q-btn",{staticClass:"q-ml-sm",attrs:{label:"Reset",type:"reset",color:"negative",flat:""}})],1)])],1)],1)],1),i("div",{staticClass:"flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md"},e._l(e.movies,(function(e){return i("MovieList",{key:e.id,attrs:{id:e.id,name:e.name,description:e.description,image:e._embedded.titleImage._links.self.href,score:e.overallScoreAgg,genres:e.genres}})})),1)])},n=[],a=i("a442"),s=(i("bc3a"),{name:"PageIndex",data(){return{movieNameChoice:null,genresChoice:null,personsChoice:null,yearMadeChoice:null,countryCodeChoice:null,links:null,movies:[{name:"Spiderman a nečakaná pasca",description:"Tento film je veľmi dojemný o živote spidermana.",overallScoreAgg:7,genres:[{id:2,name:"romantic"},{id:1,name:"action"}],id:1,_links:{self:{href:"http://localhost:8080/pa165/api/v1/movies/1"},browse:{href:"http://localhost:8080/pa165/api/v1/movies"}},_embedded:{titleImage:{id:1,image:"tu je strasne dlhy binarny string",imageMimeType:"image/jpeg",_links:{self:{href:"http://localhost:8080/pa165/api/v1/images/url/1"}}}}}],persons:[{id:1,name:"Milanko Háčik"},{id:2,name:"Jožko Vajda"},{id:3,name:"Marika Gombitová"}],genres:[{id:1,name:"Action"},{id:2,name:"Comedy"},{id:3,name:"Sci-fi"},{id:4,name:"A tak ďalej"}],yearMin:1900,yearMax:(new Date).getFullYear()}},created:function(){this.$axios.get("/movies").then((e=>{this.fillFromResponse(e),console.log("got response!")})).catch((e=>{console.error(e),this.$q.notify({color:"negative",position:"top",message:"Loading failed",icon:"report_problem"})}))},methods:{fillFromResponse(e){this.movies=e.data._embedded.halRepresentationModelList,this.links=e.data._links},onSubmit(){var e=!1;null!=this.movieNameChoice&&(this.$refs.movieNameInput.validate(),e=this.$refs.movieNameInput.hasError);var t=!1;if(null!=this.countryCodeChoice&&(this.$refs.countryCodeInput.validate(),t=this.$refs.countryCodeInput.hasError),e||t)this.$q.notify({color:"negative",textColor:"white",icon:"error",message:"Can not submit invalid form 😢"});else{var i={genreDTOList:this.genresChoice,personDTOList:this.personsChoice,movieName:this.movieNameChoice,yearMade:this.yearMadeChoice,countryCode:this.countryCodeChoice};this.$axios.post("/movies/browse",i).then((e=>{this.fillFromResponse(e),this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"Submitted"+JSON.stringify(i)})})).catch((e=>{this.$q.notify({color:"negative",textColor:"white",icon:"error",message:e})}))}},onReset(){this.movieNameChoice=null,this.genresChoice=null,this.personsChoice=null,this.yearMadeChoice=null,this.countryCodeChoice=null}},components:{MovieList:a["a"]}}),r=s,c=(i("5cc0"),i("2877")),l=i("9989"),d=i("3b73"),u=i("f09f"),m=i("a370"),p=i("0378"),h=i("ddd8"),v=i("58a81"),f=i("27f9"),C=i("c1d0"),g=i("9c40"),y=i("eebe"),b=i.n(y),q=Object(c["a"])(r,o,n,!1,null,"1ff7730d",null);t["default"]=q.exports;b()(q,"components",{QPage:l["a"],QExpansionItem:d["a"],QCard:u["a"],QCardSection:m["a"],QForm:p["a"],QSelect:h["a"],QBadge:v["a"],QInput:f["a"],QSlider:C["a"],QBtn:g["a"]})}}]);