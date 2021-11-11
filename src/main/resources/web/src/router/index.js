import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Stations from '@/components/pages/Stations'
import Ways from '@/components/pages/Ways'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/stations',
      name: 'Stations',
      component: Stations
    },
    {
      path: '/ways',
      name: 'Ways',
      component: Ways
    }
  ]
})
