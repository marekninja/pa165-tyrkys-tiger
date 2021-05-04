const routes = [{
        path: '/',
        component: () =>
            import ('layouts/MainLayout.vue'),
        children: [{
                path: '',
                component: () =>
                    import ('pages/Index.vue')
            },
            {
                path: '/browse',
                component: () =>
                    import ('pages/Browse.vue')
            },
            {
                path: '/user',
                component: () =>
                    import ('pages/User.vue')
            },
            {
                path: '/admin',
                component: () =>
                    import ('pages/AdminHome.vue')
            }
        ]
    },
    {
        path: '/movie',
        component: () =>
            import ('layouts/MainLayout.vue'),
        children: [{
            path: '',
            component: () =>
                import ('src/pages/MovieDetailPage.vue')
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