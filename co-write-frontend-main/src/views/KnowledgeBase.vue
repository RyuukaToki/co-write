<!-- 知识库管理页面 -->
<template>
  <div class="knowledge-base-container">
    <!-- 
      这里可以放当前知识库的通用信息，
      或者当没有指定具体文档时，显示文档列表。
      子路由(文档编辑器)会在这里渲染。
    -->
    <router-view></router-view>
  </div>
</template>

<script setup lang="ts">
// 知识库管理逻辑
// 可以在这里根据 route.params.knowledgeId 获取知识库信息
</script>

<style scoped>
.knowledge-base-container {
  width: 100%;
  height: 100%;
}
</style> 