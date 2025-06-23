// 知识库相关接口
import request from './request'
import type {
  CreateKnowledgeRequest,
  GetKnowledgeListResponse,
  CreateKnowledgeResponse,
} from '@/types/index'

// 获取知识库列表
export const getKnowledgeListAPI = (userId: string): Promise<GetKnowledgeListResponse> => {
  return request.get('/kbs/list', { 
    headers: {
      'X-user-Id': userId
    }
  })
}

// 新建知识库
export const createKnowledgeAPI = (data: CreateKnowledgeRequest): Promise<CreateKnowledgeResponse> => {
  return request.post('/kbs/create', data)
}

// 编辑知识库
export const editKnowledgeAPI = (id: string, name: string, cover_url: string): Promise<void> => {
  return request.put(`/kbs/edit`, {
    id,
    name,
    cover_url
  })
}

// 删除知识库
export const deleteKnowledgeAPI = (id: string): Promise<void> => {
  return request.delete(`/kbs/${id}`)
} 