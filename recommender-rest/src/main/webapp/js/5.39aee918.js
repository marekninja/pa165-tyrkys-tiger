(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[5],{1837:function(e,t,i){"use strict";var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-badge",{staticClass:"col-auto",attrs:{color:"secondary","multi-line":""},on:{click:e.clicked}},[e._v("\n    "+e._s(e.name)+"\n")])},n=[],o={name:"MovieList",props:{id:{type:Number,required:!0},name:{type:String,required:!0}},methods:{clicked(){this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"clicked"+JSON.stringify(this.id)})}}},r=o,s=i("2877"),l=i("58a81"),c=i("eebe"),d=i.n(c),m=Object(s["a"])(r,a,n,!1,null,null,null);t["a"]=m.exports;d()(m,"components",{QBadge:l["a"]})},"1f6b":function(e,t,i){"use strict";i.r(t);var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-page",[i("MovieDetail",{ref:"MovieDetail",attrs:{id:e.movie.id,name:e.movie.name,description:e.movie.description,yearMade:e.movie.yearMade,countryCode:e.movie.countryCode,lengthMin:e.movie.lengthMin,genres:e.movie.genres,actors:e.movie.actors,director:e.movie.director,ratingAgg:e.movie.ratingAgg,ratingUser:e.movie.ratingUser,imageTitle:e.movie._embedded.titleImage,imageGallery:this.getGallery(),movie:e.movie}})],1)},n=[],o=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"row full-height"},[i("div",{staticClass:"col-xs-12 col-sm-8 col-md-10 q-pa-sm"},[i("div",{staticClass:"row items-center justify-between"},[i("span",{staticClass:"col-auto text-h2"},[e._v("\n      "+e._s(e.name)+"\n      ")]),i("div",{staticClass:"col-auto"},[i("div",{staticClass:"justify-end"},[i("q-rating",{attrs:{icon:"star_border",readonly:"",size:"md","icon-selected":"star","icon-half":"star_half",max:10,color:"accent"},model:{value:e.ratingAgg,callback:function(t){e.ratingAgg=t},expression:"ratingAgg"}}),i("div",{staticClass:"text-caption q-mr-sm text-accent text-right text-weight-bold"},[e._v(e._s(e.ratingAgg))])],1)])]),i("q-responsive",{attrs:{ratio:4/3}},[i("q-carousel",{attrs:{fullscreen:e.fullscreen,animated:"",swipeable:"",thumbnails:"",padding:"",infinite:""},on:{"update:fullscreen":function(t){e.fullscreen=t}},scopedSlots:e._u([{key:"control",fn:function(){return[i("q-carousel-control",{attrs:{position:"bottom-right",offset:[18,18]}},[i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"primary",icon:e.fullscreen?"fullscreen_exit":"fullscreen"},on:{click:function(t){e.fullscreen=!e.fullscreen}}}),e.isAdmin?i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"negative",icon:"delete"},on:{click:e.deleteCurrentImage}}):e._e()],1)]},proxy:!0}]),model:{value:e.currentImage,callback:function(t){e.currentImage=t},expression:"currentImage"}},e._l(e.imageGallery,(function(e){return i("q-carousel-slide",{key:e.id,attrs:{name:e.id,"img-src":e._links.self.href}})})),1)],1),i("div",{staticClass:"text-body1"},[e._v("\n      "+e._s(e.description)+"\n    ")])],1),i("div",{staticClass:"col-xs-12 col-sm-4 col-md-2 q-pa-sm"},[i("q-img",{attrs:{src:e.imageTitle._links.self.href,contain:""}}),i("div",{staticClass:"row justify-start items-baseline q-gutter-sm q-pt-lg"},e._l(e.genres,(function(e){return i("GenreBadge",{key:e.id,attrs:{id:e.id,name:e.name}})})),1),e.isAdmin?i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"negative",icon:"add"},on:{click:e.addGenre}}):e._e(),i("AddGenreDialog",{attrs:{movie_id:this.movie.id},model:{value:e.add_genre_dialog,callback:function(t){e.add_genre_dialog=t},expression:"add_genre_dialog"}}),e.isAdmin?i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"negative",icon:"delete"},on:{click:e.deleteGenre}}):e._e(),i("DeleteGenreDialog",{attrs:{genres:this.genres,movie_id:this.movie.id},model:{value:e.delete_genre_dialog,callback:function(t){e.delete_genre_dialog=t},expression:"delete_genre_dialog"}}),i("div",{staticClass:"text-subtitle1"},[i("span",{staticClass:"text-weight-bold"},[e._v("\n        Year of production: \n      ")]),e._v("\n      "+e._s(e.yearMadeSafe)+"\n    ")]),i("div",{staticClass:"text-subtitle1"},[i("span",{staticClass:"text-weight-bold"},[e._v("\n        Country of origin: \n      ")]),e._v("\n      "+e._s(e.countryCode)+"\n    ")]),i("div",{staticClass:"text-subtitle1"},[i("span",{staticClass:"text-weight-bold"},[e._v("\n        Director: \n      ")]),i("div",[e._v("\n        "+e._s(e.director.name)+"\n        "),e.isAdmin?i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"negative",icon:"edit"},on:{click:e.editDirector}}):e._e(),i("EditCastDialog",{attrs:{movie_id:this.movie.id,changeDirector:!0},model:{value:e.edit_director_dialog,callback:function(t){e.edit_director_dialog=t},expression:"edit_director_dialog"}})],1)]),i("div",{staticClass:"text-subtitle1"},[i("div",{staticClass:"text-weight-bold"},[e._v("\n        Cast: \n        "),e.isAdmin?i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"negative",icon:"add"},on:{click:e.editCast}}):e._e(),i("EditCastDialog",{attrs:{movie_id:this.movie.id,changeDirector:!1},model:{value:e.edit_cast_dialog,callback:function(t){e.edit_cast_dialog=t},expression:"edit_cast_dialog"}})],1),e._l(e.actors,(function(t){return i("div",{key:t.id},[e._v(" "+e._s(t.name)+" \n        "),e.isAdmin?i("q-btn",{attrs:{push:"",round:"",dense:"",color:"white","text-color":"negative",icon:"delete"},on:{click:function(i){return e.deleteActor(t)}}}):e._e()],1)}))],2),i("q-btn",{staticClass:"q-my-md",attrs:{color:"positive"},on:{click:function(t){return e.doRating()}}},[i("q-icon",{attrs:{left:"",size:"2em",name:e.ratingIcon}}),i("div",[e._v(e._s(e.ratingText))])],1),e.isAdmin?i("q-btn",{staticClass:"q-my-md",attrs:{color:"secondary"},on:{click:e.editAllText}},[i("q-icon",{attrs:{left:"",size:"2em",name:"edit"}}),i("div",[e._v("Edit text")])],1):e._e(),i("EditTextDialog",{attrs:{movie:e.movie},model:{value:e.edit_text_dialog,callback:function(t){e.edit_text_dialog=t},expression:"edit_text_dialog"}}),e.isAdmin?i("q-btn",{staticClass:"q-my-md",attrs:{color:"secondary"},on:{click:e.setTitleImage}},[i("q-icon",{attrs:{left:"",size:"2em",name:"image"}}),i("div",[e._v("Set title image")])],1):e._e(),i("ChangeTitleImageDialog",{attrs:{movie:e.movie},model:{value:e.change_title_dialog,callback:function(t){e.change_title_dialog=t},expression:"change_title_dialog"}}),e.isAdmin?i("q-btn",{staticClass:"q-my-md",attrs:{color:"secondary"},on:{click:e.addGalleryImage}},[i("q-icon",{attrs:{left:"",size:"2em",name:"collections"}}),i("div",[e._v("Add to gallery")])],1):e._e(),i("AddImageDialog",{attrs:{movie:e.movie},model:{value:e.add_gallery_dialog,callback:function(t){e.add_gallery_dialog=t},expression:"add_gallery_dialog"}})],1),i("q-dialog",{model:{value:e.ratingDialog,callback:function(t){e.ratingDialog=t},expression:"ratingDialog"}},[i("q-card",{staticClass:"q-px-sm q-pb-md",staticStyle:{width:"300px"}},[i("q-card-section",[i("div",{staticClass:"text-h6"},[e._v("Your rating")])]),i("q-item-label",{attrs:{header:""}},[e._v("Story score")]),i("q-item",{attrs:{dense:""}},[i("q-item-section",{attrs:{avatar:""}},[i("q-icon",{attrs:{name:"auto_stories"}})],1),i("q-item-section",[i("q-slider",{attrs:{"label-always":"",color:"teal",step:1,min:0,max:10},model:{value:e.ratingUserSafe.storyScore,callback:function(t){e.$set(e.ratingUserSafe,"storyScore",t)},expression:"ratingUserSafe.storyScore"}})],1)],1),i("q-item-label",{attrs:{header:""}},[e._v("Visual score")]),i("q-item",{attrs:{dense:""}},[i("q-item-section",{attrs:{avatar:""}},[i("q-icon",{attrs:{name:"videocam"}})],1),i("q-item-section",[i("q-slider",{attrs:{"label-always":"",color:"teal",step:1,min:0,max:10},model:{value:e.ratingUserSafe.visualScore,callback:function(t){e.$set(e.ratingUserSafe,"visualScore",t)},expression:"ratingUserSafe.visualScore"}})],1)],1),i("q-item-label",{attrs:{header:""}},[e._v("Actor score")]),i("q-item",{attrs:{dense:""}},[i("q-item-section",{attrs:{avatar:""}},[i("q-icon",{attrs:{name:"group"}})],1),i("q-item-section",[i("q-slider",{attrs:{"label-always":"",color:"teal",step:1,min:0,max:10},model:{value:e.ratingUserSafe.actorScore,callback:function(t){e.$set(e.ratingUserSafe,"actorScore",t)},expression:"ratingUserSafe.actorScore"}})],1)],1),i("q-item-label",{attrs:{header:""}},[e._v("Overall score")]),i("q-btn",{staticClass:"q-mx-auto",attrs:{color:"positive"},on:{click:function(t){return e.submitRating()}}},[i("q-icon",{attrs:{left:"",size:"2em",name:"send"}}),i("div",[e._v("Submit")])],1)],1)],1)],1)},r=[],s=i("1837"),l=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div")},c=[],d={components:{GenreBadge:s["a"]},name:"MovieDetail",created:function(){this.ratingUser?(this.ratingText="Edit rating!",this.ratingIcon="edit"):(this.ratingText="Add rating!",this.ratingIcon="thumbs_up_down")},data(){return{visible:!0}},props:{id:{type:Number,required:!0},storyScore:{type:Number,required:!0},visualScore:{type:Number,required:!0},actorScore:{type:Number,required:!0},overallScore:{type:Number,required:!0}}},m=d,u=i("2877"),g=Object(u["a"])(m,l,c,!1,null,null,null),p=g.exports,v=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-dialog",{attrs:{value:e.value},on:{input:function(t){return e.$emit("input",t)}}},[i("q-card",{staticStyle:{"min-width":"350px"}},[i("q-form",{on:{submit:function(t){return e.submitVal(e.movieCopy)}}},[i("q-card-section",[i("q-input",{attrs:{filled:"",label:"Name",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<50||"Can not be more than 50 chars"}]},model:{value:e.movieCopy.name,callback:function(t){e.$set(e.movieCopy,"name",t)},expression:"movieCopy.name"}})],1),i("q-card-section",[i("q-input",{attrs:{filled:"",autogrow:"",label:"Description","lazy-rules":"",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<255||"Can not be more than 255 chars"}]},model:{value:e.movieCopy.description,callback:function(t){e.$set(e.movieCopy,"description",t)},expression:"movieCopy.description"}})],1),i("q-card-section",{staticClass:"column justify-center"},[i("q-date",{attrs:{"emit-immediately":!0,mask:"YYYY-MM-DD","default-view":"Years",options:e.notFutureYear,"navigation-max-year-month":e.max_year_month},model:{value:e.movieCopy.yearMade,callback:function(t){e.$set(e.movieCopy,"yearMade",t)},expression:"movieCopy.yearMade"}}),i("q-badge",{attrs:{color:"teal"}},[e._v("\n            Only year choice is supported: "+e._s(e.movieCopy.yearMade)+"\n          ")])],1),i("q-card-section",[i("q-input",{attrs:{filled:"",label:"Country code","lazy-rules":"",rules:[function(e){return e.length<=3||"Please use maximum 3 characters"},function(e){return/^[A-Z]+$/.test(e)||"Must contain only capital letters"}]},model:{value:e.movieCopy.countryCode,callback:function(t){e.$set(e.movieCopy,"countryCode",t)},expression:"movieCopy.countryCode"}})],1),i("q-card-section",[i("q-input",{attrs:{filled:"",type:"number","lazy-rules":"",label:"Length min"},model:{value:e.movieCopy.lengthMin,callback:function(t){e.$set(e.movieCopy,"lengthMin",t)},expression:"movieCopy.lengthMin"}})],1),i("q-card-actions",{staticClass:"text-primary",attrs:{align:"right"}},[i("q-btn",{directives:[{name:"close-popup",rawName:"v-close-popup"}],attrs:{flat:"",label:"Cancel",color:"negative"}}),i("q-btn",{attrs:{flat:"",label:"Submit",type:"submit",color:"positive"}})],1)],1)],1)],1)},h=[],f=(i("5319"),i("bf19"),i("e01a"),i("1069")),y={name:"EditTextDialog",props:{value:{},movie:{type:Object,required:!0}},data(){return{movieCopy:JSON.parse(JSON.stringify(this.movie))}},computed:{max_year_month:function(){return(new Date).toJSON().slice(0,7).replace(/-/g,"/")}},methods:{submitVal(e){var t=new Date(e.yearMade).getFullYear();e.yearMade=t+"-01-01";var i={id:e.id,name:e.name,description:e.description,yearMade:e.yearMade,countryCode:e.countryCode,lengthMin:e.lengthMin};this.$axios.put("/movies/"+e.id,i).then((t=>{f["a"].notifyPosit("sent update: "+JSON.stringify(i,null,1)),this.$router.push("/movie/"+e.id)})).catch((e=>{f["a"].notifyNegatResp(e)}))},notFutureYear(){return new Date(this.movieCopy.yearMade)<=new Date}},watch:{value:function(){this.movieCopy=JSON.parse(JSON.stringify(this.movie))}}},b=y,_=i("24e8"),q=i("f09f"),C=i("0378"),x=i("a370"),S=i("27f9"),k=i("52ee"),I=i("58a81"),D=i("4b7e"),Q=i("9c40"),A=i("7f67"),$=i("eebe"),w=i.n($),j=Object(u["a"])(b,v,h,!1,null,null,null),O=j.exports;w()(j,"components",{QDialog:_["a"],QCard:q["a"],QForm:C["a"],QCardSection:x["a"],QInput:S["a"],QDate:k["a"],QBadge:I["a"],QCardActions:D["a"],QBtn:Q["a"]}),w()(j,"directives",{ClosePopup:A["a"]});var M=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-dialog",{staticStyle:{"{min-height":"400px","max-width":"300px}"},attrs:{value:e.value},on:{input:function(t){return e.$emit("input",t)}}},[i("q-card",{staticStyle:{"min-width":"300px"}},[i("q-card-section",[i("span",{staticClass:"text-h3"},[e._v("\n         Add images to gallery:\n       ")])]),i("q-file",{attrs:{filled:"",label:"Set title image",accept:".jpg, image/*"},on:{rejected:e.onRejected},scopedSlots:e._u([{key:"prepend",fn:function(){return[i("q-icon",{attrs:{name:"image"}})]},proxy:!0},{key:"after",fn:function(){return[i("q-btn",{attrs:{round:"",dense:"",flat:"",icon:"send",color:"positive"},on:{click:e.sendTitleImage}})]},proxy:!0}]),model:{value:e.filesImages,callback:function(t){e.filesImages=t},expression:"filesImages"}})],1)],1)},N=[];function G(e){return new Promise(((t,i)=>{const a=new FileReader;a.readAsDataURL(e),a.onload=()=>t(/base64,(.+)/.exec(a.result)[1]),a.onerror=e=>i(e)}))}var T={name:"AddImageDialog",data(){return{filesImages:null}},methods:{addMovieId:function(){return[{movieId:this.movie.id}]},sendTitleImage(){console.log(this.filesImages),G(this.filesImages).then((e=>{console.log("base64 conv: ",e);var t={movieId:this.movie.id,image:e,imageMimeType:this.filesImages.type};this.$axios.post("/movies/addimage",t).then((e=>{console.log("image response: ",JSON.stringify(e,null,1))})).catch((e=>{console.log("image error: ",JSON.stringify(e,null,1))}))})).catch((e=>{this.$q.notify({color:"negative",icon:"error",message:"Failed to convert file..."})}))},onRejected(e){this.$q.notify({type:"negative",message:`${e.length} file(s) did not pass validation constraints`})}},props:{value:{},movie:{type:Object,required:!0}}},U=T,E=i("7d53"),J=i("0016"),P=i("ee89"),R=Object(u["a"])(U,M,N,!1,null,null,null),Y=R.exports;w()(R,"components",{QDialog:_["a"],QCard:q["a"],QCardSection:x["a"],QFile:E["a"],QIcon:J["a"],QBtn:Q["a"],QUploader:P["a"]});var B=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-dialog",{staticStyle:{"{min-height":"400px","max-width":"300px}"},attrs:{value:e.value},on:{input:function(t){return e.$emit("input",t)}}},[i("q-card",{staticStyle:{"min-width":"300px"}},[i("q-card-section",[i("span",{staticClass:"text-h3"},[e._v("\n        Set title image:\n      ")])]),i("q-file",{attrs:{filled:"",label:"Set title image",accept:".jpg, image/*"},on:{rejected:e.onRejected},scopedSlots:e._u([{key:"prepend",fn:function(){return[i("q-icon",{attrs:{name:"image"}})]},proxy:!0},{key:"after",fn:function(){return[i("q-btn",{attrs:{round:"",dense:"",flat:"",icon:"send",color:"positive"},on:{click:e.sendTitleImage}})]},proxy:!0}]),model:{value:e.filesImages,callback:function(t){e.filesImages=t},expression:"filesImages"}})],1)],1)},F=[],z={name:"ChangeTitleImageDialog",data(){return{filesImages:null}},methods:{addMovieId:function(){return[{movieId:this.movie.id}]},sendTitleImage(){console.log(this.filesImages),G(this.filesImages).then((e=>{console.log("base64 conv: ",e);var t={movieId:this.movie.id,image:e,imageMimeType:this.filesImages.type};this.$axios.put("/movies/changetitle",t).then((e=>{console.log("image response: ",JSON.stringify(e,null,1))})).catch((e=>{console.log("image error: ",JSON.stringify(e,null,1))}))})).catch((e=>{this.$q.notify({color:"negative",icon:"error",message:"Failed to convert file..."})}))},onRejected(e){this.$q.notify({type:"negative",message:`${e.length} file(s) did not pass validation constraints`})}},props:{value:{},movie:{type:Object,required:!0}}},L=z,V=Object(u["a"])(L,B,F,!1,null,null,null),K=V.exports;w()(V,"components",{QDialog:_["a"],QCard:q["a"],QCardSection:x["a"],QFile:E["a"],QIcon:J["a"],QBtn:Q["a"],QUploader:P["a"]});var H=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-dialog",{attrs:{value:e.value},on:{input:function(t){return e.$emit("input",t)}}},[i("q-card",{staticStyle:{"min-width":"350px"}},[i("q-card-section",[i("span",{staticClass:"text-h4"},[e._v("\n              "+e._s(e.headerComp)+"\n          ")])]),i("q-form",{on:{submit:function(t){return e.submit(e.personChoice)}}},[i("q-card-section",[i("q-select",{attrs:{behavior:"menu",options:e.actors,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",label:"People"},model:{value:e.personChoice,callback:function(t){e.personChoice=t},expression:"personChoice"}})],1),i("q-card-actions",{staticClass:"text-primary",attrs:{align:"right"}},[i("q-btn",{directives:[{name:"close-popup",rawName:"v-close-popup"}],attrs:{flat:"",label:"Cancel",color:"negative"}}),i("q-btn",{attrs:{flat:"",label:"Submit",type:"submit",color:"positive"}})],1)],1)],1)],1)},Z=[],W=(i("d046"),function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-select",{attrs:{value:e.value,behavior:"menu",options:e.persons,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",label:"People"},on:{input:function(t){return e.$emit("input",t)}}})}),X=[],ee={name:"PersonChoice",data(){return{persons:[{id:1,name:"Milanko Háčik"},{id:2,name:"Jožko Vajda"},{id:3,name:"Marika Gombitová"}]}},created:function(){f["a"].notifyNegat("Couldn't load persons")},props:["value"]},te=ee,ie=i("ddd8"),ae=Object(u["a"])(te,W,X,!1,null,null,null),ne=ae.exports;w()(ae,"components",{QSelect:ie["a"]});var oe={components:{PersonChoice:ne},name:"EditCastDialog",props:{value:{},movie_id:{type:Number,required:!0},changeDirector:{type:Boolean,required:!0}},data(){return{personChoice:null,actors:[{id:1,name:"Milanko Háčik"},{id:2,name:"Jožko Vajda"},{id:3,name:"Marika Gombitová"}]}},computed:{headerComp(){return this.changeDirector?"Choose director:":"Add to cast:"}},methods:{getActors(){},submit(e){e["movieId"]=this.movie_id,this.changeDirector?this.$axios.post("/movies/director",e).then((t=>{f["a"].notifyPosit("Changed director to: "+e.name),this.$router.go()})).catch((e=>{f["a"].notifyNegatResp(e)})):this.$axios.post("/movies/actor",e).then((t=>{f["a"].notifyPosit("Actor "+e.name+" added!"),this.$router.go()})).catch((e=>{f["a"].notifyNegatResp(e)}))}}},re=oe,se=Object(u["a"])(re,H,Z,!1,null,null,null),le=se.exports;w()(se,"components",{QDialog:_["a"],QCard:q["a"],QCardSection:x["a"],QForm:C["a"],QSelect:ie["a"],QCardActions:D["a"],QBtn:Q["a"]}),w()(se,"directives",{ClosePopup:A["a"]});var ce=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-dialog",{attrs:{value:e.value},on:{input:function(t){return e.$emit("input",t)}}},[i("q-card",{staticStyle:{"min-width":"350px"}},[i("q-card-section",[i("span",{staticClass:"text-h4"},[e._v("\n              Add genre:\n          ")])]),i("q-form",{on:{submit:function(t){return e.submit(e.genresChoice)}}},[i("q-card-section",[i("q-select",{attrs:{options:e.genres,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",behavior:"menu",label:"Genres"},model:{value:e.genresChoice,callback:function(t){e.genresChoice=t},expression:"genresChoice"}})],1),i("q-card-actions",{staticClass:"text-primary",attrs:{align:"right"}},[i("q-btn",{directives:[{name:"close-popup",rawName:"v-close-popup"}],attrs:{flat:"",label:"Cancel",color:"negative"}}),i("q-btn",{attrs:{flat:"",label:"Submit",type:"submit",color:"positive"}})],1)],1)],1)],1)},de=[],me={components:{PersonChoice:ne},name:"AddGenreDialog",props:{value:{},movie_id:{type:Number,required:!0}},data(){return{genresChoice:null,genres:[{id:1,name:"Action"},{id:2,name:"Comedy"},{id:3,name:"Sci-fi"},{id:4,name:"A tak ďalej"}]}},computed:{},methods:{getGenres(){},submit(e){e["movieId"]=this.movie_id,this.$axios.post("/movies/genre",e).then((t=>{f["a"].notifyPosit("Changed director to: "+e.name),this.$router.go()})).catch((e=>{f["a"].notifyNegatResp(e)}))}}},ue=me,ge=Object(u["a"])(ue,ce,de,!1,null,null,null),pe=ge.exports;w()(ge,"components",{QDialog:_["a"],QCard:q["a"],QCardSection:x["a"],QForm:C["a"],QSelect:ie["a"],QCardActions:D["a"],QBtn:Q["a"]}),w()(ge,"directives",{ClosePopup:A["a"]});var ve=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("q-dialog",{attrs:{value:e.value},on:{input:function(t){return e.$emit("input",t)}}},[i("q-card",{staticStyle:{"min-width":"350px"}},[i("q-card-section",[i("span",{staticClass:"text-h4"},[e._v("\n              Add genre:\n          ")])]),i("q-form",{on:{submit:function(t){return e.submit(e.genresChoice)}}},[i("q-card-section",[i("q-select",{attrs:{options:e.genres,"option-value":function(e){return Object(e)===e&&e},"option-label":function(e){return Object(e)===e&&"name"in e?e.name:"- Null -"},"option-disable":function(e){return Object(e)!==e||!0===e.inactive},"emit-value":"","map-options":"",behavior:"menu",label:"Genres"},model:{value:e.genresChoice,callback:function(t){e.genresChoice=t},expression:"genresChoice"}})],1),i("q-card-actions",{staticClass:"text-primary",attrs:{align:"right"}},[i("q-btn",{directives:[{name:"close-popup",rawName:"v-close-popup"}],attrs:{flat:"",label:"Cancel",color:"negative"}}),i("q-btn",{attrs:{flat:"",label:"Submit",type:"submit",color:"positive"}})],1)],1)],1)],1)},he=[],fe={name:"DeleteGenreDialog",props:{value:{},movie_id:{type:Number,required:!0},genres:{type:Array,required:!0}},data(){return{genresChoice:null,genres:JSON.parse(JSON.stringify(this.genres))}},computed:{},methods:{submit(e){e["movieId"]=this.movie_id,this.$axios.delete("/movies/genre",{data:e,headers:{"Content-Type":"application/json"}}).then((t=>{f["a"].notifyPosit("Changed director to: "+e.name),this.$router.go()})).catch((e=>{f["a"].notifyNegatResp(e)}))}},watch:{value:function(){this.movieCopy=JSON.parse(JSON.stringify(this.movie))}}},ye=fe,be=Object(u["a"])(ye,ve,he,!1,null,null,null),_e=be.exports;w()(be,"components",{QDialog:_["a"],QCard:q["a"],QCardSection:x["a"],QForm:C["a"],QSelect:ie["a"],QCardActions:D["a"],QBtn:Q["a"]}),w()(be,"directives",{ClosePopup:A["a"]});var qe={components:{GenreBadge:s["a"],RatingDialog:p,EditTextDialog:O,AddImageDialog:Y,ChangeTitleImageDialog:K,EditCastDialog:le,ChangeTitleImageDialog:K,AddGenreDialog:pe,DeleteGenreDialog:_e},name:"MovieDetail",created:function(){console.log(" created!"),this.ratingUser?(this.ratingText="Edit rating!",this.ratingIcon="edit"):(this.ratingText="Add rating!",this.ratingIcon="thumbs_up_down")},data(){return{currentImage:1,fullscreen:!1,ratingIcon:"thumbs_up_down",ratingText:"Add rating!",ratingDialog:!1,edit_text_dialog:!1,change_title_dialog:!1,add_gallery_dialog:!1,edit_cast_dialog:!1,edit_director_dialog:!1,add_genre_dialog:!1,delete_genre_dialog:!1}},computed:{ratingUserSafe:function(){return this.ratingUser?this.ratingUser:{storyScore:5,actorScore:5,visualScore:5}},imageGallerySafe(){return console.log("gallery length: "+this.imageGallery.length),this.imageGallery.length>0?this.imageGallery:[{id:null,image:"",imageMimeType:"image/png",_links:{self:{href:"~assets/no-image.png"}}}]},isAdmin:function(){var e=this.$store.getters["auth/user"];return console.log(e),!!e&&e.isAdmin},yearMadeSafe:function(){if(console.log(this.yearMade),this.yearMade)return console.log(this.yearMade),this.yearMade.substr(0,4)}},props:{id:{type:Number,required:!0},name:{type:String,required:!0},description:{type:String,required:!0},imageTitle:{type:Object,required:!0},imageGallery:{type:Array,required:!0},yearMade:{type:String},countryCode:{type:String},lengthMin:{type:Number},genres:{type:Array,required:!1},actors:{type:Array},director:{type:Object},ratingAgg:{type:Number,required:!1},ratingUser:{type:Object,required:!1},movie:{type:Object,required:!0}},methods:{deleteActor(e){e["movieId"]=this.id,console.log("actor to delete: "+JSON.stringify(e,null,1)),this.$axios.delete("/movies/actor",{data:e,headers:{"Content-Type":"application/json"}}).then((t=>{f["a"].notifyPosit("Actor "+e.name+" deleted!"),this.$router.go()})).catch((e=>{f["a"].notifyNegatResp(e)}))},deleteCurrentImage(){console.log(this.currentImage),console.log(this.imageGallery),this.$axios.delete("/movies/image/"+this.currentImage).then((e=>{f["a"].notifyPosit("Image "+this.currentImage+" deleted"),this.$router.go()})).catch((e=>{f["a"].notifyNegatResp(e)}))},doRating(){this.ratingDialog=!this.ratingDialog},submitRating(){this.$q.notify({color:"positive",textColor:"white",icon:"cloud_done",message:"Your rating is saved 👍"})},editAllText(){this.edit_text_dialog=!0},setTitleImage(){this.change_title_dialog=!0},addGalleryImage(){this.add_gallery_dialog=!0},editCast(){this.edit_cast_dialog=!0},editDirector(){this.edit_director_dialog=!0},addGenre(){this.add_genre_dialog=!0},deleteGenre(){this.delete_genre_dialog=!0}},watch:{imageGallery:function(){this.currentImage=this.imageGallery[0].id}}},Ce=qe,xe=i("daf4"),Se=i("0e51"),ke=i("880c"),Ie=i("62cd"),De=i("32a7"),Qe=i("068f"),Ae=i("0170"),$e=i("66e5"),we=i("4074"),je=i("c1d0"),Oe=Object(u["a"])(Ce,o,r,!1,null,null,null),Me=Oe.exports;w()(Oe,"components",{QRating:xe["a"],QResponsive:Se["a"],QCarousel:ke["a"],QCarouselSlide:Ie["a"],QCarouselControl:De["a"],QBtn:Q["a"],QImg:Qe["a"],QIcon:J["a"],QDialog:_["a"],QCard:q["a"],QCardSection:x["a"],QItemLabel:Ae["a"],QItem:$e["a"],QItemSection:we["a"],QSlider:je["a"]});var Ne={name:"PageIndex",components:{MovieDetail:Me,GenreBadge:s["a"]},created:function(){let e=this.$router.currentRoute.params.id;this.$q.loading.show(),this.$axios.get("/movies/"+e).then((e=>{this.movie=e.data,this.links=e.data._links,console.log("got response!"),this.$q.loading.hide()})).catch((e=>{console.error(e),this.$q.loading.hide(),this.$q.notify({color:"negative",position:"top",message:"Loading failed",icon:"report_problem"})}))},methods:{getGallery(){return this.movie._embedded.imageGallery._embedded?this.movie._embedded.imageGallery._embedded.imageDetailDTOList:[]}},data(){return{currentImage:1,links:null,movie:null,fullscreen:!1,exampleMovie:{id:2,name:"Aaj-Kalova pomsta",description:"Tento film je o tom ako Spidermanov život pokračuje, ale do cesty sa mu postaví Aaj Kal, postrach Indie.",yearMade:{year:1995,month:"JANUARY",monthValue:1,dayOfMonth:1,dayOfYear:1,chronology:{id:"ISO",calendarType:"iso8601"},dayOfWeek:"SUNDAY",era:"CE",leapYear:!1},countryCode:"USA",lengthMin:150,genres:[{id:2,name:"romantic"},{id:3,name:"animated"}],actors:[{id:3,name:"Eminem Zamlada"},{id:6,name:"Daniel Dangl"},{id:4,name:"Dr. Dre"},{id:5,name:"Jožko Vajda"}],director:{id:2,name:"Jana Kratochvilova"},ratingAgg:null,ratingUser:null,_embedded:{titleImage:{id:2,image:"binarny string",imageMimeType:"image/jpeg",_links:{self:{href:"http://localhost:8080/pa165/api/v1/images/url/2"}}},imageGallery:{_embedded:{imageDetailDTOList:[{id:7,image:"binarny string",imageMimeType:"image/jpeg",_links:{self:{href:"http://localhost:8080/pa165/api/v1/images/url/7"}}},{id:8,image:"binarny string",imageMimeType:"image/jpeg",_links:{self:{href:"http://localhost:8080/pa165/api/v1/images/url/8"}}}]}}},_links:{self:{href:"http://localhost:8080/pa165/api/v1/movies/2"},browse:{href:"http://localhost:8080/pa165/api/v1/movies"}}}}}},Ge=Ne,Te=(i("910e"),i("9989")),Ue=Object(u["a"])(Ge,a,n,!1,null,"103b5ae2",null);t["default"]=Ue.exports;w()(Ue,"components",{QPage:Te["a"]})},"910e":function(e,t,i){"use strict";i("f06b")},f06b:function(e,t,i){}}]);