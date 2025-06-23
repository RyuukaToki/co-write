<!-- 知识库目录树组件 -->
<template>
  <div class="knowledge-tree-container">
    <!-- 目录树内容 -->
    <ul class="tree-root">
      <TreeNode
        v-for="item in treeData"
        :key="item.id"
        :node="item"
        @add="handleAdd"
      />
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import TreeNode from './TreeNode.vue'

const treeData = ref([
  {
    id: '1',
    name: '研发部',
    type: 'folder',
    children: [
      { id: 'doc1', name: '文档1', type: 'doc' },
      { id: 'doc2', name: '文档2', type: 'doc' }
    ]
  },
  {
    id: '2',
    name: '运营部',
    type: 'folder',
    children: []
  }
])

function handleAdd({ parentId, type }) {
  // 这里根据 parentId 和 type 新增文件夹或文档
  // 你可以弹窗输入名称后 push 到 treeData 对应 children
}
</script>

<style scoped>
.knowledge-tree-container {
  /* 目录树样式 */
}

.tree-root {
  list-style: none;
  padding-left: 0;
  margin: 0;
}
</style>