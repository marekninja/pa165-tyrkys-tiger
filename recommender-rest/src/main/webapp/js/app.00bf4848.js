(function(e){function t(t){for(var r,o,l=t[0],u=t[1],c=t[2],s=0,f=[];s<l.length;s++)o=l[s],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&f.push(a[o][0]),a[o]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(e[r]=u[r]);d&&d(t);while(f.length)f.shift()();return i.push.apply(i,c||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],r=!0,o=1;o<n.length;o++){var l=n[o];0!==a[l]&&(r=!1)}r&&(i.splice(t--,1),e=u(u.s=n[0]))}return e}var r={},o={2:0},a={2:0},i=[];function l(e){return u.p+"js/"+({}[e]||e)+"."+{1:"5b1ea4b4",3:"3e999c31",4:"1599e9fb",5:"2d9d6995",6:"edda36db",7:"f0dd7464",8:"1598f9e1"}[e]+".js"}function u(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n={1:1,3:1,4:1,5:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{1:"19867dfa",3:"70e63209",4:"c3b3eb74",5:"d28513c4",6:"31d6cfe0",7:"31d6cfe0",8:"31d6cfe0"}[e]+".css",a=u.p+r,i=document.getElementsByTagName("link"),l=0;l<i.length;l++){var c=i[l],s=c.getAttribute("data-href")||c.getAttribute("href");if("stylesheet"===c.rel&&(s===r||s===a))return t()}var f=document.getElementsByTagName("style");for(l=0;l<f.length;l++){c=f[l],s=c.getAttribute("data-href");if(s===r||s===a)return t()}var d=document.createElement("link");d.rel="stylesheet",d.type="text/css",d.onload=t,d.onerror=function(t){var r=t&&t.target&&t.target.src||a,i=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");i.code="CSS_CHUNK_LOAD_FAILED",i.request=r,delete o[e],d.parentNode.removeChild(d),n(i)},d.href=a;var p=document.getElementsByTagName("head")[0];p.appendChild(d)})).then((function(){o[e]=0})));var r=a[e];if(0!==r)if(r)t.push(r[2]);else{var i=new Promise((function(t,n){r=a[e]=[t,n]}));t.push(r[2]=i);var c,s=document.createElement("script");s.charset="utf-8",s.timeout=120,u.nc&&s.setAttribute("nonce",u.nc),s.src=l(e);var f=new Error;c=function(t){s.onerror=s.onload=null,clearTimeout(d);var n=a[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;f.message="Loading chunk "+e+" failed.\n("+r+": "+o+")",f.name="ChunkLoadError",f.type=r,f.request=o,n[1](f)}a[e]=void 0}};var d=setTimeout((function(){c({type:"timeout",target:s})}),12e4);s.onerror=s.onload=c,document.head.appendChild(s)}return Promise.all(t)},u.m=e,u.c=r,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)u.d(n,r,function(t){return e[t]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="",u.oe=function(e){throw console.error(e),e};var c=window["webpackJsonp"]=window["webpackJsonp"]||[],s=c.push.bind(c);c.push=t,c=c.slice();for(var f=0;f<c.length;f++)t(c[f]);var d=s;i.push([0,0]),n()})({0:function(e,t,n){e.exports=n("2f39")},"2f39":function(e,t,n){"use strict";n.r(t);n("5319"),n("7d6e"),n("e54f"),n("985d"),n("31cd");var r=n("2b0e"),o=n("1f91"),a=n("42d2"),i=n("b05d"),l=n("2a19"),u=n("f508");r["a"].use(i["a"],{config:{},lang:o["a"],iconSet:a["a"],plugins:{Notify:l["a"],Loading:u["a"]}});var c=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"q-app"}},[n("router-view")],1)},s=[],f={name:"App"},d=f,p=n("2877"),h=Object(p["a"])(d,c,s,!1,null,null,null),m=h.exports,b=n("2f62");r["a"].use(b["a"]);var v=function(){const e=new b["a"].Store({modules:{},strict:!1});return e},g=n("8c4f");n("ddb0");const y=[{path:"/",component:()=>Promise.all([n.e(0),n.e(1)]).then(n.bind(null,"713b")),children:[{path:"",component:()=>Promise.all([n.e(0),n.e(4)]).then(n.bind(null,"8b24"))},{path:"/browse",component:()=>Promise.all([n.e(0),n.e(3)]).then(n.bind(null,"aba2"))},{path:"/user",component:()=>Promise.all([n.e(0),n.e(6)]).then(n.bind(null,"d843"))},{path:"/admin",component:()=>Promise.all([n.e(0),n.e(7)]).then(n.bind(null,"5218"))}]},{path:"/movie/:id",component:()=>Promise.all([n.e(0),n.e(1)]).then(n.bind(null,"713b")),children:[{path:"",component:()=>Promise.all([n.e(0),n.e(5)]).then(n.bind(null,"1f6b"))}]},{path:"*",component:()=>Promise.all([n.e(0),n.e(8)]).then(n.bind(null,"e51e"))}];var w=y;r["a"].use(g["a"]);var P=function(){const e=new g["a"]({scrollBehavior:()=>({x:0,y:0}),routes:w,mode:"hash",base:""});return e},O=async function(){const e="function"===typeof v?await v({Vue:r["a"]}):v,t="function"===typeof P?await P({Vue:r["a"],store:e}):P;e.$router=t;const n={router:t,store:e,render:e=>e(m),el:"#q-app"};return{app:n,store:e,router:t}},j=n("a925"),x={failed:"Action failed",success:"Action was successful"},S={"en-us":x};r["a"].use(j["a"]);const _=new j["a"]({locale:"en-us",fallbackLocale:"en-us",messages:S});var E=({app:e})=>{e.i18n=_},A=n("bc3a"),k=n.n(A);const C=k.a.create({baseURL:"http://localhost:8080/pa165/api/v1"});r["a"].prototype.$axios=C;const L="";async function T(){const{app:e,store:t,router:n}=await O();let o=!1;const a=e=>{o=!0;const t=Object(e)===e?n.resolve(e).route.fullPath:e;window.location.href=t},i=window.location.href.replace(window.location.origin,""),l=[E,void 0];for(let c=0;!1===o&&c<l.length;c++)if("function"===typeof l[c])try{await l[c]({app:e,router:n,store:t,Vue:r["a"],ssrContext:null,redirect:a,urlPath:i,publicPath:L})}catch(u){return u&&u.url?void(window.location.href=u.url):void console.error("[Quasar] boot error:",u)}!0!==o&&new r["a"](e)}T()},"31cd":function(e,t,n){}});