<script setup>
import missingno from '@/assets/imgs/missingno.jpg'
import JumpLinkIcon from "@/components/icons/JumpLinkIcon.vue";
import {computed, ref} from "vue";

const props = defineProps({
  project: Object
})

const showingImg = ref(false)
const showedImg = computed(() => {
  return props.project.img === undefined ? missingno : props.project.img
})

const openUrl = (url) => window.open(url, '_blank');
</script>

<template>
  <Teleport to="body">
    <Transition name="img-show-box">
      <div v-if="showingImg" class="img-show-wrapper" @click="showingImg = false">
        <div class="img-show-box">
          <img class="img-show-content" :src="showedImg" alt="" @click.stop/>
        </div>
      </div>
    </Transition>
  </Teleport>
  <div class="card-wrapper">
    <div class="card-body">
      <div class="card-img" @click="showingImg = true">
        <img :src="showedImg" alt="" loading="lazy"/>
      </div>
      <div class="card-introduction">
        <span class="introduction-title">{{ project.title }}</span>
        <div class="project-tag-list">
          <span v-for="tag in project.tags" :key="tag" class="bubble-tag project-tag">{{ tag }}</span>
        </div>
        <section class="introduction-desc">
          {{ project.desc }}
        </section>
      </div>
      <div class="card-links">
        <div class="github-link" v-show="project.link" @click="openUrl(project.link)">
          <JumpLinkIcon/>
          GitHub
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.img-show-wrapper {
  position: fixed;
  inset: 0;
  display: flex;
  background-color: rgba(0, 0, 0, 0.1);
  z-index: 1;
  justify-content: center;
  align-items: center;
}

.img-show-box {
  display: flex;
  width: 80%;
  height: 80%;
  justify-content: center;
  align-items: center;
}

.img-show-box-enter-active,
.img-show-box-leave-active {
  transition: opacity 0.5s ease;
}

.img-show-box-enter-from,
.img-show-box-leave-to {
  opacity: 0;
}

.img-show-content {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
  box-shadow: 0 1px 20px -6px rgba(0, 0, 0, 0.5);
  cursor: zoom-in
}

@media (max-width: 768px) {
  .img-show-box {
    width: 95%;
    height: 50%;
  }
}

@media (min-width: 769px) and (max-width: 1024px) {
  .img-show-box {
    width: 95%;
    height: 50%;
  }
}

.card-wrapper {
  position: relative;
  padding: 0;
  aspect-ratio: 16 / 15;
  width: 100%;
}

.card-body {
  width: 100%;
  height: 100%;
  box-shadow: 0 1px 20px -6px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  background: var(--primary-color);
  overflow: hidden;
}

.card-img {
  position: relative;
  height: 60%;
  width: 100%;
  overflow: hidden;
  user-select: none;
}

.card-img img {
  position: absolute;
  inset: 0;
  height: 100%;
  width: 100%;
  object-fit: cover;
  transition: scale 0.5s ease-out;
  pointer-events: none;
}

.card-body:hover img {
  scale: 1.1;
}

.card-introduction {
  display: flex;
  flex-direction: column;
  margin-left: 1rem;
  margin-right: 1rem;
  overflow: hidden;
  gap: 2px;
}

.introduction-title {
  font-size: 20px;
  font-weight: bold;
  color: var(--text-hover-color);
}

.project-tag-list {
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
}

.project-tag {
  user-select: none;
}

.introduction-desc {
  color: var(--text-color);
  word-break: break-all;
}

.card-links {
  position: absolute;
  display: flex;
  align-items: center;
  right: 0;
  bottom: 0;
  margin-right: 1rem;
  margin-bottom: 0.5rem;
  gap: 0.5rem;
}

.github-link {
  display: flex;
  color: var(--contrast-color);
  cursor: pointer;
  align-items: center;
  gap: 2px;
  user-select: none;
}

.github-link svg {
  width: 16px;
  height: 16px;
  padding-top: 2px;
}
</style>