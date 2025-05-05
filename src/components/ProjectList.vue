<script setup>
import ProjectCard from "@/components/ProjectCard.vue";

defineProps({
  projects: Array
})
</script>

<template>
  <div class="project-list-wrapper" v-if="projects.length > 0">
    <div class="project-list-body">
      <div class="title-wrapper">
        <span class="bubble-title">Projects</span>
      </div>
      <div class="project-list-content">
        <div v-for="project in projects" :key="project" class="project">
          <ProjectCard :project="project"></ProjectCard>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.project-list-wrapper {
  display: flex;
  padding-top: 1rem;
  padding-bottom: 1rem;
  justify-content: center;
  overflow: hidden;
}

.project-list-body{
  display: flex;
  flex-direction: column;
  width: min(100%, 80rem);
  margin-left: 3rem;
  margin-right: 3rem;
}

.project-list-content{
  padding-top: 1rem;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
  place-content: center;
}

@media (max-width: 768px) {
  .title-wrapper{
    text-align: center;
  }
  .project-list-content{
    grid-template-columns: 1fr;
  }
}

@media (min-width: 769px) and (max-width: 1024px) {
  .project-list-content{
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>