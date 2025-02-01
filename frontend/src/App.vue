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

  /* Modified height to account for header/footer space */
  height: calc(100vh - 120px); /* Reserve 60px top and bottom */
  max-width: calc(100vh - 120px);
  margin: 60px auto; /* Centers grid and creates top/bottom space */
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
  color: #999;
  font-size: 1.5rem;
}
</style>
