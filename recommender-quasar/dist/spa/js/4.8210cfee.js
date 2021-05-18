(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{1837:function(e,t,i){"use strict";var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-badge",{staticClass:"col-auto",attrs:{color:"secondary","multi-line":""},on:{click:e.clicked}},[e._v("\n    "+e._s(e.name)+"\n")])},s=[],o={name:"MovieList",props:{id:{type:Number,required:!0},name:{type:String,required:!0}},methods:{clicked(){this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"clicked"+JSON.stringify(this.id)})}}},a=o,r=i("2877"),c=i("58a81"),d=i("eebe"),l=i.n(d),m=Object(r["a"])(a,n,s,!1,null,null,null);t["a"]=m.exports;l()(m,"components",{QBadge:c["a"]})},"4c6a":function(e,t,i){},"700b":function(e,t,i){"use strict";i("4c6a")},"8b24":function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-page",{staticClass:"flex flex-center wrap justify-arround items-center q-pa-md q-gutter-md"},e._l(e.movies,(function(e){return i("MovieList",{key:e.id,attrs:{id:e.id,name:e.name,description:e.description,image:e._embedded.titleImage._links.self.href,score:e.overallScoreAgg,genres:e.genres}})})),1)},s=[],o=i("a442"),a={name:"PageIndex",components:{MovieList:o["a"]},data(){return{movies:null,links:null,moviesExample:[{id:1,name:"Tvoja mamka",description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",titleImage:"https://cdn.quasar.dev/img/mountains.jpg",overallScoreAgg:4.2,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"}]},{id:2,name:"Tvoja Mamka 2",description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",titleImage:"https://cdn.quasar.dev/img/mountains.jpg",overallScoreAgg:3.6,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"}]},{id:3,name:"Velke srandy",description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",titleImage:"https://cdn.quasar.dev/img/mountains.jpg",overallScoreAgg:4,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"}]},{id:4,name:"Zaujimavy film",description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",titleImage:"https://cdn.quasar.dev/img/mountains.jpg",overallScoreAgg:4.5,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"}]},{id:5,name:"Tento neni velmi zaujimavy",description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",titleImage:"https://cdn.quasar.dev/img/mountains.jpg",overallScoreAgg:1.3,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"}]},{id:6,name:"Da sa zvladnut",description:"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",titleImage:"https://cdn.quasar.dev/img/mountains.jpg",overallScoreAgg:2.5,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"}]}]}},created:function(){var e=this.$store.getters["global/user"];console.log(JSON.stringify(e,null,1)),this.$axios.post("/movies/recommended",e).then((e=>{console.log("response",JSON.stringify(e,null,1)),0===Object.keys(e.data).length&&e.data.constructor===Object?this.$axios.get("/movies").then((e=>{this.fillFromResponse(e)})).catch((e=>{this.notifyError(e)})):this.fillFromResponse(e)})).catch((e=>{console.log("error",e);var t=null;t=e.response?e.response.status:e,this.notifyError(t)}))},methods:{fillFromResponse(e){this.movies=e.data._embedded.halRepresentationModelList,this.links=e.data._links},notifyError(e){this.$q.notify({color:"negative",textColor:"white",icon:"error",message:e})}}},r=a,c=(i("700b"),i("2877")),d=i("9989"),l=i("eebe"),m=i.n(l),u=Object(c["a"])(r,n,s,!1,null,"7688a638",null);t["default"]=u.exports;m()(u,"components",{QPage:d["a"]})},a442:function(e,t,i){"use strict";var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-card",{directives:[{name:"ripple",rawName:"v-ripple",value:{color:"secondary"},expression:"{color:'secondary'}"}],staticClass:"my-card"},[i("q-card-section",[e.name.length>25?i("div",{staticClass:"text-h6 q-mb-xs cursor-pointer",on:{click:function(t){return e.clicked(e.id)}}},[e._v("\n          "+e._s(e.name.substring(0,22))+"...\n        ")]):i("div",{staticClass:"text-h6 q-mb-xs cursor-pointer",on:{click:function(t){return e.clicked(e.id)}}},[e._v("\n          "+e._s(e.name)+"\n        ")]),i("div",{staticClass:"row no-wrap items-end"},[i("q-rating",{staticClass:"q-my-auto",attrs:{icon:"star_border",size:"xs",readonly:"","icon-selected":"star","icon-half":"star_half",max:10,color:"accent"},model:{value:e.score,callback:function(t){e.score=t},expression:"score"}}),i("span",{staticClass:"text-caption text-grey q-ml-sm"},[e._v(e._s(e.score))])],1)]),i("q-card-section",[i("q-responsive",{staticStyle:{"max-height":"300px"},attrs:{ratio:1}},[i("q-img",{staticClass:"cursor-pointer",attrs:{contain:"",src:e.image},on:{click:function(t){return e.clicked(e.id)}}})],1)],1),i("q-card-section",{staticClass:"q-pt-none"},[e._v("\n        "+e._s(e.description.substring(0,47))+"...\n    ")]),i("q-card-section",[i("div",{staticClass:"row justify-start items-baseline q-gutter-sm"},e._l(e.genres,(function(e){return i("GenreBadge",{key:e.id,attrs:{id:e.id,name:e.name}})})),1)])],1)},s=[],o=i("1837"),a={components:{GenreBadge:o["a"]},name:"MovieList",props:{id:{type:Number,required:!0},name:{type:String,required:!0},description:{type:String,required:!0},image:{type:String,required:!0},score:{type:Number,required:!0},genres:{type:Array,required:!1}},methods:{clicked(e){this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"clicked"+JSON.stringify(this.id)}),this.$router.push("/movie/"+this.id)}}},r=a,c=i("2877"),d=i("f09f"),l=i("a370"),m=i("daf4"),u=i("0e51"),p=i("068f"),g=i("714f"),v=i("eebe"),h=i.n(v),f=Object(c["a"])(r,n,s,!1,null,null,null);t["a"]=f.exports;h()(f,"components",{QCard:d["a"],QCardSection:l["a"],QRating:m["a"],QResponsive:u["a"],QImg:p["a"]}),h()(f,"directives",{Ripple:g["a"]})}}]);