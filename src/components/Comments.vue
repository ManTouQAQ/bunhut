<script setup>

import CommentLine from "@/components/CommentLine.vue";
import {computed, onBeforeUnmount, onMounted, ref} from "vue";

const API_BASE = import.meta.env.VITE_API_BASE

const username = defineModel("username", {
  default: ""
});
const commentInput = defineModel("commentInput", {
  default: ""
});

const props = defineProps({
  comments: Array
})

const onlineComments = ref([])

async function getComments() {
  const response = await fetch(`${API_BASE}/comments`)
  const result = await response.json()
  if (result.code !== 200) {
    return
  }

  onlineComments.value = result.data.map(item => ({
    avatar: item.avatarUrl,
    author: item.author,
    comment: item.comment
  }))
}
onMounted(getComments)

const commentSubmitTip = ref()

let timeout;
function addPopupTip(msg){
  if (timeout){
    clearTimeout(timeout)
  }

  commentSubmitTip.value = msg

  timeout = setTimeout(() => {
    commentSubmitTip.value = null
    timeout = null
  }, 500)
}

onBeforeUnmount(() => {
  if (timeout) {
    clearTimeout(timeout)
  }
})

async function addComment() {
  const trimmedUsername = username.value.trim();
  if (trimmedUsername === "") {
    addPopupTip("名字不能为空喵 ;w;")
    return
  }
  const trimmedCommentInput = commentInput.value.trim();
  if (trimmedCommentInput === ""){
    addPopupTip("评论内容不能为空喵 ;w;")
    return;
  }
  const response = await fetch(`${API_BASE}/comments`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: trimmedUsername,
      comment: trimmedCommentInput,
    })
  }).catch(() => {
    addPopupTip("评论发送失败喵 ;w;")
  });

  const result = await response.json()
  if (result.code !== 200) {
    addPopupTip("评论发送失败喵 ;w;")
    return
  }
  await getComments()
}


const commentList = computed(() => {
  return [...props.comments, ...onlineComments.value]
})
</script>

<template>
  <Transition name="comment-submit-popup">
    <div v-if="commentSubmitTip" class="comment-submit-popup-wrapper">
      <span class="comment-submit-tip">{{ commentSubmitTip }}</span>
    </div>
  </Transition>
  <div class="comments-wrapper" v-if="commentList.length > 0">
    <div class="comments-box">
      <div class="comments-title">
        <span class="bubble-title">Comments</span>
      </div>
      <div class="comment-edit-box-wrapper">
        <div class="comment-edit-box">
          <span class="comment-edit-title">用户名:</span>
          <input class="comment-username-edit-box" v-model="username"/>
          <span class="comment-edit-title">内容:</span>
          <input class="comment-content-edit-box" v-model="commentInput" @keyup.enter.prevent="addComment"/>
        </div>
      </div>
      <div class="comments-content">
        <CommentLine v-for="comment in commentList" :comment="comment"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.comment-submit-popup-enter-active {
  animation: bounce-in 0.5s ease-in-out;
}

.comment-submit-popup-leave-to {
  animation: bounce-in 0.5s ease-in-out reverse;
}

@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.25);
  }
  100% {
    transform: scale(1);
  }
}

.comment-submit-popup-wrapper {
  position: fixed;
  display: flex;
  left: 0;
  bottom: 2rem;
  width: 100%;
  justify-content: center;
}

.comment-submit-tip {
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 1rem;
  display: inline-block;
  transition: color 0.2s, border 0.2s;
  color: var(--primary-color);
  background: var(--text-hover-color);
  padding-left: 2rem;
  padding-right: 2rem;
  padding-bottom: 0.25rem;
  border-radius: 25px;
}

.comments-wrapper{
  display: flex;
  justify-content: center;
  padding-top: 1rem;
  padding-bottom: 1rem;
}

.comments-box{
  display: flex;
  width: min(100%, 80rem);
  margin-left: 3rem;
  margin-right: 3rem;
  flex-direction: column;
}

.comments-title{
  width: 100%;
}

.comments-content {
  margin-top: 1rem;
  padding-top: 1rem;
  padding-bottom: 2rem;
  box-shadow: 0 1px 20px -6px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  background: var(--primary-color);
  overflow: hidden;
}

.comment-edit-box-wrapper{
  display: flex;
  margin-top: 1rem;
}

.comment-edit-box {
  display: grid;
  width: 100%;
  box-shadow: 0 1px 20px -6px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  background: var(--primary-color);
  grid-template-columns: auto 1fr;
  flex-direction: column;
  overflow: hidden;
  padding: 1rem;
  gap: 1rem;
}

.comment-edit-title {
  color: var(--text-hover-color);
  font-weight: bold;
  display: flex;
  align-items: center;
}

.comment-username-edit-box {
  width: 100%;
  height: 100%;
  border: none;
  background: none;
  outline: none;
  border-bottom: 2px solid var(--text-hover-color);
  color: var(--text-color);
  font-weight: bold;
  font-size: 1rem;
}

.comment-content-edit-box {
  width: 100%;
  height: 4rem;
  min-height: 4rem;
  padding: 0;
  border: none;
  background: none;
  outline: none;
  font-size: 1rem;
  font-family: inherit;
  color: var(--text-color);
  border-bottom: 2px solid var(--text-hover-color);
}

@media (max-width: 768px) {
  .comments-title{
    text-align: center;
  }
}
</style>