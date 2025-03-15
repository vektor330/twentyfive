<template>
  <PhotoGrid :images="images" @select="showImg" />
  <vue-easy-lightbox :visible="visibleRef" :imgs="images.filter(img => img !== null).map(img => img!.url)"
    :index="indexRef" @hide="onHide"></vue-easy-lightbox>
  <footer class="footer">
    HTTP <span v-if="healthCode !== null">{{ healthCode }}</span>
    <span v-else>Loading...</span>
    <div class="auth-buttons">
      <button v-if="!isAuthenticated" @click="login">Log in</button>
      <button v-else @click="logout">Log out</button>
      <span v-if="isAuthenticated">Welcome, {{ user?.name }}!</span>
    </div>
  </footer>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import PhotoGrid from './components/PhotoGrid.vue'
import VueEasyLightbox from 'vue-easy-lightbox'
import { useGalleryService } from './services/apiService'
import { useAuthService } from './services/authService'

const { images, healthCode, loadGallery } = useGalleryService()

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

const { login, logout, user, isAuthenticated } = useAuthService()

onMounted(() => {
  if (isAuthenticated.value) {
    loadGallery()
  } else {
    const stopWatch = watch(isAuthenticated, (newVal) => {
      if (newVal) {
        loadGallery()
        stopWatch()
      }
    })
  }
})
</script>

<style scoped>
.footer {
  text-align: center;
  padding: 16px;
  font-size: 1rem;
  color: var(--text);
}

.auth-buttons {
  margin-top: 8px;
}

.auth-buttons button {
  margin: 0 8px;
  padding: 8px 16px;
  border-radius: 4px;
  border: none;
  background-color: var(--placeholder-background);
  color: var(--text);
  cursor: pointer;
}

.auth-buttons button:hover {
  opacity: 0.8;
}
</style>
