<script setup>
import ArrowIcon from "@/components/icons/ArrowIcon.vue";
import {scrollTo} from "@/utils/common.js";
import KotlinIcon from "@/components/icons/KotlinIcon.vue";

defineProps({
  title: String,
  desc: String,
  avatar: String,
})

</script>

<template>
  <div class="welcome-wrapper">
    <div class="card-wrapper">
      <div class="card">
        <div class="introduction">
          <span class="title">{{ title }}</span>
          <span class="description">{{ desc }}</span>
        </div>
        <div class="avatar-wrapper">
          <div class="avatar">
            <div class="avatar-img">
              <img :src="avatar" alt=""/>
            </div>
            <div class="float-skill-stack-group">
              <KotlinIcon class="float-kotlin float-icon" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="arrow-wrapper">
      <button class="arrow-button bounce" @click="scrollTo('#about')">
        <ArrowIcon/>
      </button>
    </div>
  </div>
</template>

<style scoped>
.welcome-wrapper {
  position: relative;
  display: flex;
  width: 100%;
  height: 100vh;
  justify-content: center;
  align-items: center;
  user-select: none;
  overflow: hidden;
}

.card-wrapper {
  width: 100%;
  padding: 10%;
}

.card {
  display: flex;
  align-items: center;
  width: 100%;
  justify-content: center;
  gap: 5rem;
}

.introduction {
  display: flex;
  flex-direction: column;
  row-gap: 1rem;
  opacity: 0;
  animation: fadeInRight 0.5s ease-out forwards 0.2s;
}

.title {
  white-space: nowrap;
  font-weight: bold;
  font-size: 4rem;
  color: var(--text-hover-color);
  margin: 0;
}

.description {
  font-weight: 500;
  font-size: 1.25rem;
  text-align: right;
  padding-right: 5px;
  color: var(--text-color);
  margin: 0;
}

.avatar-wrapper {
  display: flex;
  justify-content: center;
  width: min(100%, 28rem);
}

.avatar {
  position: relative;
  animation: fadeInLeft 0.5s ease-out forwards;
}

.float-skill-stack-group {
  position: absolute;
  width: 100%;
  height: 100%;
  inset: 0;
  pointer-events: none;
}

.float-kotlin {
  bottom: -0.25rem;
  right: 0;
  width: 4rem;
  height: 4rem;
}

.float-icon {
  position: absolute;
  animation: float-vertical 3s ease-in-out infinite;
}

@keyframes float-vertical {
  0% {
    transform: translate(0, 0.25rem);
  }
  50% {
    transform: translate(0, -0.25rem);
  }
  100% {
    transform: translate(0, 0.25rem);
  }
}

.avatar-img {
  width: 16rem;
  height: 16rem;
  border-radius: 50%;
  overflow: hidden;
  border: 4px solid var(--secondary-color);
  transition: transform 0.6s cubic-bezier(0.25, 0.1, 0.25, 1), border-color 0.2s;
}

.avatar-img:hover {
  transform: scale(1.1);
}

.avatar-img img {
  pointer-events: none;
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.6s cubic-bezier(0.25, 0.1, 0.25, 1);
}

.avatar-img:hover img {
  transform: rotate(360deg)
}

.arrow-wrapper {
  position: absolute;
  transform: translateY(35vh);
  opacity: 0;
  animation: fadeIn 0.8s ease-in forwards 0.5s;
}

.arrow-button {
  color: var(--text-color);
  background: none;
  border: none;
  cursor: pointer;
}

.arrow-button svg {
  width: 2rem;
  height: 2rem;
  transform: rotate(90deg);
}

@media (max-width: 768px) {
  .card {
    flex-direction: column-reverse;
  }

  .introduction {
    opacity: 0;
    animation: fadeIn 0.5s ease-in forwards 0.2s;
  }

  .avatar {
    animation: fadeIn 0.5s ease-out forwards;
  }

  .description {
    text-align: center;
  }

  .introduction {
    margin-bottom: 4rem;
  }
}

.bounce {
  animation: bounce 1s infinite;
}
</style>