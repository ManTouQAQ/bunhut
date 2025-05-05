<script setup>
import {onBeforeUnmount, onMounted, ref} from "vue";

defineProps({
  desc: Array,
  skillStack: Object,
})

const descRef = ref(null)
const skillsRef = ref(null)
const needObserveRefs = [
  descRef,
  skillsRef
]

let observer = null;
onMounted(() => {
  observer = new IntersectionObserver(
      (entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.style.animationPlayState = 'running'
            observer.unobserve(entry.target);
          }
        });
      },
      {
        threshold: 0.1
      }
  );

  needObserveRefs.forEach((item) => {observer.observe(item.value)})
})

onBeforeUnmount(() => {
  if (observer) {
    observer.disconnect();
  }
});
</script>

<template>
  <div id="about" class="about-wrapper">
    <div class="about-body">
      <div class="description" ref="descRef">
        <span class="bubble-title">About</span>
        <div class="description-lines">
          <p v-for="(line, index) in desc" :key="index" class="description-line">
            {{ line }}
          </p>
        </div>
      </div>
      <div class="skills" ref="skillsRef">
        <span class="bubble-title">Skill Stack</span>
        <div class="skills-groups">
          <div v-for="[category, skills] in skillStack" :key="category" class="skill-category">
            <span class="skill-category-title">{{ category }}</span>
            <div class="skill-tags">
              <span v-for="skill in skills" :key="skill" class="bubble-tag skill-tag">
                {{ skill }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.about-wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 5rem;
  margin-bottom: 1rem;
  overflow: hidden;
}

.about-body {
  display: flex;
  width: min(100%, 80rem);
  margin-left: 3rem;
  margin-right: 3rem;
  gap: 2rem;
}

.description {
  width: 100%;
  animation: fadeInLeft 0.5s ease-out forwards paused;
}

.description-lines {
  padding-top: 2rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.description-line {
  color: var(--text-color);
}

.skills {
  width: 100%;
  text-align: right;
  animation: fadeInRight 0.5s ease-out forwards paused;
}

.skills-groups {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  user-select: none;
}

.skill-category {
  display: flex;
  align-items: end;
  flex-direction: column;
  gap: 0.5rem;
}

.skill-category-title {
  font-weight: bold;
  color: var(--text-hover-color);
}

.skill-tags {
  display: flex;
  flex-wrap: wrap;
  justify-content: right;
  max-width: 25rem;
  gap: 3px;
}

.skill-tag:hover {
  background-color: var(--primary-color);
  color: var(--text-hover-color);
}

@media (max-width: 768px) {
  .about-body {
    flex-direction: column;
  }

  .description,
  .skills {
    text-align: center;

  }

  .description-lines {
    text-align: left;
    padding-top: 0;
  }

  .skill-category {
    align-items: start;
  }

  .skill-tags {
    justify-content: start;
  }
}
</style>