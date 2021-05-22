import NotifHelper from '../services/NotifHelper'

const routes = [{
        path: '/',
        component: () =>
            import ('layouts/MainLayout.vue'),
        children: [{
                path: '',
                component: () =>
                    import ('pages/Browse.vue')
            },
            {
                path: '/recommended',
                component: () =>
                    import ('pages/Index.vue')
            },
        ]
    },
    {
        path: '/movie/:id',
        component: () =>
            import ('layouts/MainLayout.vue'),
        children: [{
            path: '',
            component: () =>
                import ('src/pages/MovieDetailPage.vue')
        }, ]
    },
    {
        path: '/user',
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem('user')) {
                next()
            } else {
                next('/')
                NotifHelper.notifyNegat('Log in first!')
            }
        },
        component: () =>
            import ('layouts/MainLayout.vue'),
        children: [{
            path: '',
            component: () =>
                import ('pages/UserHome.vue')
        }, ]
    },
    {
        path: '/admin',
        beforeEnter: (to, from, next) => {
            const admin = JSON.parse(localStorage.getItem('user'))
            console.log('admin protect ', JSON.stringify(admin))
                // const admin = JSON.parse(localStorage.getItem('user'))
            if (admin) {
                if (admin.isAdmin) {
                    next()
                    return
                }
            }
            next('/')
            NotifHelper.notifyNegat('You are not admin!')
        },
        component: () =>
            import ('layouts/MainLayout.vue'),
        children: [{
            path: '',
            component: () =>
                import ('pages/AdminHome.vue')
        }, {
            path: 'movie',
            component: () =>
                import ('pages/CreateMovie.vue')
        }, ]

    },

    // Always leave this as last one,
    // but you can also remove it
    {
        path: '*',
        component: () =>
            import ('pages/Error404.vue')
    }
]

export default routes