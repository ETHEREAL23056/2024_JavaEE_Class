import { createRouter, createWebHashHistory } from "vue-router";

import Index from '../pages/index.vue'
import NotFound from '../pages/404.vue'
import Main from '../pages/main.vue'
import Usage from '../pages/usage.vue'
import Bill from '../pages/bill.vue'
import Statistic from '../pages/statistic.vue'

const routes = [{
    path: "/",
    name: "home",
    component: Index
}, {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound
}, {
    path: '/main',
    name: 'Main',
    component: Main
}, {
    path: '/usage',
    name: 'Usage',
    component: Usage
}, {
    path: '/bill',
    name: 'Bill',
    component: Bill
}, {
    path: '/statistic',
    name: 'Statistic',
    component: Statistic
}
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

export default router