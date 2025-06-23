<!-- 知识库首页 -->
<template>
  <div class="home-container">
    <!-- 知识库列表区域 -->
    <section class="knowledge-section">
      <div class="section-header">
        <h2 class="section-title">知识库列表</h2>
        <button class="btn btn-primary" @click="createKnowledge" style="margin-top: 10px;">
          <span class="icon">+</span>
          新建知识库
        </button>
      </div>
      
      <div class="knowledge-grid">
        <div 
          v-for="knowledge in knowledgeList" 
          :key="knowledge.id"
          class="knowledge-card"
          @click="openKnowledge(knowledge.id)"
        >
          <img :src="knowledge.cover_url" :alt="knowledge.name" class="knowledge-cover" />
          <div class="knowledge-info">
            <h3 class="knowledge-name">{{ knowledge.name }}</h3>
            <p class="knowledge-stats">{{ knowledge.docs.length }} 个文档</p>
          </div>
          
          <!-- 操作按钮区域 -->
          <div class="knowledge-actions" @click.stop>
            <el-button 
              type="primary" 
              size="small" 
              :icon="Edit"
              @click="editKnowledge(knowledge)"
              class="action-btn"
            >
              编辑
            </el-button>
            <el-button 
              type="danger" 
              size="small" 
              :icon="Delete"
              @click="deleteKnowledge(knowledge)"
              class="action-btn"
            >
              删除
            </el-button>
          </div>
        </div>
      </div>
    </section>

    <!-- 最近访问区域 -->
    <section class="recent-section">
      <div class="section-header">
        <h2 class="section-title">最近访问列表</h2>
        <div class="view-controls">
          <button 
            class="view-btn" 
            :class="{ active: viewMode === 'list' }"
            @click="viewMode = 'list'"
          >
            📋 列表
          </button>
          <button 
            class="view-btn" 
            :class="{ active: viewMode === 'grid' }"
            @click="viewMode = 'grid'"
          >
            🔲 网格
          </button>
        </div>
      </div>
      
      <div class="recent-documents" :class="viewMode">
        <div 
          v-for="document in recentDocuments" 
          :key="document.id"
          class="document-item"
          @click="openDocument(document.id)"
        >
          <div class="document-icon">
            <span class="file-icon">📄</span>
          </div>
          <div class="document-info">
            <h4 class="document-title">{{ document.title }}</h4>
            <p class="document-time">{{ formatTime(document.updated_at) }}</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElButton, ElMessageBox, ElMessage } from 'element-plus'
import { Edit, Delete } from '@element-plus/icons-vue'
import { editKnowledgeAPI } from '@/api/knowledge'
import { useKnowledgeStore } from '@/stores/knowledge'
import { storeToRefs } from 'pinia'

const router = useRouter()
const viewMode = ref<'list' | 'grid'>('grid')
const recentDocuments = ref<any[]>([])
const knowledgeStore = useKnowledgeStore()

const { knowledgeList } = storeToRefs(knowledgeStore)

const createKnowledge = () => {
  // TODO: 打开创建知识库对话框
  console.log('创建知识库')
}

const openKnowledge = (id: string) => {
  router.push(`/knowledge/${id}`)
}

const editKnowledge = async (knowledge: any) => {
  // TODO: 打开编辑知识库对话框
  console.log('编辑知识库:', knowledge.name)
  // 弹出编辑知识库对话框，显示现在有的名称和封面，用户可以修改名称和封面

  await editKnowledgeAPI(knowledge.id, knowledge.name, knowledge.cover_url)
}

