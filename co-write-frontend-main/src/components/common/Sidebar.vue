<!-- 左侧边栏组件 -->
<template>
  <aside class="sidebar" :class="{ collapsed }">
    <!-- 折叠按钮 - 顶部 -->
    <div class="sidebar-header">
      <el-button 
        circle 
        size="small" 
        @click="$emit('toggle')" 
        :icon="collapsed ? ArrowRight : ArrowLeft"
        class="collapse-btn"
        type="primary"
        plain
      />
    </div>

    <div class="sidebar-content">
      <!-- Element Plus 菜单 -->
      <el-menu
        :default-active="activeMenu"
        :collapse="collapsed"
        :collapse-transition="false"
        background-color="#f8f9fa"
        text-color="#606266"
        active-text-color="#409eff"
        @select="handleMenuSelect"
        class="sidebar-menu"
      >
        <!-- 首页 -->
        <el-menu-item index="home" @click="$router.push('/')">
          <el-icon><House /></el-icon>
          <template #title>首页</template>
        </el-menu-item>

        <!-- 我的知识库 -->
        <el-sub-menu index="my-knowledge">
          <template #title>
            <el-icon><Folder /></el-icon>
            <span>我的知识库</span>
          </template>
          
          <!-- 知识库列表 -->
          <el-sub-menu 
            v-for="knowledge in myKnowledgeList" 
            :key="knowledge.id"
            :index="`knowledge-${knowledge.id}`"
          >
            <template #title>
              <el-icon><FolderOpened /></el-icon>
              <span>{{ knowledge.name }}</span>
            </template>
            
            <!-- 文档列表 -->
            <el-menu-item 
              v-for="doc in knowledge.docs" 
              :key="doc.id"
              :index="`doc-${doc.id}`"
              @click="$router.push(`/knowledge/${knowledge.id}/${doc.id}`)"
            >
              <el-icon><Document /></el-icon>
              <template #title>{{ doc.name }}</template>
            </el-menu-item>
          </el-sub-menu>
        </el-sub-menu>

        <!-- 我共享的文档 -->
        <el-sub-menu index="shared-docs">
          <template #title>
            <el-icon><Share /></el-icon>
            <span>我共享的文档</span>
          </template>
          
          <el-menu-item 
            v-for="doc in sharedDocuments" 
            :key="doc.id"
            :index="`shared-${doc.id}`"
            @click="$router.push(`/shared/${doc.id}`)"
          >
            <el-icon><Document /></el-icon>
            <template #title>{{ doc.name }}</template>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
  </div>
  </aside>
</template>

<script setup lang="ts">
import { computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { 
  ElMenu, 
  ElMenuItem, 
  ElSubMenu, 
  ElIcon, 
  ElButton 
} from 'element-plus'
import { 
  House, 
  Folder, 
  FolderOpened, 
  Document, 
  Share,
  ArrowLeft,
  ArrowRight
} from '@element-plus/icons-vue'
import { useKnowledgeStore } from '@/stores/knowledge'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'

interface Props {
  collapsed: boolean
}

defineProps<Props>()
defineEmits<{
  toggle: []
}>()

const route = useRoute()
const knowledgeStore = useKnowledgeStore()

const { knowledgeList: myKnowledgeList } = storeToRefs(knowledgeStore)

const activeMenu = computed(() => {
  const path = route.path
  if (path === '/') return 'home'
  if (path.startsWith('/editor/')) {
    const docId = route.params.documentId as string
    return `doc-${docId}`
  }
  return 'home'
})

const sharedDocuments = ref([
  { id: 'shared1', name: '项目需求文档' },
  { id: 'shared2', name: '技术方案设计' },
  { id: 'shared3', name: '用户手册' },
  { id: 'shared4', name: '会议纪要' }
])

const handleMenuSelect = (_key: string, _keyPath: string[]) => {
  // console.log('菜单选择:', key, keyPath)
}

onMounted(() => {
  if (myKnowledgeList.value.length === 0) {
    // console.log('侧边栏发现Store为空,正在获取知识库列表...')
    knowledgeStore.fetchKnowledgeList()
  } else {
    // console.log('侧边栏发现Store已有数据,直接使用')
  }
})
</script>

<style scoped lang="scss">
.sidebar {
  width: 260px;
  flex-shrink: 0;
  height: 100%;
  background: #f8f9fa;
  border-right: 1px solid #ebeef5;
  transition: width 0.3s ease;
  display: flex;
  flex-direction: column;
  position: relative;

  &.collapsed {
    width: 64px;
  }
}

.sidebar-header {
  padding: 12px 16px;
  border-bottom: 1px solid #ebeef5;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  
  .collapsed & {
    justify-content: center;
    padding: 12px 8px;
  }
}

.sidebar-content {
  flex: 1;
  overflow-y: auto;
  overflow-x: hidden;
}

.sidebar-menu {
  border: none;
  height: 100%;
  
  // 自定义菜单样式
  :deep(.el-menu-item) {
    height: 48px;
    line-height: 48px;
    
    &.is-active {
      background-color: #e8f4ff;
      border-right: 3px solid #409eff;
    }
  }
  
  :deep(.el-sub-menu) {
    .el-sub-menu__title {
      height: 48px;
      line-height: 48px;
    }
  }
  
  // 折叠状态下的样式
  &.el-menu--collapse {
    :deep(.el-sub-menu__title) {
      padding: 0 20px;
    }
    
    :deep(.el-menu-item) {
      padding: 0 20px;
    }
  }
}

.collapse-btn {
  transition: all 0.3s ease;
  
  &:hover {
    transform: scale(1.1);
  }
}

// 自定义滚动条
.sidebar-content::-webkit-scrollbar {
  width: 4px;
}

.sidebar-content::-webkit-scrollbar-track {
  background: transparent;
}

.sidebar-content::-webkit-scrollbar-thumb {
  background: #dcdfe6;
  border-radius: 2px;
}

.sidebar-content::-webkit-scrollbar-thumb:hover {
  background: #c0c4cc;
}

// 调整Element Plus图标样式
:deep(.el-icon) {
  margin-right: 8px;
}

@media (max-width: 768px) {
  .sidebar {
    transform: translateX(-100%);
    
    &:not(.collapsed) {
      transform: translateX(0);
    }
  }
}
</style> 