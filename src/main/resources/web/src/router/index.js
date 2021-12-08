import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Stations from '@/components/pages/Stations'
import Ways from '@/components/pages/Ways'
import RoutesInformation from '@/components/pages/RoutesInformation'
import RoutesStations from '@/components/pages/RoutesStations'
import RoutesTimetable from '@/components/pages/RoutesTimetable'
import RoutesUpdate from '@/components/pages/RoutesUpdate'
import MostRoutesByStations from '@/components/pages/MostRoutesByStations'
import MostRoutesBetweenStations from '@/components/pages/MostRoutesBetweenStations'
import MostStationsInRoutes from '@/components/pages/MostStationsInRoutes'
import LongestTimeRoutes from '@/components/pages/LongestTimeRoutes'
import RoutesType from '@/components/pages/RoutesType'
import SpecialStation from '@/components/pages/SpecialStation'

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
    }, ,
    {
      path: '/routes/information',
      name: 'RoutesInformation',
      component: RoutesInformation
    },
    {
      path: '/routes/stations',
      name: 'RoutesStations',
      component: RoutesStations
    },
    {
      path: '/routes/timetable',
      name: 'RoutesTimetable',
      component: RoutesTimetable
    },
    {
      path: '/routes/update',
      name: 'RoutesUpdate',
      component: RoutesUpdate
    },
    {
      path: '/stastics/mostroutesbystations',
      name: 'MostRoutesByStations',
      component: MostRoutesByStations
    },
    {
      path: '/stastics/mostroutesbetweenstations',
      name: 'MostRoutesBetweenStations',
      component: MostRoutesBetweenStations
    },
    {
      path: '/stastics/moststationsinroutes',
      name: 'MostStationsInRoutes',
      component: MostStationsInRoutes
    },
    {
      path: '/stastics/longesttimeroutes',
      name: 'LongestTimeRoutes',
      component: LongestTimeRoutes
    },
    {
      path: '/stastics/routestype',
      name: 'RoutesType',
      component: RoutesType
    },
    {
      path: '/stastics/specialstation',
      name: 'SpecialStation',
      component: SpecialStation
    }
  ]
})
