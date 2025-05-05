<script setup>
import missingno from '@/assets/imgs/missingno.jpg'
import {computed} from "vue";

const props = defineProps({
  comment: {
    type: Object,
    required: true
  }
})

const avatar = computed(() => {
  return props.comment.avatar === undefined ? missingno : props.comment.avatar;
})

</script>

<template>
  <div class="comment-line-wrapper">
    <div class="comment-author-avatar">
      <img :src="avatar" alt=""/>
    </div>
    <div class="comment-content-box">
      <div class="comment-author-name">
        <span class="author-name">{{ comment.author }}</span>
      </div>
      <div class="comment-content">
        <span class="comment-line">{{ comment.comment }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.comment-line-wrapper {
  display: flex;
  margin-top: 1rem;
  padding-bottom: 1rem;
  margin-left: 1rem;
  margin-right: 1rem;
  border-bottom: 3px solid var(--secondary-color-transparent-transition);
}

.comment-author-avatar{
  flex-shrink: 0;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  overflow: hidden;
  user-select: none;
}

.comment-author-avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  pointer-events: none;
}

.comment-content-box{
  padding-left: 1rem;
}

.comment-author-name{
  color: var(--text-hover-color);
  font-weight: bold;
}

.comment-content {
  padding-top: 0.5rem;
}

.comment-line {
  color: var(--contrast-color);
}

</style>