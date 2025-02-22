<template>
  <div class="photo-grid root">
    <div v-for="(image, index) in images" :key="index" class="photo-cell" @click="image && showImg(index)">
      <img v-if="image" :src="image.url" :alt="`Photo ${index + 1}`" class="photo" />
      <div v-else class="photo-placeholder">{{ index + 1 }}</div>
    </div>
  </div>
  <vue-easy-lightbox :visible="visibleRef" :imgs="images.filter(img => img !== null).map(img => img!.url)"
    :index="indexRef" @hide="onHide"></vue-easy-lightbox>
  <footer class="footer">
    HTTP <span v-if="healthCode !== null">{{ healthCode }}</span>
    <span v-else>Loading...</span>
  </footer>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue'
import VueEasyLightbox from 'vue-easy-lightbox'

import { API_BASE_URL } from './config'

const TOTAL_IMAGES = 25

interface Picture {
  id: string;
  url: string;
}

const images = ref<(Picture | null)[]>(Array(TOTAL_IMAGES).fill(null))

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

const healthCode = ref<number | null>(null)

onMounted(async () => {
  const apiBaseUrl = import.meta.env.DEV
    ? API_BASE_URL.development
    : API_BASE_URL.production;

  try {
    const healthResponse = await fetch(`${apiBaseUrl}/health`)
    healthCode.value = healthResponse.status

    const galleryResponse = await fetch(`${apiBaseUrl}/gallery`)
    const galleryData = await galleryResponse.json()
    const fetchedImages = galleryData.pictures as Picture[]
    fetchedImages.forEach((img, index) => {
      if (index < TOTAL_IMAGES) {
        images.value[index] = img
      }
    })
  } catch (error) {
    console.error('Error fetching data:', error)
    healthCode.value = 0
  }
})
</script>

<style scoped>
.photo-grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 8px;
  padding: 8px;
  width: min(calc(100vh - 120px), calc(100vw - 16px));
  height: min(calc(100vh - 120px), calc(100vw - 16px));
  margin: 60px auto;
  box-sizing: border-box;
}

.photo-cell {
  aspect-ratio: 1;
  background-color: var(--placeholder-background);
  border-radius: 4px;
  cursor: pointer;
}

.photo-placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999999;
  font-size: 1.5rem;
}

.photo {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 4px;
}

.footer {
  text-align: center;
  padding: 16px;
  font-size: 1rem;
  color: var(--text);
}

/* Tablet/medium screen layout */
@media (max-width: 900px) {
  .photo-grid {
    grid-template-columns: repeat(3, 1fr);
    width: calc(100vw - 64px);
    height: auto;
    margin: 32px auto;
  }

  .photo-cell {
    width: 100%;
  }
}

/* Mobile layout */
@media (max-width: 600px) {
  .photo-grid {
    grid-template-columns: 1fr;
    width: calc(100vw - 32px);
    height: auto;
    margin: 16px auto;
  }

  .photo-cell {
    width: 100%;
  }
}
</style>
