(function(e){function t(t){for(var r,o,s=t[0],u=t[1],c=t[2],l=0,d=[];l<s.length;l++)o=s[l],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&d.push(a[o][0]),a[o]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(e[r]=u[r]);f&&f(t);while(d.length)d.shift()();return i.push.apply(i,c||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],r=!0,o=1;o<n.length;o++){var s=n[o];0!==a[s]&&(r=!1)}r&&(i.splice(t--,1),e=u(u.s=n[0]))}return e}var r={},o={2:0},a={2:0},i=[];function s(e){return u.p+"js/"+({}[e]||e)+"."+{1:"5fbd809d",3:"cdbb2b7b",4:"23134440",5:"1fce6299",6:"edda36db",7:"f0dd7464",8:"1598f9e1"}[e]+".js"}function u(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n={1:1,3:1,4:1,5:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{1:"2fbee96f",3:"21f36750",4:"2d2cd7f8",5:"90e809a7",6:"31d6cfe0",7:"31d6cfe0",8:"31d6cfe0"}[e]+".css",a=u.p+r,i=document.getElementsByTagName("link"),s=0;s<i.length;s++){var c=i[s],l=c.getAttribute("data-href")||c.getAttribute("href");if("stylesheet"===c.rel&&(l===r||l===a))return t()}var d=document.getElementsByTagName("style");for(s=0;s<d.length;s++){c=d[s],l=c.getAttribute("data-href");if(l===r||l===a)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var r=t&&t.target&&t.target.src||a,i=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=r,delete o[e],f.parentNode.removeChild(f),n(i)},f.href=a;var m=document.getElementsByTagName("head")[0];m.appendChild(f)})).then((function(){o[e]=0})));var r=a[e];if(0!==r)if(r)t.push(r[2]);else{var i=new Promise((function(t,n){r=a[e]=[t,n]}));t.push(r[2]=i);var c,l=document.createElement("script");l.charset="utf-8",l.timeout=120,u.nc&&l.setAttribute("nonce",u.nc),l.src=s(e);var d=new Error;c=function(t){l.onerror=l.onload=null,clearTimeout(f);var n=a[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+r+": "+o+")",d.name="ChunkLoadError",d.type=r,d.request=o,n[1](d)}a[e]=void 0}};var f=setTimeout((function(){c({type:"timeout",target:l})}),12e4);l.onerror=l.onload=c,document.head.appendChild(l)}return Promise.all(t)},u.m=e,u.c=r,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)u.d(n,r,function(t){return e[t]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="",u.oe=function(e){throw console.error(e),e};var c=window["webpackJsonp"]=window["webpackJsonp"]||[],l=c.push.bind(c);c.push=t,c=c.slice();for(var d=0;d<c.length;d++)t(c[d]);var f=l;i.push([0,0]),n()})({0:function(e,t,n){e.exports=n("2f39")},1069:function(e,t,n){"use strict";var r=n("2a19");class o{notifyPosit(e){r["a"].create({color:"green-4",textColor:"white",icon:"cloud_done",message:e})}notifyNegat(e){r["a"].create({color:"negative",textColor:"white",icon:"error",message:e})}notifyNegatResp(e){console.log(JSON.stringify(e,null,1)),this.message=e.response&&e.response.data||e.message||e.toString(),this.notifyNegat(this.message)}}t["a"]=new o},"2f39":function(e,t,n){"use strict";n.r(t);var r={};n.r(r),n.d(r,"user",(function(){return N}));var o={};n.r(o),n.d(o,"saveNickname",(function(){return k})),n.d(o,"savePassword",(function(){return j})),n.d(o,"setAdmin",(function(){return I})),n.d(o,"saveEmail",(function(){return A}));var a={};n.r(a),n.d(a,"saveNickname",(function(){return E})),n.d(a,"savePassword",(function(){return x})),n.d(a,"setAdmin",(function(){return _})),n.d(a,"saveEmail",(function(){return C}));var i={};n.r(i),n.d(i,"user",(function(){return T})),n.d(i,"loggedIn",(function(){return B}));var s={};n.r(s),n.d(s,"loginSuccess",(function(){return F})),n.d(s,"loginFailure",(function(){return M})),n.d(s,"logout",(function(){return $})),n.d(s,"registerSuccess",(function(){return q})),n.d(s,"registerFailure",(function(){return H}));var u={};n.r(u),n.d(u,"login",(function(){return K})),n.d(u,"logout",(function(){return Q})),n.d(u,"register",(function(){return Y}));n("5319"),n("7d6e"),n("e54f"),n("985d"),n("31cd");var c=n("2b0e"),l=n("1f91"),d=n("42d2"),f=n("b05d"),m=n("2a19"),p=n("f508");c["a"].use(f["a"],{config:{},lang:l["a"],iconSet:d["a"],plugins:{Notify:m["a"],Loading:p["a"]}});var g=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"q-app"}},[n("router-view")],1)},h=[],v={name:"App"},b=v,y=n("2877"),w=Object(y["a"])(b,g,h,!1,null,null,null),P=w.exports,S=n("2f62"),O=function(){return{user:{id:1,name:"Milanko Háčik",nickName:"milani$$",email:"milanko@azet.sk",administrator:!1,passwordHash:"heslo",dateOfBirth:null}}};function N(e){return e.user}function k(e,t){e.user.nickname=t}function j(e,t){e.user.pass=t}function I(e,t){e.user.isAdministrator=t}function A(e,t){e.user.email=t}function E(e,t){e.commit("saveNickname",t)}function x(e,t){e.commit("savePassword",t)}function _(e,t){e.commit("setAdmin",t)}function C(e,t){e.commit("saveEmail",t)}var L={namespaced:!0,getters:r,mutations:o,actions:a,state:O},J=function(){return{user:JSON.parse(localStorage.getItem("user")),status:{loggedIn:!!localStorage.getItem("user")}}};function T(e){return e.user}function B(e){return e.status.loggedIn}function F(e,t){e.status.loggedIn=!0,e.user=t}function M(e){e.status.loggedIn=!1,e.user=null}function $(e){e.status.loggedIn=!1,e.user=null}function q(e){e.status.loggedIn=!1}function H(e){e.status.loggedIn=!1}var V=n("bc3a"),D=n.n(V);const R="http://localhost:8080/api/auth/";class U{login(e){return D.a.post(R+"signin",{username:e.username,password:e.password}).then((e=>(e.data.accessToken&&localStorage.setItem("user",JSON.stringify(e.data)),e.data))).catch((t=>{var n={username:e.username,token:"token",isAdmin:"true"};return localStorage.setItem("user",JSON.stringify(n)),n}))}logout(){localStorage.removeItem("user")}register(e){return D.a.post(R+"signup",{username:e.username,email:e.email,password:e.password})}}var z=new U;function K(e,t){return z.login(t).then((t=>(e.commit("loginSuccess",t),Promise.resolve(t))),(t=>(e.commit("loginFailure"),Promise.reject(t))))}function Q(e){z.logout(),e.commit("logout")}function Y(e,t){return z.register(t).then((t=>(e.commit("registerSuccess"),Promise.resolve(t.data))),(t=>(e.commit("registerFailure"),Promise.reject(t))))}var G={namespaced:!0,getters:i,mutations:s,actions:u,state:J};c["a"].use(S["a"]);var W=function(){const e=new S["a"].Store({modules:{global:L,auth:G},strict:!1});return e},X=n("a18c"),Z=async function(){const e="function"===typeof W?await W({Vue:c["a"]}):W,t="function"===typeof X["a"]?await Object(X["a"])({Vue:c["a"],store:e}):X["a"];e.$router=t;const n={router:t,store:e,render:e=>e(P),el:"#q-app"};return{app:n,store:e,router:t}},ee=n("a925"),te={failed:"Action failed",success:"Action was successful"},ne={"en-us":te};c["a"].use(ee["a"]);const re=new ee["a"]({locale:"en-us",fallbackLocale:"en-us",messages:ne});var oe=({app:e})=>{e.i18n=re};const ae=D.a.create({baseURL:"/pa165/api/v1"});c["a"].prototype.$axios=ae;const ie="";async function se(){const{app:e,store:t,router:n}=await Z();let r=!1;const o=e=>{r=!0;const t=Object(e)===e?n.resolve(e).route.fullPath:e;window.location.href=t},a=window.location.href.replace(window.location.origin,""),i=[oe,void 0];for(let u=0;!1===r&&u<i.length;u++)if("function"===typeof i[u])try{await i[u]({app:e,router:n,store:t,Vue:c["a"],ssrContext:null,redirect:o,urlPath:a,publicPath:ie})}catch(s){return s&&s.url?void(window.location.href=s.url):void console.error("[Quasar] boot error:",s)}!0!==r&&new c["a"](e)}se()},"31cd":function(e,t,n){},a18c:function(e,t,n){"use strict";var r=n("2b0e"),o=n("8c4f"),a=n("d046");r["a"].use(o["a"]),t["a"]=function({store:e}){const t=new o["a"]({scrollBehavior:()=>({x:0,y:0}),routes:a["a"],mode:"hash",base:""});return t}},d046:function(e,t,n){"use strict";n("ddb0");var r=n("1069");const o=[{path:"/",component:()=>Promise.all([n.e(0),n.e(1)]).then(n.bind(null,"713b")),children:[{path:"",component:()=>Promise.all([n.e(0),n.e(3)]).then(n.bind(null,"aba2"))},{path:"/recommended",component:()=>Promise.all([n.e(0),n.e(4)]).then(n.bind(null,"8b24"))}]},{path:"/movie/:id",component:()=>Promise.all([n.e(0),n.e(1)]).then(n.bind(null,"713b")),children:[{path:"",component:()=>Promise.all([n.e(0),n.e(5)]).then(n.bind(null,"1f6b"))}]},{path:"/user",beforeEnter:(e,t,n)=>{localStorage.getItem("user")?n():(n("/"),r["a"].notifyNegat("Log in first!"))},component:()=>Promise.all([n.e(0),n.e(1)]).then(n.bind(null,"713b")),children:[{path:"",component:()=>Promise.all([n.e(0),n.e(6)]).then(n.bind(null,"d843"))}]},{path:"/admin",beforeEnter:(e,t,n)=>{const o=JSON.parse(localStorage.getItem("user"));console.log("admin protect ",JSON.stringify(o)),o&&o.isAdmin?n():(n("/"),r["a"].notifyNegat("You are not admin!"))},component:()=>Promise.all([n.e(0),n.e(1)]).then(n.bind(null,"713b")),children:[{path:"",component:()=>Promise.all([n.e(0),n.e(7)]).then(n.bind(null,"5218"))}]},{path:"*",component:()=>Promise.all([n.e(0),n.e(8)]).then(n.bind(null,"e51e"))}];t["a"]=o}});