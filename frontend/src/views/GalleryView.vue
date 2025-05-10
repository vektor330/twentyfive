<template>
  <PhotoGrid :images="images" @select="showImg" />
  <vue-easy-lightbox :visible="visibleRef" :imgs="images.filter(img => img !== null).map(img => img!.url)"
    :index="indexRef" @hide="onHide"></vue-easy-lightbox>
  <div v-if="isAuthenticated" class="upload-form">
    <h3>Upload a Photo</h3>
    <form @submit.prevent="handleUpload">
      <label>
        Position:
        <select v-model="selectedPosition">
          <option v-for="n in 25" :key="n" :value="n - 1">{{ n }}</option>
        </select>
      </label>
      <label>
        File:
        <input type="file" @change="onFileChange" />
      </label>
      <button type="submit">Upload</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import PhotoGrid from '../components/PhotoGrid.vue'
import VueEasyLightbox from 'vue-easy-lightbox'
import { useGalleryService } from '../services/apiService'
import { useAuthService } from '../services/authService'

const { images, loadGallery, uploadPicture } = useGalleryService()
const { isAuthenticated } = useAuthService()
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

// Upload form logic
const selectedPosition = ref(0)
const selectedFile = ref<File | null>(null)

function onFileChange(e: Event) {
  const files = (e.target as HTMLInputElement).files
  selectedFile.value = files && files.length > 0 ? files[0] : null
}

// TODO improvement: only show the upload form if the user has the appropriate permission (failed to extract it from the JWT token)
// TODO refactoring: move the upload logic to a separate component
async function handleUpload() {
  if (!selectedFile.value) {
    alert('Please select a file to upload.')
    return
  }
  try {
    const result = await uploadPicture(selectedFile.value, selectedPosition.value + 1)
    // Update local state with new image using backend id and url
    // TODO improvement: ideally let's refresh the whole gallery from the backend
    images.value[selectedPosition.value] = {
      id: result.id,
      url: result.url
    }
    selectedFile.value = null
  } catch (err) {
    alert('Upload error: ' + (err instanceof Error ? err.message : err))
  }
}
</script>

<style scoped>
.upload-form {
  margin: 24px auto;
  padding: 16px;
  max-width: 400px;
  background: var(--background, #f9f9f9);
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

.upload-form h3 {
  margin-bottom: 12px;
}

.upload-form label {
  display: block;
  margin-bottom: 12px;
}

.upload-form select,
.upload-form input[type="file"] {
  margin-left: 8px;
}

.upload-form button {
  padding: 8px 16px;
  border-radius: 4px;
  border: none;
  background-color: var(--placeholder-background, #eee);
  color: var(--text, #222);
  cursor: pointer;
}

.upload-form button:hover {
  opacity: 0.85;
}
</style>
