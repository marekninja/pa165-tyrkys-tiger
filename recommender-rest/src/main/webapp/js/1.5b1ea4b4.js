(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[1],{"63b1":function(t,e,a){},"713b":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("q-layout",{attrs:{view:"hHh lpR lff"}},[a("q-header",{staticClass:"bg-primary text-white",attrs:{elevated:""}},[a("q-toolbar",[a("q-btn",{attrs:{dense:"",flat:"",round:"",icon:"menu"},on:{click:function(e){t.leftDrawerOpen=!t.leftDrawerOpen}}}),a("q-toolbar-title",[a("q-avatar",[a("q-img",{attrs:{src:"/icons/favicon-32x32.png",srcset:"/icons/favicon-32x32.png 300w,\n                      /icons/favicon-96x96.png 3x,\n                      /icons/favicon-128x128.png 4x"}})],1),t._v("\n        Movie Recommender System\n      ")],1),a("q-btn",{staticClass:"q-mr-xs",attrs:{flat:"",round:"",dense:"",icon:"login"},on:{click:function(e){t.loginDialogVisible=!0}}}),a("q-btn",{attrs:{flat:"",round:"",dense:"",icon:t.dark_mode},on:{click:t.darkToggle}})],1)],1),a("q-dialog",{model:{value:t.loginDialogVisible,callback:function(e){t.loginDialogVisible=e},expression:"loginDialogVisible"}},[a("q-card",{staticClass:"q-px-sm q-pb-md",staticStyle:{"max-width":"500px"}},[a("q-form",{staticClass:"q-gutter-md",on:{submit:t.onSubmit,reset:t.onReset}},[a("q-card-section",[a("div",{staticClass:"text-h3"},[t._v("\n            Log in:\n          ")])]),a("q-card-section",[a("q-input",{attrs:{filled:"",label:"Your nickname *","lazy-rules":"",rules:[function(t){return t&&t.length>0||"Please type something"}]},model:{value:t.nickname,callback:function(e){t.nickname=e},expression:"nickname"}})],1),a("q-card-section",[a("q-input",{attrs:{filled:"",type:t.isPwd?"password":"text",hint:"Password with toggle"},scopedSlots:t._u([{key:"append",fn:function(){return[a("q-icon",{staticClass:"cursor-pointer",attrs:{name:t.isPwd?"visibility_off":"visibility"},on:{click:function(e){t.isPwd=!t.isPwd}}})]},proxy:!0}]),model:{value:t.password,callback:function(e){t.password=e},expression:"password"}})],1),a("q-card-section",[a("q-btn",{attrs:{label:"Submit",type:"submit",color:"primary"}}),a("q-btn",{staticClass:"q-ml-sm",attrs:{label:"Reset",type:"reset",color:"primary",flat:""}})],1)],1)],1)],1),a("q-drawer",{attrs:{side:"left",overlay:"",elevated:""},model:{value:t.leftDrawerOpen,callback:function(e){t.leftDrawerOpen=e},expression:"leftDrawerOpen"}},[a("q-list",[a("q-item-label",{staticClass:"text-grey-8",attrs:{header:""}},[t._v("\n        Essential Links\n      ")]),t._l(t.essentialLinks,(function(e){return a("q-item",{directives:[{name:"ripple",rawName:"v-ripple"}],key:e.to,staticClass:"row",attrs:{to:e.to,clickable:"",exact:""}},[a("q-item-section",{attrs:{avatar:""}},[a("q-icon",{attrs:{name:e.icon}})],1),a("q-item-section",[a("q-item-label",[t._v(" "+t._s(e.label)+" ")])],1)],1)}))],2)],1),a("q-page-container",{staticClass:"container-width "},[a("router-view")],1),a("q-footer",{staticClass:"bg-grey-8 text-white row ",attrs:{bordered:""}},[a("q-toolbar",[a("q-toolbar-title",{staticClass:"row justify-center items-center q-gutter-md"},[a("q-avatar",[a("q-img",{attrs:{src:"/icons/favicon-32x32.png",srcset:"/icons/favicon-32x32.png 300w,\n                      /icons/favicon-96x96.png 3x,\n                      /icons/favicon-128x128.png 4x"}})],1),a("div",[t._v("\n        Tyrkis Tiger © 2021\n        ")])],1)],1)],1)],1)},n=[],s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("q-item",{attrs:{clickable:"",tag:"a",target:"_blank",href:t.link}},[t.icon?a("q-item-section",{attrs:{avatar:""}},[a("q-icon",{attrs:{name:t.icon}})],1):t._e(),a("q-item-section",[a("q-item-label",[t._v(t._s(t.title))]),a("q-item-label",{attrs:{caption:""}},[t._v("\n      "+t._s(t.caption)+"\n    ")])],1)],1)},o=[],r={name:"EssentialLink",props:{title:{type:String,required:!0},caption:{type:String,default:""},link:{type:String,default:"#"},icon:{type:String,default:""}}},l=r,c=a("2877"),d=a("66e5"),m=a("4074"),u=a("0016"),p=a("0170"),f=a("eebe"),b=a.n(f),q=Object(c["a"])(l,s,o,!1,null,null,null),g=q.exports;b()(q,"components",{QItem:d["a"],QItemSection:m["a"],QIcon:u["a"],QItemLabel:p["a"]});const v=[{label:"Recommended",icon:"theaters",to:"/"},{label:"Browse",icon:"search",to:"/browse"},{label:"My Account",icon:"account_circle",to:"/user"},{label:"Administrator",icon:"verified_user",to:"/admin"}];var w={name:"MainLayout",components:{EssentialLink:g},data(){return{leftDrawerOpen:!1,essentialLinks:v,dark_mode:"dark_mode",loginDialogVisible:!1,slideVol:39,slideAlarm:56,slideVibration:63,nickname:"",password:"",isPwd:!0}},methods:{darkToggle(){this.$q.dark.set(!this.$q.dark.isActive),this.$q.dark.isActive?this.dark_mode="light_mode":this.dark_mode="dark_mode"},onSubmit(){this.$q.notify({color:"green-4",textColor:"white",icon:"cloud_done",message:"Submitted"})},onReset(){this.nickname=null,this.password=null}}},k=w,h=(a("ef9c"),a("4d5a")),x=a("e359"),y=a("65c6"),_=a("9c40"),Q=a("6ac5"),C=a("cb32"),S=a("068f"),D=a("24e8"),L=a("f09f"),I=a("0378"),O=a("a370"),P=a("27f9"),R=a("9404"),T=a("1c1c"),V=a("09e3"),A=a("7ff0"),$=a("714f"),E=Object(c["a"])(k,i,n,!1,null,"e92011b6",null);e["default"]=E.exports;b()(E,"components",{QLayout:h["a"],QHeader:x["a"],QToolbar:y["a"],QBtn:_["a"],QToolbarTitle:Q["a"],QAvatar:C["a"],QImg:S["a"],QDialog:D["a"],QCard:L["a"],QForm:I["a"],QCardSection:O["a"],QInput:P["a"],QIcon:u["a"],QDrawer:R["a"],QList:T["a"],QItemLabel:p["a"],QItem:d["a"],QItemSection:m["a"],QPageContainer:V["a"],QFooter:A["a"]}),b()(E,"directives",{Ripple:$["a"]})},ef9c:function(t,e,a){"use strict";a("63b1")}}]);