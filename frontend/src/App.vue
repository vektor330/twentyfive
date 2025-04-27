<template>
  <router-view></router-view>
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
import { useAuthService } from './services/authService'
import { useGalleryService } from './services/apiService'
import { onMounted } from 'vue'

const { healthCode, loadGallery } = useGalleryService()
const { login, logout, user, isAuthenticated } = useAuthService()

onMounted(() => {
  loadGallery()
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
