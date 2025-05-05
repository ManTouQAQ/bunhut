<script setup>
import {scrollToTop, scrollTo} from "@/utils/common.js";

defineProps({
  navItems: Array,
  headerImg: String
});

const navTo = (item) => {
  if (item.changeUrl) {
    window.open(item.changeUrl, '_self');
    return
  }
  if (item.openUrl){
    window.open(item.openUrl, '_blank');
    return
  }
  if (item.scrollTo){
    scrollTo(item.scrollTo)
    return
  }
}

</script>

<template>
  <div class="nav-bar-wrapper">
    <div class="nav-bar">
      <div class="nav-header" @click="scrollToTop">
        <img :src="headerImg" alt=""/>
      </div>
      <div class="nav-items">
        <span v-for="item in navItems" :key="item" class="nav-item" @click="navTo(item)">
          {{ item.name }}
        </span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.nav-bar-wrapper {
  position: fixed;
  height: 64px;
  width: 100%;
  z-index: 1;
  justify-content: center;
  display: flex;
  background-color: var(--secondary-color-transparent);
  border-bottom: 1px solid var(--secondary-color-transparent-transition);
  backdrop-filter: blur(10px);
  user-select: none;
}

.nav-bar {
  width: min(100%, 80rem);
  margin-left: 1.5rem;
  margin-right: 3rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.nav-items {
  display: flex;
  gap: 1.5rem;
}

.nav-item {
  color: var(--text-color);
  font-weight: 500;
  transition: color 0.2s;
  cursor: pointer;
}

.nav-item:hover {
  color: var(--text-hover-color)
}

.nav-header {
  height: 100%;
  cursor: pointer;
}

.nav-header img {
  width: auto;
  height: 100%;
  object-fit: contain;
  pointer-events: none;
  filter: drop-shadow(0 0 0 var(--text-color)) drop-shadow(1px 0 0 var(--text-color)) drop-shadow(-1px 0 0 var(--text-color)) drop-shadow(0 1px 0 var(--text-color)) drop-shadow(0 -1px 0 var(--text-color));
}
</style>