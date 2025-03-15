<template>
  <div class="photo-grid">
    <div v-for="(image, index) in images" :key="index" class="photo-cell" @click="handleClick(index)">
      <img v-if="image" :src="image.url" :alt="`Photo ${index + 1}`" class="photo" />
      <div v-else class="photo-placeholder">{{ index + 1 }}</div>
    </div>
  </div>
</template>

<script setup lang="ts">
interface Picture {
  id: string
  url: string
}

const props = defineProps<{
  images: (Picture | null)[]
}>()

const emit = defineEmits<{
  (e: 'select', index: number): void
}>()

function handleClick(index: number) {
  if (props.images[index]) {
    emit('select', index)
  }
}
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
