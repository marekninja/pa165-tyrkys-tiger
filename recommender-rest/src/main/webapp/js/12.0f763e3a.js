(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[12],{3386:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("q-page",{attrs:{padding:""}},[a("span",{staticClass:"text-h2"},[e._v("\n    Update user data:\n  ")]),a("q-form",{staticClass:"q-mx-auto column justify-center",staticStyle:{"max-width":"400px"},on:{submit:e.submit}},[a("q-input",{attrs:{filled:"",label:"Nickname",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<50||"Can not be more than 50 chars"}]},model:{value:e.userData.nickName,callback:function(t){e.$set(e.userData,"nickName",t)},expression:"userData.nickName"}}),a("q-input",{attrs:{filled:"",autogrow:"",label:"Name","lazy-rules":"",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<255||"Can not be more than 255 chars"}]},model:{value:e.userData.name,callback:function(t){e.$set(e.userData,"name",t)},expression:"userData.name"}}),a("q-input",{attrs:{type:"email",filled:"",autogrow:"",label:"E-mail",rules:[function(e){return e&&e.length>0||"Please type something"},function(e){return e&&e.length<255||"Can not be more than 255 chars"}]},model:{value:e.userData.email,callback:function(t){e.$set(e.userData,"email",t)},expression:"userData.email"}}),a("q-date",{staticClass:"q-mx-auto",attrs:{mask:"YYYY-MM-DD","default-view":"Calendar",options:e.notFutureYear,"navigation-max-year-month":e.max_year_month},model:{value:e.userData.dateOfBirth,callback:function(t){e.$set(e.userData,"dateOfBirth",t)},expression:"userData.dateOfBirth"}}),a("div",{staticClass:"row justify-center"},[a("q-btn",{attrs:{flat:"",label:"Update",type:"submit",color:"positive"}})],1)],1)],1)},s=[],r=(a("5319"),a("bf19"),a("1069")),u={name:"UserHome",data(){return{userData:{nickName:null,name:null,email:null,dateOfBirth:null}}},created:function(){this.getUserPaswordless()},computed:{max_year_month:function(){return(new Date).toJSON().slice(0,7).replace(/-/g,"/")}},methods:{getUserPaswordless(){this.$axios.get("/users/nickname/"+this.$store.getters["auth/user"].username).then((e=>{this.userData=e.data})).catch((e=>{r["a"].notifyNegatResp(e)}))},submit(){console.log("not yet working"),r["a"].notifyNegat("not yet working"),this.$axios.put("/users/update",this.userData).then((e=>{r["a"].notifyPosit("Update sent!"),this.$router.go()})).catch((e=>{r["a"].notifyNegatResp(e)}))},notFutureYear(){return new Date(this.userData.dateOfBirth)<=new Date}}},i=u,l=a("2877"),o=a("9989"),c=a("0378"),m=a("27f9"),h=a("52ee"),f=a("9c40"),p=a("eebe"),d=a.n(p),g=Object(l["a"])(i,n,s,!1,null,null,null);t["default"]=g.exports;d()(g,"components",{QPage:o["a"],QForm:c["a"],QInput:m["a"],QDate:h["a"],QBtn:f["a"]})}}]);