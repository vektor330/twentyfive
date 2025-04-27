<template>
  <PhotoGrid :images="images" @select="showImg" />
  <vue-easy-lightbox :visible="visibleRef" :imgs="images.filter(img => img !== null).map(img => img!.url)"
    :index="indexRef" @hide="onHide"></vue-easy-lightbox>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import PhotoGrid from '../components/PhotoGrid.vue'
import VueEasyLightbox from 'vue-easy-lightbox'
import { useGalleryService } from '../services/apiService'

const { images, loadGallery } = useGalleryService()
const route = useRoute()

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
  loadGallery(userSlug, gallerySlug)
})
</script>
