<script setup>

import NavigationBar from "@/components/NavigationBar.vue";
import Welcome from "@/components/Welcome.vue";
import header from "@/assets/imgs/Kotlin.png"
import avatar from "@/assets/imgs/avatar.jpg"
import AboutMe from "@/components/AboutMe.vue";
import {onBeforeUnmount, onMounted, ref} from "vue";
import LightIcon from "@/components/icons/LightIcon.vue";
import DarkIcon from "@/components/icons/DarkIcon.vue";
import {title, navItems, desc, skillStack, projects, comments} from "@/config/appConfig.js";
import ProjectList from "@/components/ProjectList.vue";
import Comments from "@/components/Comments.vue";

onMounted(() => {
  history.scrollRestoration = 'manual';
  window.scrollTo({top: 0, behavior: 'auto'});
});

const particles = ref([]);
const createParticle = () => {
  return {
    x: Math.random() * window.innerWidth,
    y: Math.random() * window.innerHeight,
    size: Math.random() * 10 + 5,
    opacity: 1,
  };
};

const MAX_PARTICLES = 64;
let animationFrame;
onMounted(() => {
  animationFrame = requestAnimationFrame(function run() {
    if (particles.value.length < MAX_PARTICLES) {
      particles.value.push(createParticle());
    }

    for (let i = particles.value.length - 1; i >= 0; i--) {
      particles.value[i].opacity -= 0.01;
      if (particles.value[i].opacity <= 0) {
        particles.value.splice(i, 1);
      }
    }

    animationFrame = requestAnimationFrame(run)
  })
});
onBeforeUnmount(() => {
  cancelAnimationFrame(animationFrame)
});
const isLightTheme = ref(true)
const toggleTheme = () => {
  const currentTheme = document.documentElement.getAttribute('data-theme') || "light";
  const newTheme = currentTheme === "light" ? "dark" : "light";
  document.documentElement.setAttribute("data-theme", newTheme);
  localStorage.setItem("using-theme", newTheme);
  isLightTheme.value = newTheme === "light";
};

onMounted(() => {
  let savedTheme = localStorage.getItem('using-theme');
  // if (!savedTheme) {
  //   const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
  //   savedTheme = prefersDark ? "dark" : "light";
  // }
  document.documentElement.setAttribute('data-theme', savedTheme);
  isLightTheme.value = savedTheme === "light";
})
</script>

<template>
  <div class="snow-wrapper">
    <div v-for="particle in particles"
         class="snow"
         :style="{
        left: `${particle.x}px`,
        top: `${particle.y}px`,
        width: `${particle.size}px`,
        height: `${particle.size}px`,
        opacity: particle.opacity
      }"></div>
  </div>
  <NavigationBar :nav-items="navItems" :header-img="header"/>
  <Welcome :title="title.title" :desc="title.subtitle" :avatar="avatar"/>
  <AboutMe :desc="desc" :skillStack="skillStack"/>
  <ProjectList :projects="projects"/>
  <Comments :comments="comments"/>
  <div class="footer">
    <span>已经到底了 ;w;</span>
  </div>

  <LightIcon class="theme-icon light-theme" v-show="!isLightTheme" @click="toggleTheme"/>
  <DarkIcon class="theme-icon dark-theme" v-show="isLightTheme" @click="toggleTheme"/>
</template>

<style scoped>
.light-theme,
.dark-theme {
  position: fixed;
  right: 0;
  bottom: 0;
  z-index: 1;
  width: 2rem;
  height: 2rem;
  margin-right: 2rem;
  margin-bottom: 2rem;
  color: var(--primary-color);
  background-color: var(--text-color);
  border-radius: 15px;
  padding: 4px;
}

.theme-icon {
  transition: transform 0.1s ease-out;
  cursor: pointer;
}

.theme-icon:active {
  transform: scale(0.9);
}

.theme-icon:hover:not(:active) {
  transform: scale(1.1);
}

.snow-wrapper {
  position: fixed;
  inset: 0;
  pointer-events: none;
}

.snow {
  position: absolute;
  background-color: white;
  border-radius: 50%;
}

.footer {
  display: flex;
  width: 100%;
  margin-top: 5rem;
  margin-bottom: 5rem;
  justify-content: center;
  color: var(--text-color);
  font-weight: bold;
  font-size: 1.2rem;
  user-select: none;
}
</style>
