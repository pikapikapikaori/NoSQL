import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import testpage from '@/components/pages/testpage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/testpage',
      name: 'testpage',
      component: testpage
    }
  ]
})
