import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import GalleryView from '../views/GalleryView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: GalleryView,
  },
  {
    path: '/g/:userSlug?/:gallerySlug?',
    name: 'Gallery',
    component: GalleryView,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
