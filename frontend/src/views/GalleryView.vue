<template>
  <PhotoGrid :images="images" @select="showImg" />
  <vue-easy-lightbox :visible="visibleRef" :imgs="images.filter(img => img !== null).map(img => img!.url)"
    :index="indexRef" @hide="onHide"></vue-easy-lightbox>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import PhotoGrid from '../components/PhotoGrid.vue'
import VueEasyLightbox from 'vue-easy-lightbox'
import { useGalleryService } from '../services/apiService'
import { useAuthService } from '../services/authService'

const { images, loadGallery } = useGalleryService()
const route = useRoute()
const { isAuthenticated } = useAuthService()

const visibleRef = ref(false)
const indexRef = ref(0)

function showImg(index: number) {
  if (images.value[index]) {
    indexRef.value = index
    visibleRef.value = true
  }
}

function onHide() {
  visibleRef.value = false
}

onMounted(() => {
  const userSlug = route.params.userSlug as string | undefined
  const gallerySlug = route.params.gallerySlug as string | undefined

  if (isAuthenticated.value) {
    loadGallery(userSlug, gallerySlug)
  } else {
    const stopWatch = watch(isAuthenticated, (newVal) => {
      if (newVal) {
        loadGallery(userSlug, gallerySlug)
        stopWatch()
      }
    })
  }
})
</script>
