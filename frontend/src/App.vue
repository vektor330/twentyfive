<template>
  <div class="photo-grid">
    <div v-for="i in 25" :key="i" class="photo-cell">
      <div class="placeholder">
        {{ i }}
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// For now, we don't need any script logic
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

.placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999999;
  font-size: 1.5rem;
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
