// 知识库状态管理 - Pinia Store
import { ref } from 'vue'
import { defineStore } from 'pinia'


export const useKnowledgeStore = defineStore('knowledge', () => {
  // 1. State: 直接在这里硬编码初始数据
  const knowledgeList = ref<any[]>([
    {
      id: 'kb_1',
      name: '产品设计',
      cover_url: '/figure1.png',
      docs: [
        { id: '1', name: '用户故事地图' },
        { id: '2', name: '原型设计规范' }
      ]
    },
    {
      id: 'kb_2',
      name: '前端开发',
      cover_url: '/figure2.png',
      docs: [
        { id: '3', name: 'Vue3 最佳实践' },
        { id: '4', name: 'TypeScript 指南' },
        { id: '5', name: 'Pinia 状态管理' }
      ]
    },
  ])

  // 2. Action: 暂时让 fetchKnowledgeList 什么都不做
  const fetchKnowledgeList = async () => {
    console.log('fetchKnowledgeList 被调用，但当前使用硬编码数据，跳过API请求。')
    // 当你需要恢复API获取时，取消下面的注释即可
    /*
    try {
      const result = await getKnowledgeListAPI('user_001')
      if (result && result.knowledge_list) {
        knowledgeList.value = result.knowledge_list.map(kb => ({ ...kb }))
      }
    } catch (error) {
      console.error("Failed to fetch knowledge list:", error)
      knowledgeList.value = []
    }
    */
  }

  // 3. Action
  const deleteKnowledge = async (id: string) => {
    console.log(`尝试删除ID为 ${id} 的知识库...`)
    const index = knowledgeList.value.findIndex(item => item.id === id)
    if (index !== -1) {
      knowledgeList.value.splice(index, 1)
      console.log(`ID ${id} 已被删除。`)
    } else {
      console.warn(`未找到ID为 ${id} 的知识库。`)
    }
    // 当你需要恢复API时，在这里添加 await deleteKnowledgeAPI(id)
    // await deleteKnowledgeAPI(id)
  }
  // const createKnowledge = async (data: CreateKnowledgeRequest): Promise<void> => {
  //   const result = await createKnowledgeAPI(data)
  //   knowledgeList.value.push(result.knowledge)
  // }
  // const editKnowledge = async (id: string, name: string, cover_url: string): Promise<void> => {
  //   await editKnowledgeAPI(id, name, cover_url)
  // }

  return {
    knowledgeList,
    fetchKnowledgeList,
    deleteKnowledge,
    // createKnowledge,
    // editKnowledge
  }
}) 