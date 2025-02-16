<template>
  <div class="photo-grid">
    <div
      v-for="i in TOTAL_IMAGES"
      :key="i"
      class="photo-cell"
      @click="images[i - 1] && showImg(i - 1)"
    >
      <img v-if="images[i - 1]" :src="getImageUrl(i)" :alt="`Photo ${i}`" class="photo" />
      <div v-else class="photo-placeholder">{{ i }}</div>
    </div>
  </div>
  <vue-easy-lightbox
    :visible="visibleRef"
    :imgs="images.filter(Boolean)"
    :index="indexRef"
    @hide="onHide"
  ></vue-easy-lightbox>
  <footer class="footer">
    HTTP <span v-if="healthCode !== null">{{ healthCode }}</span>
    <span v-else>Loading...</span>
  </footer>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import VueEasyLightbox from 'vue-easy-lightbox'

const TOTAL_IMAGES = 25

const imageModules = import.meta.glob('./assets/photos/*.jpg', { eager: true })
const images = ref<string[]>([])

// Populate images array
for (let i = 1; i <= TOTAL_IMAGES; i++) {
  const key = `./assets/photos/photo${i}.jpg`
  if (key in imageModules) {
    images.value.push((imageModules[key] as { default: string }).default)
  } else {
    images.value.push('')
  }
}

const visibleRef = ref(false)
const indexRef = ref(0)

function getImageUrl(index: number): string {
  return images.value[index - 1]
}

function showImg(index: number) {
  indexRef.value = index
  visibleRef.value = true
}

function onHide() {
  visibleRef.value = false
}

const healthCode = ref<number | null>(null)

onMounted(async () => {
  try {
    const response = await fetch('/health')
    healthCode.value = response.status
  } catch (error) {
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
  background-color: #f0f0f0;
  border-radius: 4px;
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
  color: #333;
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
