import Vue from 'vue'
import VueRouter from 'vue-router'

import routes from './routes'

Vue.use(VueRouter)

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default function({ store /*, ssrContext*/ }) {
    const Router = new VueRouter({
        scrollBehavior: () => ({ x: 0, y: 0 }),
        routes,
        // Leave these as they are and change in quasar.conf.js instead!
        // quasar.conf.js -> build -> vueRouterMode
        // quasar.conf.js -> build -> publicPath
        mode: process.env.VUE_ROUTER_MODE,
        base: process.env.VUE_ROUTER_BASE
    }, )

    // Router.beforeEach((to, from, next) => {
    //     const userAcces = '/user';
    //     const adminAcces = '/admin';
    //     const userAuth = userAcces.includes(to.path);
    //     const adminAuth = adminAcces.includes(to.path);

    //     console.log('*************userAuth:', userAuth, 'adminAuth:', adminAuth)
    //     if (adminAuth && userAuth) {
    //         next()
    //     }

    //     console.log('user local: ', JSON.stringify(localStorage.getItem('user'), null, 1));
    //     const loggedIn = localStorage.getItem('user');
    //     var isAdmin = false;
    //     if (loggedIn) {
    //         if (loggedIn.isAdmin) {
    //             isAdmin = loggedIn.isAdmin
    //         }
    //     }
    //     console.log('isAdmin: ', isAdmin);
    //     // trying to access a restricted page + not logged in
    //     // redirect to home page
    //     if (userAuth && !loggedIn) {
    //         console.log('redirect user to / userAuth:', userAuth, ' loggedIn: ', loggedIn)
    //         next('/');
    //     }
    //     if (userAuth && loggedIn) {
    //         console.log('user is ok userAuth:', userAuth, ' loggedIn: ', loggedIn)
    //         next()
    //     }
    //     // trying to access a admin page + not admin
    //     if (adminAuth && !isAdmin) {
    //         console.log('redirecting admin to / adminAuth:', adminAuth, ' isAdmin: ', isAdmin)
    //         next('/');
    //     }
    //     if (adminAuth && isAdmin) {
    //         console.log('admin is ok adminAuth:', adminAuth, ' isAdmin: ', isAdmin)
    //         next();
    //     }
    // })

    return Router
}