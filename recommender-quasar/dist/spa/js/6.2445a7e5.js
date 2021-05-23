(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[6],{"23d1":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("q-page",{attrs:{padding:""}},[a("q-form",{staticClass:"q-mx-auto justify-center",staticStyle:{"max-width":"400px"},on:{submit:e.submit}},[a("span",{staticClass:"text-h2"},[e._v("\n           Create movie:\n        ")]),a("q-input",{staticClass:"q-my-sm",attrs:{filled:"",label:"Name",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<50||"Can not be more than 50 chars"}]},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),a("q-input",{staticClass:"q-my-sm",attrs:{filled:"",autogrow:"",label:"Description",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<255||"Can not be more than 255 chars"}]},model:{value:e.description,callback:function(t){e.description=t},expression:"description"}}),a("q-file",{staticClass:"q-my-sm",attrs:{accept:"image/jpeg",filled:"",label:"Set title image"},on:{input:function(t){return e.encodeTitle(e.imageTitleFiles)},rejected:e.onRejected},scopedSlots:e._u([{key:"prepend",fn:function(){return[a("q-icon",{attrs:{name:"image"}})]},proxy:!0}]),model:{value:e.imageTitleFiles,callback:function(t){e.imageTitleFiles=t},expression:"imageTitleFiles"}}),a("q-file",{staticClass:"q-my-sm",attrs:{filled:"",multiple:"",label:"Set gallery images",accept:"image/jpeg"},on:{input:function(t){return e.encodeGallery(e.imageGalleryFiles)},rejected:e.onRejected},scopedSlots:e._u([{key:"prepend",fn:function(){return[a("q-icon",{attrs:{name:"collections"}})]},proxy:!0}]),model:{value:e.imageGalleryFiles,callback:function(t){e.imageGalleryFiles=t},expression:"imageGalleryFiles"}}),a("div",{staticClass:"column justify-center"},[a("q-date",{staticClass:"q-my-sm q-mx-auto",attrs:{"emit-immediately":!0,mask:"YYYY-MM-DD","default-view":"Years",options:e.notFutureYear,"navigation-max-year-month":e.max_year_month},model:{value:e.yearMade,callback:function(t){e.yearMade=t},expression:"yearMade"}}),a("q-badge",{attrs:{color:"teal"}},[e._v("\n              Only year choice is supported: "+e._s(e.yearMade)+"\n            ")])],1),a("q-input",{staticClass:"q-my-sm",attrs:{filled:"",label:"Country code",rules:[function(e){return e.length<=3||"Please use maximum 3 characters"},function(e){return/^[A-Z]+$/.test(e)||"Must contain only capital letters"}]},model:{value:e.countryCode,callback:function(t){e.countryCode=t},expression:"countryCode"}}),a("q-input",{staticClass:"q-my-sm",attrs:{filled:"",type:"number","lazy-rules":"",label:"Length min"},model:{value:e.lengthMin,callback:function(t){e.lengthMin=t},expression:"lengthMin"}}),a("q-select",{staticClass:"q-my-sm",attrs:{multiple:"",options:e.genres,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",behavior:"menu",label:"Genres"},model:{value:e.genresChoice,callback:function(t){e.genresChoice=t},expression:"genresChoice"}}),a("q-select",{staticClass:"q-my-sm",attrs:{multiple:"",behavior:"menu",options:e.persons,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",label:"Actors"},model:{value:e.actorsChoice,callback:function(t){e.actorsChoice=t},expression:"actorsChoice"}}),a("q-select",{staticClass:"q-my-sm",attrs:{behavior:"menu",options:e.persons,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",label:"Director"},model:{value:e.directorChoice,callback:function(t){e.directorChoice=t},expression:"directorChoice"}}),a("div",{staticClass:"justify-center row"},[a("q-btn",{directives:[{name:"close-popup",rawName:"v-close-popup"}],attrs:{flat:"",label:"Cancel",color:"negative"}}),a("q-btn",{attrs:{flat:"",label:"Create",type:"submit",color:"positive"}})],1)],1)],1)},i=[],o=(a("5319"),a("bf19"),a("e01a"),a("ddb0"),a("1069")),s=a("a3d6"),l={name:"CreateMovie",created:function(){this.getGenres(),this.getActors()},data(){return{name:null,description:null,imageTitleFiles:null,imageGalleryFiles:null,yearMade:null,countryCode:null,lengthMin:null,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"},{id:3,name:"Sci-fi"},{id:4,name:"A tak ďalej"}],genresChoice:null,persons:[{id:1,name:"Milanko Háčik"},{id:2,name:"Jožko Vajda"},{id:3,name:"Marika Gombitová"}],actorsChoice:null,directorChoice:null,titleImageObj:null,galleryImageObjs:null}},computed:{max_year_month:function(){return(new Date).toJSON().slice(0,7).replace(/-/g,"/")}},methods:{getGenres(){this.$axios.get("/genres").then((e=>{this.genres=e.data._embedded.genreDTOList})).catch((e=>{o["a"].notifyNegatResp(e)}))},getActors(){this.$axios.get("/persons").then((e=>{this.persons=e.data._embedded.personDTOList})).catch((e=>{o["a"].notifyNegatResp(e)}))},submit(){var e=new Date(this.yearMade).getFullYear();this.yearMade=e+"-01-01";var t={name:this.name,description:this.description,imageTitle:this.titleImageObj,gallery:this.galleryImageObjs,yearMade:this.yearMade,countryCode:this.countryCode,lengthMin:this.lengthMin,genres:this.genresChoice,actors:this.actorsChoice,director:this.directorChoice};console.log("movie to create: ",JSON.stringify(t)),this.$axios.post("/movies/create",t).then((e=>{o["a"].notifyPosit("Movie created! id: ",e.data.id),this.$router.push("/movie/"+e.data.id)})).catch((e=>{o["a"].notifyNegatResp(e)}))},encodeTitle(e){Object(s["a"])(e).then((e=>{o["a"].notifyPosit("Title image converted");var t={image:e,imageMimeType:"image/jpeg"};this.titleImageObj=t})).catch((e=>{this.$q.notify({color:"negative",icon:"error",message:"Failed to convert file..."})}))},encodeGallery(e){var t=[];e.forEach((e=>{t.push(Object(s["a"])(e))})),Promise.all(t).then((e=>{var t=[];e.forEach((e=>{var a={image:e,imageMimeType:"image/jpeg"};t.push(a)})),this.galleryImageObjs=t,o["a"].notifyPosit("Gallery converted")})).catch((e=>{o["a"].notifyNegat("Couldn't convert gallery...")}))},notFutureYear(){return new Date(this.yearMade)<=new Date},onRejected(e){this.$q.notify({type:"negative",message:`${e.length} file(s) did not pass validation constraints`})}}},r=l,c=a("2877"),u=a("9989"),m=a("1c1c"),d=a("0378"),p=a("66e5"),g=a("27f9"),h=a("7d53"),y=a("0016"),f=a("52ee"),b=a("58a81"),v=a("ddd8"),C=a("9c40"),j=a("7f67"),q=a("eebe"),x=a.n(q),M=Object(c["a"])(r,n,i,!1,null,null,null);t["default"]=M.exports;x()(M,"components",{QPage:u["a"],QList:m["a"],QForm:d["a"],QItem:p["a"],QInput:g["a"],QFile:h["a"],QIcon:y["a"],QDate:f["a"],QBadge:b["a"],QSelect:v["a"],QBtn:C["a"]}),x()(M,"directives",{ClosePopup:j["a"]})},a3d6:function(e,t,a){"use strict";function n(e){return new Promise(((t,a)=>{const n=new FileReader;n.readAsDataURL(e),n.onload=()=>t(/base64,(.+)/.exec(n.result)[1]),n.onerror=e=>a(e)}))}a.d(t,"a",(function(){return n}))}}]);