const deleteKnowledge = async (knowledge: any) => {
  try {
    await ElMessageBox.confirm(
      `确定要删除知识库"${knowledge.name}"吗？删除后无法恢复。`,
      '删除确认',
      {
        confirmButtonText: '确定删除',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
    
    await knowledgeStore.deleteKnowledge(knowledge.id)
    
    ElMessage({
      message: `知识库"${knowledge.name}"已删除`,
      type: 'success',
    })
  } catch (error) {
    console.error('删除操作失败:', error)
    // 可以在这里加一个错误提示
  }
}

const openDocument = (id: string) => {
  // 注意：这里我们假设最近访问的文档也属于某个知识库
  // 在真实场景中，Document对象需要包含其knowledge_id
  const knowledgeId = '1'; // 模拟一个所属知识库ID
  router.push(`/knowledge/${knowledgeId}/${id}`)
}

const formatTime = (time: string) => {
  return new Date(time).toLocaleString()
}

const loadData = () => {
  if (knowledgeList.value.length === 0) {
    knowledgeStore.fetchKnowledgeList()
  }

  // 暂时保留最近访问的模拟数据
  recentDocuments.value = [
    {
      id: 'doc1',
      title: '产品需求文档 V1.2',
      updated_at: '2025-06-14 10:30'
    },
    {
      id: 'doc2',
      title: '第二季度开发计划',
      updated_at: '2025-06-13 18:45'
    }
  ]
}

onMounted(() => {
  loadData()
})
</script>

<style scoped lang="scss">
.home-container {
  max-width: 1200px;
  margin: 0 auto;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.section-title {
  font-size: 20px;
  font-weight: 600;
  margin: 0;
  color: #303133;
}

.btn {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 10px 20px;
  border: none;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
  
  &.btn-primary {
    background: #409eff;
    color: white;
    
    &:hover {
      background: #337ecc;
    }
  }
}

.knowledge-section {
  margin-bottom: 48px;
}

.knowledge-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 16px;
}

.knowledge-card {
  background: white;
  border-radius: 8px;
  padding: 16px;
  cursor: pointer;
  transition: all 0.2s;
  border: 1px solid #ebeef5;
  position: relative;
  
  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    
    .knowledge-actions {
      opacity: 1;
      visibility: visible;
    }
  }
}

.knowledge-cover {
  width: 100%;
  height: 120px;
  object-fit: cover;
  border-radius: 6px;
  margin-bottom: 12px;
}

.knowledge-name {
  font-size: 16px;
  font-weight: 500;
  margin: 0 0 4px 0;
  color: #303133;
}

.knowledge-stats {
  font-size: 14px;
  color: #909399;
  margin: 0;
}

.view-controls {
  display: flex;
  gap: 8px;
}

.view-btn {
  padding: 6px 12px;
  border: 1px solid #dcdfe6;
  background: white;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  transition: all 0.2s;
  
  &.active {
    background: #409eff;
    color: white;
    border-color: #409eff;
  }
  
  &:hover:not(.active) {
    border-color: #409eff;
    color: #409eff;
  }
}

.recent-documents {
  &.grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 16px;
  }
  
  &.list {
    display: flex;
    flex-direction: column;
    gap: 8px;
  }
}

.document-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px;
  background: white;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s;
  border: 1px solid #ebeef5;
  
  &:hover {
    background: #f5f7fa;
    border-color: #409eff;
  }
}

.document-icon {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f7fa;
  border-radius: 6px;
  
  .file-icon {
    font-size: 20px;
  }
}

.document-title {
  font-size: 14px;
  font-weight: 500;
  margin: 0 0 4px 0;
  color: #303133;
}

.document-time {
  font-size: 12px;
  color: #909399;
  margin: 0;
}

.knowledge-actions {
  position: absolute;
  bottom: 16px;
  right: 16px;
  display: flex;
  gap: 8px;
  opacity: 0;
  visibility: hidden;
  transition: all 0.2s ease;
  
  .action-btn {
    padding: 4px 8px;
    font-size: 12px;
  }
}

@media (max-width: 768px) {
  .section-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 16px;
  }
  
  .knowledge-grid {
    grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
  }
  
  .recent-documents.grid {
    grid-template-columns: 1fr;
  }
}
</style> 